package fr.aet.plugins.usbserial;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Handler;
import android.os.Looper;

import org.json.JSONArray;
import org.json.JSONObject;

import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;
import com.hoho.android.usbserial.util.SerialInputOutputManager;

import java.io.IOException;
import java.lang.Error;
import java.util.List;
import java.util.List;
import java.util.Map;

// https://github.com/mik3y/usb-serial-for-android

public class UsbSerial implements SerialInputOutputManager.Listener {
    private final Context context;
    private final Callback callback;

    public static final String ACTION_USB_PERMISSION = "fr.aet.plugins.usbserial.ACTION_USB_PERMISSION";
    private static final int WRITE_WAIT_MILLIS = 2000;
    private static final int READ_WAIT_MILLIS = 2000;

    private SerialInputOutputManager usbIoManager = null;
    private UsbSerialPort usbSerialPort = null;
    private UsbDevice currentDevice = null;
    private final Handler mainLooper;

    public UsbSerial(Context context, Callback callback) {
        super();
        this.context = context;
        this.callback = callback;

        mainLooper = new Handler(Looper.getMainLooper());

        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
                    UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
                    callback.usbDeviceAttached(getDeviceInfo(usbDevice));
                }
            }
        }, new IntentFilter(UsbManager.ACTION_USB_DEVICE_ATTACHED));

        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
                    UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
                    callback.usbDeviceDetached(getDeviceInfo(usbDevice));
                }
            }
        }, new IntentFilter(UsbManager.ACTION_USB_DEVICE_DETACHED));

        // context.registerReceiver(new BroadcastReceiver() {
        // @Override
        // public void onReceive(Context context, Intent intent) {
        // String action = intent.getAction();
        // if (ACTION_USB_PERMISSION.equals(action)) {
        // synchronized (this) {
        // UsbDevice device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
        // if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
        // if (device != null && (device == currentDevice)) {
        // try {
        // openSerialPort(device);
        // } catch (Exception ignored) {
        // }
        // }
        // }
        // }
        // }
        // }
        // }, new IntentFilter(ACTION_USB_PERMISSION));
    }

    @Override
    public void onNewData(byte[] data) {
        mainLooper.post(() -> {
            callback.receivedData(new String(data));
        });
    }

    @Override
    public void onRunError(Exception e) {
        mainLooper.post(() -> {
            callback.error(new Error(e.getMessage(), e.getCause()));
            closeSerial();
        });
    }

    public void closeSerial() {
        setConnectedDevice(null);
        if (usbIoManager != null) {
            usbIoManager.setListener(null);
            usbIoManager.stop();
            usbIoManager = null;
        }
        try {
            if (usbSerialPort != null) {
                usbSerialPort.close();
            }
        } catch (IOException ignored) {
        }
        usbSerialPort = null;
    }

    public void openSerial(UsbSerialOptions settings) throws IOException {
        try {
            closeSerial();

            UsbDevice device = null;
            UsbManager usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);

            for (UsbDevice usbDevice : usbManager.getDeviceList().values()) {
                if ((usbDevice.getVendorId() == settings.vendorId) &&
                        (usbDevice.getProductId() == settings.productId)) {
                    device = usbDevice;
                    break;
                }
            }
            if (device == null) {
                throw new IOException("connectionFailed:DeviceNotFound");
            }

            UsbSerialDriver driver = UsbSerialProber.getDefaultProber().probeDevice(device);
            if (driver == null) {
                throw new IOException("connectionFailed:NoDriverForDevice");
            }
            if (driver.getPorts().size() < settings.portNum) {
                throw new IOException("connectionFailed:NoAvailablePorts");
            }

            if (!usbManager.hasPermission(driver.getDevice())) {
                PendingIntent usbPermissionIntent = PendingIntent.getBroadcast(context, 0,
                        new Intent(ACTION_USB_PERMISSION),
                        PendingIntent.FLAG_CANCEL_CURRENT |
                                PendingIntent.FLAG_IMMUTABLE);
                usbManager.requestPermission(driver.getDevice(), usbPermissionIntent);
                throw new IOException("connectionFailed:UsbConnectionPermissionDenied");
            }

            UsbDeviceConnection usbConnection = usbManager.openDevice(driver.getDevice());
            if (usbConnection == null) {
                throw new IOException("connectionFailed:SerialOpenFailed");
            }

            usbSerialPort = driver.getPorts().get(settings.portNum);
            usbSerialPort.open(usbConnection);
            usbSerialPort.setParameters(settings.baudRate, settings.dataBits, settings.stopBits, settings.parity);
            if (settings.dtr)
                usbSerialPort.setDTR(true);
            if (settings.rts)
                usbSerialPort.setRTS(true);

            usbIoManager = new SerialInputOutputManager(usbSerialPort, this);
            usbIoManager.start();

            setConnectedDevice(device);
        } catch (Exception exception) {
            closeSerial();
            throw new IOException(exception.getMessage());
        }
    }

    public void writeSerial(String str) throws IOException {
        if (this.currentDevice == null) {
            throw new IOException("writeFailed:DeviceNotConnected");
        }
        if (str.length() == 0) {
            throw new IOException("writeFailed:EmptyData");
        }
        try {
            usbSerialPort.write(str.getBytes(), WRITE_WAIT_MILLIS);
        } catch (Exception e) {
            closeSerial();
            throw new IOException("writeFailed:ConnectionLost");
        }
    }

    public JSONArray listDevices() throws IOException {
        try {
            UsbManager usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
            Map<String, UsbDevice> UsbDeviceMap = usbManager.getDeviceList();
            JSONArray devices = new JSONArray();

            for (UsbDevice device : UsbDeviceMap.values()) {
                UsbSerialDriver driver = UsbSerialProber.getDefaultProber().probeDevice(device);
                if (driver != null) {
                    devices.put(getDeviceInfo(device));
                }
            }
            return devices;
        } catch (Exception e) {
            throw new IOException("listFailed:CannotListDevices");
        }
    }

    private JSONObject getDeviceInfo(UsbDevice device) {
        JSONObject deviceInfo = new JSONObject();

        try {
            deviceInfo.put("deviceName", device.getDeviceName());
            deviceInfo.put("vendorId", device.getVendorId());
            deviceInfo.put("productId", device.getProductId());
            deviceInfo.put("deviceId", device.getDeviceId());
            deviceInfo.put("serialNumber", device.getSerialNumber());
        } catch (Exception ignored) {
        }

        return deviceInfo;
    }

    private void setConnectedDevice(UsbDevice device) {
        if (this.currentDevice != device) {
            this.currentDevice = device;
            if (this.currentDevice != null) {
                this.callback.connected();
            } else {
                this.callback.disconnected();
            }
        }
    }
}
