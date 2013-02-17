package com.teknonsys.twilio.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface IncomingConnectionHandler extends EventHandler
  {
  void onIncomingConnection(IncomingConnectionEvent evt);
  }