package com.teknonsys.twilio.client.event;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public interface HasConnectionHandlers extends HasHandlers
  {
  HandlerRegistration addDisconnectHandler(DisconnectHandler handler);
  HandlerRegistration addAcceptHandler(AcceptHandler handler);
  HandlerRegistration addErrorHandler(ErrorHandler handler);
  }