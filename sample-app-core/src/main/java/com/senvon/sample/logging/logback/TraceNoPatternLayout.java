package com.senvon.sample.logging.logback;

import org.apache.commons.lang3.StringUtils;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * description: 在每行加上traceNo
 */
public class TraceNoPatternLayout extends PatternLayout {

    @Override
    public String doLayout(ILoggingEvent event) {
        this.getEffectiveConverterMap().put("traceNo", TraceNoConverter.class.getName());
    	String s = super.doLayout(event);
        
//        System.out.println(event.getMDCPropertyMap());
        
        String traceNo = event.getMDCPropertyMap().get("traceNo");
        if (s != null && event.getThrowableProxy() != null && traceNo != null) {
            // 有异常信息的，每行都打印跟踪号
            if (s.endsWith("\n")) {
                s = StringUtils.replace(s.substring(0, s.length() - 1), "\n", "\n[" + traceNo + "]\t") + "\n";
            } else {
                s = StringUtils.replace(s, "\n", "\n[" + traceNo + "]\t");
            }
        }
        return s;
    }
}
