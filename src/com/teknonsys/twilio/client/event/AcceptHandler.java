package com.teknonsys.twilio.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface AcceptHandler extends EventHandler
  {
  void onAccept(AcceptEvent evt);
  }