package com.teknonsys.twilio.client.event;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.shared.GwtEvent;
import com.teknonsys.twilio.client.Connection;

public class ErrorEvent extends GwtEvent<ErrorHandler>
  {
  private final String message;
  private final int code;
  private final JavaScriptObject info;
  private final Connection connection;
  
  public ErrorEvent(String message,int code,JavaScriptObject info,
    Connection connection)
    {
    this.message=message;
    this.code=code;
    this.info=info;
    this.connection=connection;
    }
  
  public String getMessage() {return message;}
  public int getCode() {return code;}
  public JavaScriptObject getInfo() {return info;}
  public Connection getConnection() {return connection;}
  
  private static Type<ErrorHandler> TYPE;
  public static Type<ErrorHandler> getType()
    {
    if(TYPE==null)
      TYPE=new Type<ErrorHandler>();
    return TYPE;
    }
  
  public @Override Type<ErrorHandler> getAssociatedType()
    {return TYPE;}
  public @Override void dispatch(ErrorHandler handler)
    {handler.onError(this);}
  }