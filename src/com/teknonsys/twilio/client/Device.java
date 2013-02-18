package com.teknonsys.twilio.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.shared.*;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.teknonsys.twilio.client.event.*;
import java.util.Map;

public class Device implements HasDeviceHandlers
  {
  public enum Status {OFFLINE,READY,BUSY}
  
  private static final Device instance=new Device();
  private HandlerManager handlerManager;
  private SoundsConfiguration sounds;
  
  private Device() {}
  public static Device getInstance() {return instance;}
  
  public native void setup(String token)
    /*-{$wnd.Twilio.Device.setup(token);}-*/;
  public Connection connect(Map<String,String> params)
    {return new Connection(connect(convert(params)));}
  private native JavaScriptObject connect(JavaScriptObject params)
    /*-{return $wnd.Twilio.Device.connect(params);}-*/;
  public Connection connect() {return new Connection(connectNoParams());}
  private native JavaScriptObject connectNoParams()
    /*-{return $wnd.Twilio.Device.connect();}-*/;
  public native void disconnectAll()
    /*-{$wnd.Twilio.Device.disconnectAll();}-*/;
  public Status getStatus() {return Status.valueOf(getStatusJS().toUpperCase());}
  private native String getStatusJS() /*-{return $wnd.Twilio.Device.status()}-*/;
  public SoundsConfiguration getSoundsConfiguration()
    {return sounds==null?sounds=JavaScriptObject.createObject().cast():sounds;}
  
  static JavaScriptObject convert(Map<String,String> map)
    {
    JSONObject obj=new JSONObject();
    if(map==null)
      return obj.getJavaScriptObject();
    for(String key:map.keySet())
      obj.put(key,new JSONString(map.get(key)));
    return obj.getJavaScriptObject();
    }
  
  private HandlerManager ensureHandlers()
    {
    if(handlerManager!=null)
      return handlerManager;
    registerEvents();
    return handlerManager=new HandlerManager(this);
    }
  
  private native void registerEvents()
    /*-{
    var self=this;
    $wnd.Twilio.Device.ready(function(device)
      {
      self.@com.teknonsys.twilio.client.Device::fireDeviceReadyEvent()();
      });
    $wnd.Twilio.Device.offline(function(device)
      {
      self.@com.teknonsys.twilio.client.Device::fireDeviceOfflineEvent()();
      });
    $wnd.Twilio.Device.incoming(function(connection)
      {
      self.@com.teknonsys.twilio.client.Device::fireIncomingConnectionEvent(Lcom/google/gwt/core/client/JavaScriptObject;)(connection);
      });
    $wnd.Twilio.Device.cancel(function(connection)
      {
      self.@com.teknonsys.twilio.client.Device::fireConnectionCanceledEvent(Lcom/google/gwt/core/client/JavaScriptObject;)(connection);
      });
    $wnd.Twilio.Device.connect(function(connection)
      {
      self.@com.teknonsys.twilio.client.Device::fireConnectionEstablishedEvent(Lcom/google/gwt/core/client/JavaScriptObject;)(connection);
      });
    $wnd.Twilio.Device.disconnect(function(connection)
      {
      self.@com.teknonsys.twilio.client.Device::fireDisconnectEvent(Lcom/google/gwt/core/client/JavaScriptObject;)(connection);
      });
    $wnd.Twilio.Device.presence(function(pe)
      {
      self.@com.teknonsys.twilio.client.Device::firePresenceEvent(Ljava/lang/String;Z)(pe.from,pe.available);
      });
    $wnd.Twilio.Device.error(function(err)
      {
      self.@com.teknonsys.twilio.client.Device::fireErrorEvent(Ljava/lang/String;ILcom/google/gwt/core/client/JavaScriptObject;Lcom/google/gwt/core/client/JavaScriptObject;)
        (err.message.message,err.code,err.info,err.connection);
      });
    }-*/;
  
  private void fireDeviceReadyEvent() {fireEvent(new DeviceReadyEvent());}
  private void fireDeviceOfflineEvent() {fireEvent(new DeviceOfflineEvent());}
  private void fireIncomingConnectionEvent(JavaScriptObject jso)
    {fireEvent(new IncomingConnectionEvent(new Connection(jso)));}
  private void fireConnectionCanceledEvent(JavaScriptObject jso)
    {fireEvent(new ConnectionCanceledEvent(new Connection(jso)));}
  private void fireConnectionEstablishedEvent(JavaScriptObject jso)
    {fireEvent(new ConnectionEstablishedEvent(new Connection(jso)));}
  private void fireDisconnectEvent(JavaScriptObject jso)
    {fireEvent(new DisconnectEvent(new Connection(jso)));}
  private void firePresenceEvent(String from,boolean available)
    {fireEvent(new PresenceEvent(from,available));}
  private void fireErrorEvent(String message,int code,JavaScriptObject info,
    JavaScriptObject connection)
    {fireEvent(new ErrorEvent(message,code,info,new Connection(connection)));}
  
  public HandlerRegistration addDeviceReadyHandler(DeviceReadyHandler handler)
    {return addHandler(DeviceReadyEvent.getType(),handler);}
  public HandlerRegistration addDeviceOfflineHandler(DeviceOfflineHandler handler)
    {return addHandler(DeviceOfflineEvent.getType(),handler);}
  public HandlerRegistration addIncomingConnectionHandler(IncomingConnectionHandler handler)
    {return addHandler(IncomingConnectionEvent.getType(),handler);}
  public HandlerRegistration addConnectionCanceledHandler(ConnectionCanceledHandler handler)
    {return addHandler(ConnectionCanceledEvent.getType(),handler);}
  public HandlerRegistration addConnectionEstablishedHandler(ConnectionEstablishedHandler handler)
    {return addHandler(ConnectionEstablishedEvent.getType(),handler);}
  public HandlerRegistration addDisconnectHandler(DisconnectHandler handler)
    {return addHandler(DisconnectEvent.getType(),handler);}
  public HandlerRegistration addPresenceHandler(PresenceHandler handler)
    {return addHandler(PresenceEvent.getType(),handler);}
  public HandlerRegistration addErrorHandler(ErrorHandler handler)
    {return addHandler(ErrorEvent.getType(),handler);}
  
  public void fireEvent(GwtEvent<?> event)
    {if(handlerManager!=null) handlerManager.fireEvent(event);}
  private <H extends EventHandler> HandlerRegistration
    addHandler(GwtEvent.Type<H> type,H handler)
    {return ensureHandlers().addHandler(type,handler);}
  }