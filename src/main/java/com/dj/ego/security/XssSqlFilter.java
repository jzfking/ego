package com.dj.ego.security;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author tomsun28
 * @date 21:24 2018/4/16
 */
@WebFilter(filterName = "xssFilter", urlPatterns = "/*", asyncSupported = true)
public class XssSqlFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        XssSqlHttpServletRequestWrapper xssSqlHttpServletRequestWrapper = new XssSqlHttpServletRequestWrapper((HttpServletRequest) servletRequest);
        filterChain.doFilter(xssSqlHttpServletRequestWrapper, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
