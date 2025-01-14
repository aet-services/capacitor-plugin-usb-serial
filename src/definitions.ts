import { PluginListenerHandle } from '@capacitor/core';

export interface UsbSerialOptions {
  vendorId: number;
  productId: number;
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
  vendorId: number;
  productId: number;
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
    listenerFunc: (data: { device: UsbDeviceInfo }) => void,
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
    listenerFunc: (data: { error: string | UsbSerialErrorMsg }) => void,
  ): Promise<PluginListenerHandle> & PluginListenerHandle;
}

/**
 * Enum representing USB serial error messages.
 */
export enum UsbSerialErrorMsg {
  /** Connection failed because the device was not found. */
  ConnectionFailedDeviceNotFound = 'connectionFailed:DeviceNotFound',
  /** Connection failed because no driver was found for the device. */
  ConnectionFailedNoDriverForDevice = 'connectionFailed:NoDriverForDevice',
  /** Connection failed because no available ports were found. */
  ConnectionFailedNoAvailablePorts = 'connectionFailed:NoAvailablePorts',
  /** Connection failed because USB connection permission was denied. */
  ConnectionFailedUsbConnectionPermissionDenied = 'connectionFailed:UsbConnectionPermissionDenied',
  /** Connection failed because serial open operation failed. */
  ConnectionFailedSerialOpenFailed = 'connectionFailed:SerialOpenFailed',
  /** Write operation failed because the device is not connected. */
  WriteFailedDeviceNotConnected = 'writeFailed:DeviceNotConnected',
  /** Write operation failed because the data was empty. */
  WriteFailedEmptyData = 'writeFailed:EmptyData',
  /** Write operation failed because the connection was lost. */
  WriteFailedConnectionLost = 'writeFailed:ConnectionLost',
  /** Listing devices failed because devices cannot be listed. */
  ListFailedCannotListDevices = 'listFailed:CannotListDevices',
}
