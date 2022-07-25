package com.codestates.springsecurity.filter;

import javax.servlet.*;
import java.io.IOException;

public class CustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(super.toString() + "is initiated");
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("first filter is working");
        System.out.println("here are some information");
        System.out.println("====================================");
        request.getAttributeNames().asIterator().forEachRemaining(System.out::println);
        System.out.println("====================================");

        int port = request.getLocalPort();
        String protocol = request.getProtocol();
        System.out.println("port : " + port + "/ protocol : " + protocol);
        System.out.println("====================================");
        System.out.println("removing attributes");
        request.getAttributeNames().asIterator().forEachRemaining(request::removeAttribute);
        System.out.println("====================================");
        System.out.println("set new attributes");

        request.setAttribute("my name is", "thom");
        request.setAttribute("today's lunch is", "sandwich as always");

        chain.doFilter(request, response);

        System.out.println("====================================");
        System.out.println("first filter is about to be dead");
        System.out.println("first filter calls for some response information before it dies");
        String contentType = response.getContentType();
        String charset = response.getCharacterEncoding();
        System.out.println("content type : " + contentType);
        System.out.println("charset : " + charset);
        System.out.println("====================================");

    }
}
