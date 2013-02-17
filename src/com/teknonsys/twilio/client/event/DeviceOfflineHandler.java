package com.teknonsys.twilio.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface DeviceOfflineHandler extends EventHandler
  {
  void onDeviceOffline(DeviceOfflineEvent evt);
  }