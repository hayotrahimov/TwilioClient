##Inheriting the module

The module can be downloaded here: [TwilioClient.jar](http://public.teknonsys.com/TwilioClient.jar). To use it, first inherit the module in your `.gwt.xml` file:

```xml 
  <inherits name="com.teknonsys.twilio.TwilioClient"/>
```

The TwilioClient module automatically injects the Twilio Client script, so it's it's not necessary to include the `<script></script>` tag in your `index.html` file.

##Basic usage

First, get the `Device` instance and call the `setup` method with your server-generated compatibility token. The module uses standard GWT Events and Handlers, making them usable with `@UiHandler` methods.

```java
    @UiField(provided=true) Device device=Device.getInstance();

    public void setupDevice()
      {
	  device.setup(token);
      }

    @UiHandler("device") void handleReady(DeviceReadyEvent evt)
      {
	  Connection conn=device.connect();
	  conn.addDisconnectHandler(this);
	  conn.disconnect();
      }
```

##Implemented methods

All methods from version 1.1 of the Twilio client are implemented.

###com.teknonsys.twilio.client.Device

The Device object is retrieved using the `getInstance()` method. It represents a "soft device”, the client that provides connections into Twilio. You need to invoke `.setup()` before using it.

```java
public void set(String token);
```

Initialize Device with a capability token (see [Twilio Client Capability Tokens](http://www.twilio.com/docs/client/capability-tokens)). This will activate the device and give it an identity and certain privileges based on the token passed. You should call this before anything else. If your token allows inbound client connections, the device will start listening for new connections when you call `.setup()`.

```java
public Connection connect();
public Connection connect(Map<String,String> params);
```

Attempt a new connection to a Twilio Application.

The optional params argument is a JavaScript object which will be url-encoded and passed to your application as POST/GET parameters. Your application should not assume that these parameters are safe since any user can call this function with whatever parameters she wants.

The `.connect()` method returns a Connection object. You can cancel the connection by calling its `.disconnect()` method.

```java
public void disconnectAll();
```

Terminate all active connections. This will trigger the disconnect event handler for each active connection. It will not prevent new incoming connections.

```java
public Device.Status getStatus();
```

Return the status of the device. The status will be one of the following: `OFFLINE`, `READY`, or `BUSY`.

`READY`

The device can receive incoming connections and attempt outgoing connections.

`OFFLINE`

The device is not connected and cannot receive incoming connections.

`BUSY`

The device is connected to the network, has an active connection, and cannot receive incoming connections or make outgoing connection attempts.

```java
public SoundsConfiguration getSoundsConfiguration();
```

The configuration object for the sounds for this device. These are the default sounds played for an incoming connection, initiating an outgoing connection, and disconnecting a connection.

###com.teknonsys.twilio.client.Conenction

A Connection object represents a specific connection into Twilio, such as a call. You never instantiate it directly, but it's often passed in event handlers and one is returned when you call `Device.getInstance().connect()`.

```java
public void accept();
```

Accept a pending connection.

```java
public void reject();
```

Reject a pending connection. This will cause a hangup to be issued from the client session to the dialing party. If multiple client sessions are active the pending connection will be rejected for all of them.

```java
public void disconnect();
```

Close this connection.

```java
public void mute();
```

Stop capturing audio from the microphone for this connection.

```java
public void unmute();
```

Resume capturing audio from the microphone for this connection.

```java
public void sendDigits(String digits);
```

Play DTMF tones. The digits parameter is a string and can contain the characters `0-9`, `#`, and `*`. If you're familiar with TwiML, you can think of the `sendDigits()` method as the `sendDigits` attribute in the `<Number>` Noun.

```java
public Connection.Status getStatus();
```

Return the status of this connection. The status will be one of the following strings: `PENDING`, `CONNECTING`, `OPEN` or `CLOSED`.

`PENDING`

The connection is incoming and hasn't yet been established.

`CONNECTING`

The connection is transitioning to open status.

`OPEN`

The connection has been established.

`CLOSED`

The connection has been disconnected.

```java
public CallParameters getCallParameters();
```

An object available on incoming connections that contains application parameters such as who is calling and what was dialed. These parameters are identical to those Twilio sends with a [Twilio Voice request for TwiML](http://www.twilio.com/docs/api/twiml/twilio_request#synchronous).