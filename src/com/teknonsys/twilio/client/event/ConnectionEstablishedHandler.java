package com.teknonsys.twilio.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface ConnectionEstablishedHandler extends EventHandler
  {
  void onConnectionEstablished(ConnectionEstablishedEvent evt);
  }