import { WebPlugin } from '@capacitor/core';
import type { UsbSerialOptions, UsbSerialPlugin, UsbDeviceInfo } from './definitions';
export declare class UsbSerialWeb extends WebPlugin implements UsbSerialPlugin {
    listDevices(): Promise<{
        devices: UsbDeviceInfo[];
    }>;
    openSerial(options: UsbSerialOptions): Promise<void>;
    closeSerial(): Promise<void>;
    writeSerial(options: {
        data: string;
    }): Promise<void>;
    addListener(eventName: 'attached' | 'detached' | 'connected' | 'disconnected' | 'data' | 'error' | 'log', listenerFunc: (data: any) => void): any;
}
