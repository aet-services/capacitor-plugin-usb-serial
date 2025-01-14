# capacitor-plugin-usb-serial

This plugin can be used for reading data from other device over the usb channel

## Install

```bash
npm install @aet-services/capacitor-plugin-usb-serial
npx cap sync
```

## API

<docgen-index>

* [`listDevices()`](#listdevices)
* [`openSerial(...)`](#openserial)
* [`closeSerial()`](#closeserial)
* [`writeSerial(...)`](#writeserial)
* [`addListener('attached' | 'detached', ...)`](#addlistenerattached--detached-)
* [`addListener('connected' | 'disconnected', ...)`](#addlistenerconnected--disconnected-)
* [`addListener('data', ...)`](#addlistenerdata-)
* [`addListener('log', ...)`](#addlistenerlog-)
* [`addListener('error', ...)`](#addlistenererror-)
* [Interfaces](#interfaces)
* [Enums](#enums)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### listDevices()

```typescript
listDevices() => Promise<{ devices: UsbDeviceInfo[]; }>
```

**Returns:** <code>Promise&lt;{ devices: UsbDeviceInfo[]; }&gt;</code>

--------------------


### openSerial(...)

```typescript
openSerial(options: UsbSerialOptions) => Promise<void>
```

| Param         | Type                                                          |
| ------------- | ------------------------------------------------------------- |
| **`options`** | <code><a href="#usbserialoptions">UsbSerialOptions</a></code> |

--------------------


### closeSerial()

```typescript
closeSerial() => Promise<void>
```

--------------------


### writeSerial(...)

```typescript
writeSerial(options: { data: string; }) => Promise<void>
```

| Param         | Type                           |
| ------------- | ------------------------------ |
| **`options`** | <code>{ data: string; }</code> |

--------------------


### addListener('attached' | 'detached', ...)

```typescript
addListener(eventName: 'attached' | 'detached', listenerFunc: (data: { device: UsbDeviceInfo; }) => void) => Promise<PluginListenerHandle> & PluginListenerHandle
```

| Param              | Type                                                                                    |
| ------------------ | --------------------------------------------------------------------------------------- |
| **`eventName`**    | <code>'attached' \| 'detached'</code>                                                   |
| **`listenerFunc`** | <code>(data: { device: <a href="#usbdeviceinfo">UsbDeviceInfo</a>; }) =&gt; void</code> |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt; & <a href="#pluginlistenerhandle">PluginListenerHandle</a></code>

--------------------


### addListener('connected' | 'disconnected', ...)

```typescript
addListener(eventName: 'connected' | 'disconnected', listenerFunc: () => void) => Promise<PluginListenerHandle> & PluginListenerHandle
```

| Param              | Type                                       |
| ------------------ | ------------------------------------------ |
| **`eventName`**    | <code>'connected' \| 'disconnected'</code> |
| **`listenerFunc`** | <code>() =&gt; void</code>                 |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt; & <a href="#pluginlistenerhandle">PluginListenerHandle</a></code>

--------------------


### addListener('data', ...)

```typescript
addListener(eventName: 'data', listenerFunc: (data: { data: string; }) => void) => Promise<PluginListenerHandle> & PluginListenerHandle
```

| Param              | Type                                              |
| ------------------ | ------------------------------------------------- |
| **`eventName`**    | <code>'data'</code>                               |
| **`listenerFunc`** | <code>(data: { data: string; }) =&gt; void</code> |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt; & <a href="#pluginlistenerhandle">PluginListenerHandle</a></code>

--------------------


### addListener('log', ...)

```typescript
addListener(eventName: 'log', listenerFunc: (data: { text: string; tag: string; }) => void) => Promise<PluginListenerHandle> & PluginListenerHandle
```

| Param              | Type                                                           |
| ------------------ | -------------------------------------------------------------- |
| **`eventName`**    | <code>'log'</code>                                             |
| **`listenerFunc`** | <code>(data: { text: string; tag: string; }) =&gt; void</code> |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt; & <a href="#pluginlistenerhandle">PluginListenerHandle</a></code>

--------------------


### addListener('error', ...)

```typescript
addListener(eventName: 'error', listenerFunc: (data: { error: string | UsbSerialErrorMsg; }) => void) => Promise<PluginListenerHandle> & PluginListenerHandle
```

| Param              | Type                                               |
| ------------------ | -------------------------------------------------- |
| **`eventName`**    | <code>'error'</code>                               |
| **`listenerFunc`** | <code>(data: { error: string; }) =&gt; void</code> |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt; & <a href="#pluginlistenerhandle">PluginListenerHandle</a></code>

--------------------


### Interfaces


#### UsbDeviceInfo

| Prop               | Type                        |
| ------------------ | --------------------------- |
| **`deviceName`**   | <code>string</code>         |
| **`vendorId`**     | <code>number</code>         |
| **`productId`**    | <code>number</code>         |
| **`deviceId`**     | <code>number</code>         |
| **`serialNumber`** | <code>string \| null</code> |


#### UsbSerialOptions

| Prop            | Type                 |
| --------------- | -------------------- |
| **`vendorId`**  | <code>number</code>  |
| **`productId`** | <code>number</code>  |
| **`portNum`**   | <code>number</code>  |
| **`baudRate`**  | <code>number</code>  |
| **`dataBits`**  | <code>number</code>  |
| **`stopBits`**  | <code>number</code>  |
| **`parity`**    | <code>number</code>  |
| **`dtr`**       | <code>boolean</code> |
| **`rts`**       | <code>boolean</code> |


#### PluginListenerHandle

| Prop         | Type                                      |
| ------------ | ----------------------------------------- |
| **`remove`** | <code>() =&gt; Promise&lt;void&gt;</code> |


### Enums


#### UsbSerialErrorMsg

| Members                                             | Value                                                         | Description                                                     |
| --------------------------------------------------- | ------------------------------------------------------------- | --------------------------------------------------------------- |
| **`ConnectionFailedDeviceNotFound`**                | <code>'connectionFailed:DeviceNotFound'</code>                | Connection failed because the device was not found.             |
| **`ConnectionFailedNoDriverForDevice`**             | <code>'connectionFailed:NoDriverForDevice'</code>             | Connection failed because no driver was found for the device.   |
| **`ConnectionFailedNoAvailablePorts`**              | <code>'connectionFailed:NoAvailablePorts'</code>              | Connection failed because no available ports were found.        |
| **`ConnectionFailedUsbConnectionPermissionDenied`** | <code>'connectionFailed:UsbConnectionPermissionDenied'</code> | Connection failed because USB connection permission was denied. |
| **`ConnectionFailedSerialOpenFailed`**              | <code>'connectionFailed:SerialOpenFailed'</code>              | Connection failed because serial open operation failed.         |
| **`WriteFailedDeviceNotConnected`**                 | <code>'writeFailed:DeviceNotConnected'</code>                 | Write operation failed because the device is not connected.     |
| **`WriteFailedEmptyData`**                          | <code>'writeFailed:EmptyData'</code>                          | Write operation failed because the data was empty.              |
| **`WriteFailedConnectionLost`**                     | <code>'writeFailed:ConnectionLost'</code>                     | Write operation failed because the connection was lost.         |
| **`ListFailedCannotListDevices`**                   | <code>'listFailed:CannotListDevices'</code>                   | Listing devices failed because devices cannot be listed.        |

</docgen-api>
