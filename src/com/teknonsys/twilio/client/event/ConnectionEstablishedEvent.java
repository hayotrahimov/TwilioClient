package com.teknonsys.twilio.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.teknonsys.twilio.client.Connection;

public class ConnectionEstablishedEvent extends GwtEvent<ConnectionEstablishedHandler>
  {
  private final Connection connection;
  public ConnectionEstablishedEvent(Connection connection) {this.connection=connection;}
  public Connection getConnection() {return connection;}
  
  private static GwtEvent.Type<ConnectionEstablishedHandler> TYPE;
  public static GwtEvent.Type<ConnectionEstablishedHandler> getType()
    {
    if(TYPE==null)
      TYPE=new GwtEvent.Type<ConnectionEstablishedHandler>();
    return TYPE;
    }
  
  public @Override GwtEvent.Type<ConnectionEstablishedHandler> getAssociatedType()
    {return TYPE;}
  public @Override void dispatch(ConnectionEstablishedHandler handler)
    {handler.onConnectionEstablished(this);}
  }