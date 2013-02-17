package com.teknonsys.twilio.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.teknonsys.twilio.client.Connection;

public class AcceptEvent extends GwtEvent<AcceptHandler>
  {
  private final Connection connection;
  public AcceptEvent(Connection connection) {this.connection=connection;}
  public Connection getConnection() {return connection;}
  
  private static GwtEvent.Type<AcceptHandler> TYPE;
  public static GwtEvent.Type<AcceptHandler> getType()
    {
    if(TYPE==null)
      TYPE=new GwtEvent.Type<AcceptHandler>();
    return TYPE;
    }
  
  public @Override GwtEvent.Type<AcceptHandler> getAssociatedType()
    {return TYPE;}
  public @Override void dispatch(AcceptHandler handler)
    {handler.onAccept(this);}
  }