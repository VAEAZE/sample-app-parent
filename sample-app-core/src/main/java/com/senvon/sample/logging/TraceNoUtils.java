package com.senvon.sample.logging;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

/**
 * description: 
 */
public class TraceNoUtils {
    /**
     * 产生新的traceNo,并放到MDC中
     */
    public static void newTraceNo() {
        MDC.put("traceNo", RandomStringUtils.randomAlphanumeric(12));
    }

    /**
     * 产生新的traceNo,并放到MDC中<br>
     * parentTranceNo-newTraceNo
     */
    public static void newTraceNo(String parentTranceNo) {
        if (StringUtils.isNotEmpty(parentTranceNo)) {
            MDC.put("traceNo", parentTranceNo + "-" + RandomStringUtils.randomAlphanumeric(6));
        } else {
            newTraceNo();
        }
    }

    /**
     * 获得MDC中的traceNo
     * @return
     */
    public static String getTraceNo() {
        return MDC.get("traceNo");
    }

    /**
     * 清除MDC中的traceNo
     */
    public static void clearTraceNo() {
        MDC.remove("traceNo");
    }
}
