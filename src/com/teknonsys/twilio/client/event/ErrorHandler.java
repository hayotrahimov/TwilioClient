package com.teknonsys.twilio.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface ErrorHandler extends EventHandler
  {
  void onError(ErrorEvent evt);
  }