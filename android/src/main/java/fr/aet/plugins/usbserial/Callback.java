package fr.aet.plugins.usbserial;

import org.json.JSONObject;

import android.hardware.usb.UsbDevice;

public interface Callback {
    void usbDeviceAttached(JSONObject deviceInfo);
    void usbDeviceDetached(JSONObject deviceInfo);
    void connected();
    void disconnected();
    void receivedData(String data);
    void error(Error error);
    void log(String tag, String text);
}


