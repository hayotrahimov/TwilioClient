package com.teknonsys.twilio.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.shared.*;
import com.teknonsys.twilio.client.event.*;

public final class Connection implements HasConnectionHandlers
  {
  public enum Status {PENDING,CONNECTING,OPEN,CLOSED}
  
  private ConnectionJSO jso;
  private HandlerManager handlerManager;
  
  Connection(JavaScriptObject jso) {this.jso=jso.cast();}
  
  public void accept() {jso.accept();}
  public void reject() {jso.reject();}
  public void disconnect() {jso.disconnect();}
  public void mute() {jso.mute();}
  public void unmute() {jso.unmute();}
  public void sendDigits(String digits) {jso.sendDigits(digits);}
  public Status getStatus() {return Status.valueOf(jso.getStatus().toUpperCase());}
  public CallParameters getCallParameters() {return jso.getCallParameters();}
  
  private HandlerManager ensureHandlers()
    {
    if(handlerManager!=null)
      return handlerManager;
    registerEvents(jso);
    return handlerManager=new HandlerManager(this);
    }
  
  private native void registerEvents(ConnectionJSO jso)
    /*-{
    var self=this;
    jso.disconnect(function(connection)
      {
      self.@com.teknonsys.twilio.client.Connection::fireDisconnectEvent()();
      });
    jso.accept(function(connection)
      {
      self.@com.teknonsys.twilio.client.Connection::fireAcceptEvent()();
      });
    $wnd.Twilio.Device.error(function(err)
      {
      self.@com.teknonsys.twilio.client.Connection::fireErrorEvent(Ljava/lang/String;ILcom/google/gwt/core/client/JavaScriptObject;)
        (err.message.message,err.code,err.info);
      });
    }-*/;
  
  private void fireDisconnectEvent() {fireEvent(new DisconnectEvent(this));}
  private void fireAcceptEvent() {fireEvent(new AcceptEvent(this));}
  private void fireErrorEvent(String message,int code,JavaScriptObject info)
    {fireEvent(new ErrorEvent(message,code,info,this));}
  
  public HandlerRegistration addDisconnectHandler(DisconnectHandler handler)
    {return addHandler(DisconnectEvent.getType(),handler);}
  public HandlerRegistration addAcceptHandler(AcceptHandler handler)
    {return addHandler(AcceptEvent.getType(),handler);}
  public HandlerRegistration addErrorHandler(ErrorHandler handler)
    {return addHandler(ErrorEvent.getType(),handler);}
  
  public void fireEvent(GwtEvent<?> event)
    {if(handlerManager!=null) handlerManager.fireEvent(event);}
  private <H extends EventHandler> HandlerRegistration
    addHandler(GwtEvent.Type<H> type,H handler)
    {return ensureHandlers().addHandler(type,handler);}
  
  private static final class ConnectionJSO extends JavaScriptObject
    {
    protected ConnectionJSO() {}
    private native void accept() /*-{this.accept();}-*/;
    private native void reject() /*-{this.reject();}-*/;
    private native void disconnect() /*-{this.disconnect();}-*/;
    private native void mute() /*-{this.mute();}-*/;
    private native void unmute() /*-{this.unmute();}-*/;
    private native void sendDigits(String digits) /*-{this.sendDigits(digits);}-*/;
    private native String getStatus() /*-{return this.status();}-*/;
    private native CallParameters getCallParameters()
      /*-{
      if(this.parameters==undefined)
        return null;
      return this.parameters;
      }-*/;
    }
  }