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