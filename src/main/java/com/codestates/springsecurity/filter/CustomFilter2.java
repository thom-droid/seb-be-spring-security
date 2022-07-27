package com.codestates.springsecurity.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
public class CustomFilter2 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        log.info("filter 2 initiated");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("filter 2");
        System.out.println("====================================");

        System.out.println("get some attributes");

        request.getAttributeNames().asIterator().forEachRemaining((attribute) -> {
            System.out.println("name : " + attribute);
            System.out.println(" attribute : " + request.getAttribute(attribute));
        });

        System.out.println("====================================");
        System.out.println("setting contentType as UTF-16 and json");
        response.setContentType("application/json;charset=UTF-16");
        response.setCharacterEncoding("UTF-16");
        chain.doFilter(request, response);
        System.out.println("====================================");
        System.out.println("second filter is about to be dead");
        System.out.println("second filter do some response setting before it dies");
        System.out.println("====================================");

    }
}
