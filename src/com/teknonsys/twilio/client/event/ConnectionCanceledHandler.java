package com.teknonsys.twilio.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface ConnectionCanceledHandler extends EventHandler
  {
  void onConnectionCanceled(ConnectionCanceledEvent evt);
  }