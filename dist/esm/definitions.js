/**
 * Enum representing USB serial error messages.
 */
export var UsbSerialErrorMsg;
(function (UsbSerialErrorMsg) {
    /** Connection failed because the device was not found. */
    UsbSerialErrorMsg["ConnectionFailedDeviceNotFound"] = "connectionFailed:DeviceNotFound";
    /** Connection failed because no driver was found for the device. */
    UsbSerialErrorMsg["ConnectionFailedNoDriverForDevice"] = "connectionFailed:NoDriverForDevice";
    /** Connection failed because no available ports were found. */
    UsbSerialErrorMsg["ConnectionFailedNoAvailablePorts"] = "connectionFailed:NoAvailablePorts";
    /** Connection failed because USB connection permission was denied. */
    UsbSerialErrorMsg["ConnectionFailedUsbConnectionPermissionDenied"] = "connectionFailed:UsbConnectionPermissionDenied";
    /** Connection failed because serial open operation failed. */
    UsbSerialErrorMsg["ConnectionFailedSerialOpenFailed"] = "connectionFailed:SerialOpenFailed";
    /** Write operation failed because the device is not connected. */
    UsbSerialErrorMsg["WriteFailedDeviceNotConnected"] = "writeFailed:DeviceNotConnected";
    /** Write operation failed because the data was empty. */
    UsbSerialErrorMsg["WriteFailedEmptyData"] = "writeFailed:EmptyData";
    /** Write operation failed because the connection was lost. */
    UsbSerialErrorMsg["WriteFailedConnectionLost"] = "writeFailed:ConnectionLost";
    /** Listing devices failed because devices cannot be listed. */
    UsbSerialErrorMsg["ListFailedCannotListDevices"] = "listFailed:CannotListDevices";
})(UsbSerialErrorMsg || (UsbSerialErrorMsg = {}));
//# sourceMappingURL=definitions.js.map