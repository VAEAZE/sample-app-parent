package com.senvon.sample.logging.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.senvon.sample.logging.TraceNoUtils;

/**
 * description: 
 * time : 2015年5月7日 上午9:56:21
 */
public class TraceNoFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        TraceNoUtils.newTraceNo();
        try {
            chain.doFilter(request, response);
        } finally {
            TraceNoUtils.clearTraceNo();
        }
    }

    @Override
    public void destroy() {

    }

}
