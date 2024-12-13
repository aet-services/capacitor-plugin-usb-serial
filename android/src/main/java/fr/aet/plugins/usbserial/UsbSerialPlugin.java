package fr.aet.plugins.usbserial;

import android.hardware.usb.UsbDevice;

import org.json.JSONArray;
import org.json.JSONObject;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "UsbSerial")
public class UsbSerialPlugin extends Plugin implements Callback {
    private UsbSerial usbSerial;

    @Override
    public void load() {
        super.load();
        usbSerial = new UsbSerial(getContext(), this);
    }

    @PluginMethod
    public void listDevices(PluginCall call) {
        try {
            JSObject jsObject = new JSObject();
            JSONArray devices = usbSerial.listDevices();
            jsObject.put("devices", devices);
            call.resolve(jsObject);
        } catch (Exception e) {
            error(new Error(e.toString()));
            call.reject(extractErrorMessage(e.toString()));
        }
    }

    @PluginMethod
    public void openSerial(PluginCall call) {
        try {
            UsbSerialOptions settings = new UsbSerialOptions();

            if (call.hasOption("vendorId"))
                settings.vendorId = call.getInt("vendorId");

            if (call.hasOption("productId"))
                settings.productId = call.getInt("productId");

            if (call.hasOption("portNum"))
                settings.portNum = call.getInt("portNum");

            if (call.hasOption("baudRate"))
                settings.baudRate = call.getInt("baudRate");

            if (call.hasOption("dataBits"))
                settings.dataBits = call.getInt("dataBits");

            if (call.hasOption("stopBits"))
                settings.stopBits = call.getInt("stopBits");

            if (call.hasOption("parity"))
                settings.parity = call.getInt("parity");

            if (call.hasOption("dtr"))
                settings.dtr = call.getBoolean("dtr");

            if (call.hasOption("rts"))
                settings.rts = call.getBoolean("rts");

            usbSerial.openSerial(settings);
            call.resolve(new JSObject());
        } catch (Exception e) {
            error(new Error(e.toString()));
            call.reject(extractErrorMessage(e.toString()));
        }
    }

    @PluginMethod
    public void closeSerial(PluginCall call) {
        try {
            usbSerial.closeSerial();
            call.resolve(new JSObject());
        } catch (Exception e) {
            error(new Error(e.toString()));
            call.reject(extractErrorMessage(e.toString()));
        }
    }

    @PluginMethod
    public void writeSerial(PluginCall call) {
        try {
            String data = call.hasOption("data") ? call.getString("data") : "";
            usbSerial.writeSerial(data);
            call.resolve(new JSObject());
        } catch (Exception e) {
            error(new Error(e.toString()));
            call.reject(extractErrorMessage(e.toString()));
        }
    }

    @Override
    public void connected() {
        notifyListeners("connected", new JSObject());
    }

    @Override
    public void disconnected() {
        notifyListeners("disconnected", new JSObject());
    }

    @Override
    public void usbDeviceAttached(JSONObject deviceInfo) {
        JSObject jsObject = new JSObject();
        jsObject.put("device", deviceInfo);
        notifyListeners("attached", jsObject);
    }

    @Override
    public void usbDeviceDetached(JSONObject deviceInfo) {
        JSObject jsObject = new JSObject();
        jsObject.put("device", deviceInfo);
        notifyListeners("detached", jsObject);
    }

    @Override
    public void receivedData(String Data) {
        JSObject ret = new JSObject();
        ret.put("data", Data);
        notifyListeners("data", ret);
    }

    @Override
    public void log(String tag, String text) {
        JSObject ret = new JSObject();
        ret.put("tag", tag);
        ret.put("text", text);
        notifyListeners("log", ret);
    }

    @Override
    public void error(Error error) {
        JSObject ret = new JSObject();
        ret.put("error", extractErrorMessage(error.toString()));
        notifyListeners("error", ret);
    }

    private String extractErrorMessage(String message) {
        String regex = "java\\.[\\w_-]+\\.[\\w_-]+:";
        return message.replaceAll(regex, "").trim();
    }
}
