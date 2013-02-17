package com.teknonsys.twilio.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface PresenceHandler extends EventHandler
  {
  void onPresenceChanged(PresenceEvent evt);
  }