package com.teknonsys.twilio.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface DeviceReadyHandler extends EventHandler
  {
  void onDeviceReady(DeviceReadyEvent evt);
  }