package com.teknonsys.twilio.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.teknonsys.twilio.client.Connection;

public class ConnectionCanceledEvent extends GwtEvent<ConnectionCanceledHandler>
  {
  private final Connection connection;
  public ConnectionCanceledEvent(Connection connection) {this.connection=connection;}
  public Connection getConnection() {return connection;}
  
  private static GwtEvent.Type<ConnectionCanceledHandler> TYPE;
  public static GwtEvent.Type<ConnectionCanceledHandler> getType()
    {
    if(TYPE==null)
      TYPE=new GwtEvent.Type<ConnectionCanceledHandler>();
    return TYPE;
    }
  
  public @Override GwtEvent.Type<ConnectionCanceledHandler> getAssociatedType()
    {return TYPE;}
  public @Override void dispatch(ConnectionCanceledHandler handler)
    {handler.onConnectionCanceled(this);}
  }