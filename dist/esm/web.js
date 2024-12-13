/* eslint-disable @typescript-eslint/no-explicit-any */
import { WebPlugin } from '@capacitor/core';
export class UsbSerialWeb extends WebPlugin {
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
//# sourceMappingURL=web.js.map