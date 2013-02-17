package com.teknonsys.twilio.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface DisconnectHandler extends EventHandler
  {
  void onDisconnect(DisconnectEvent evt);
  }