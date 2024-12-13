'use strict';

var core = require('@capacitor/core');

/**
 * Enum representing USB serial error messages.
 */
exports.UsbSerialErrorMsg = void 0;
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
})(exports.UsbSerialErrorMsg || (exports.UsbSerialErrorMsg = {}));

const UsbSerial = core.registerPlugin('UsbSerial', {
    web: () => Promise.resolve().then(function () { return web; }).then((m) => new m.UsbSerialWeb()),
});

/* eslint-disable @typescript-eslint/no-explicit-any */
class UsbSerialWeb extends core.WebPlugin {
    listDevices() {
        throw new Error('Method not implemented.');
    }
    openSerial(options) {
        throw new Error('Method not implemented: ' + JSON.stringify(options));
    }
    closeSerial() {
        throw new Error('Method not implemented.');
    }
    writeSerial(options) {
        throw new Error('Method not implemented: ' + JSON.stringify(options));
    }
    addListener(eventName, listenerFunc) {
        listenerFunc({});
        return Promise.reject(`Method '${eventName}' not implemented.`);
    }
}

var web = /*#__PURE__*/Object.freeze({
    __proto__: null,
    UsbSerialWeb: UsbSerialWeb
});

exports.UsbSerial = UsbSerial;
//# sourceMappingURL=plugin.cjs.js.map
