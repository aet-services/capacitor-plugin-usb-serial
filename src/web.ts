/* eslint-disable @typescript-eslint/no-explicit-any */
import { WebPlugin } from '@capacitor/core';

import type { UsbSerialOptions, UsbSerialPlugin, UsbDeviceInfo } from './definitions';

export class UsbSerialWeb extends WebPlugin implements UsbSerialPlugin {
  listDevices(): Promise<{ devices: UsbDeviceInfo[] }> {
    throw new Error('Method not implemented.');
  }
  openSerial(options: UsbSerialOptions): Promise<void> {
    throw new Error('Method not implemented: ' + JSON.stringify(options));
  }
  closeSerial(): Promise<void> {
    throw new Error('Method not implemented.');
  }
  writeSerial(options: { data: string }): Promise<void> {
    throw new Error('Method not implemented: ' + JSON.stringify(options));
  }
  addListener(
    eventName: 'attached' | 'detached' | 'connected' | 'disconnected' | 'data' | 'error' | 'log',
    listenerFunc: (data: any) => void,
  ) {
    listenerFunc({});
    return Promise.reject(`Method '${eventName}' not implemented.`) as any;
  }
}
