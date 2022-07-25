package com.codestates.springsecurity.config;

import com.codestates.springsecurity.filter.CustomFilter;
import com.codestates.springsecurity.filter.CustomFilter2;
import com.codestates.springsecurity.filter.CustomFilteringProxy;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<CustomFilter> customFilterFilterRegistrationBean() {
        FilterRegistrationBean<CustomFilter> filterRegistrationBean = new FilterRegistrationBean<>(new CustomFilter());

        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/hello");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<CustomFilter2> customFilter2FilterRegistrationBean() {
        FilterRegistrationBean<CustomFilter2> filterRegistrationBean = new FilterRegistrationBean<>(new CustomFilter2());

        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.addUrlPatterns("/hello");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<CustomFilteringProxy> customFilteringProxyFilterRegistrationBean() {
        FilterRegistrationBean<CustomFilteringProxy> filterRegistrationBean = new FilterRegistrationBean<>(new CustomFilteringProxy());
        filterRegistrationBean.setOrder(3);
        filterRegistrationBean.addUrlPatterns("/proxy");
        return filterRegistrationBean;
    }

}
