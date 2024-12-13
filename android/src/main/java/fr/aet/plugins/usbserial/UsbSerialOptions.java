package fr.aet.plugins.usbserial;

import com.hoho.android.usbserial.driver.UsbSerialPort;

public class UsbSerialOptions {
    public int vendorId = 0;
    public int productId = 0;
    public int portNum = 0;
    public int baudRate = 115200;
    public int dataBits = UsbSerialPort.DATABITS_8;
    public int stopBits = UsbSerialPort.STOPBITS_1;
    public int parity = UsbSerialPort.PARITY_NONE;
    public boolean dtr = false;
    public boolean rts = false;
}
