import { PluginListenerHandle } from '@capacitor/core';

export interface UsbSerialOptions {
  vendorId: string;
  productId: string;
  portNum?: number;
  baudRate?: number;
  dataBits?: number;
  stopBits?: number;
  parity?: number;
  dtr?: boolean;
  rts?: boolean;
}

export interface UsbDeviceInfo {
  deviceName: string;
  vendorId: string;
  productId: string;
  deviceId: number;
  serialNumber: string | null;
}

export interface UsbSerialPlugin {
  listDevices(): Promise<{ devices: UsbDeviceInfo[] }>;
  openSerial(options: UsbSerialOptions): Promise<void>;
  closeSerial(): Promise<void>;
  writeSerial(options: { data: string }): Promise<void>;
  addListener(
    eventName: 'attached' | 'detached',
    listenerFunc: (device: UsbDeviceInfo) => void,
  ): Promise<PluginListenerHandle> & PluginListenerHandle;
  addListener(
    eventName: 'connected' | 'disconnected',
    listenerFunc: () => void,
  ): Promise<PluginListenerHandle> & PluginListenerHandle;
  addListener(
    eventName: 'data',
    listenerFunc: (data: { data: string }) => void,
  ): Promise<PluginListenerHandle> & PluginListenerHandle;
  addListener(
    eventName: 'log',
    listenerFunc: (data: { text: string; tag: string }) => void,
  ): Promise<PluginListenerHandle> & PluginListenerHandle;
  addListener(
    eventName: 'error',
    listenerFunc: (data: { error: string }) => void,
  ): Promise<PluginListenerHandle> & PluginListenerHandle;
}
