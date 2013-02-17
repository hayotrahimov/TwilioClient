package com.teknonsys.twilio.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.teknonsys.twilio.client.Connection;

public class IncomingConnectionEvent extends GwtEvent<IncomingConnectionHandler>
  {
  private final Connection connection;
  public IncomingConnectionEvent(Connection connection) {this.connection=connection;}
  public Connection getConnection() {return connection;}
  
  private static GwtEvent.Type<IncomingConnectionHandler> TYPE;
  public static GwtEvent.Type<IncomingConnectionHandler> getType()
    {
    if(TYPE==null)
      TYPE=new GwtEvent.Type<IncomingConnectionHandler>();
    return TYPE;
    }
  
  public @Override GwtEvent.Type<IncomingConnectionHandler> getAssociatedType()
    {return TYPE;}
  public @Override void dispatch(IncomingConnectionHandler handler)
    {handler.onIncomingConnection(this);}
  }