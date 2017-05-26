package com.senvon.sample.logging.logback;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class TraceNoConverter extends ClassicConverter {

	@Override
	public String convert(ILoggingEvent event) {
		return event.getMDCPropertyMap().get("traceNo");
	}

}
