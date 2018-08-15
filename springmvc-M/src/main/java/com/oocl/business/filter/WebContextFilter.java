//package com.oocl.business.filter;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.oocl.business.util.PropertiesUtil;
//
//@WebFilter(filterName = "WebContextFilter")
//public class WebContextFilter implements Filter {
//
//    public void destroy() {
//
//    }
//
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//
//        HttpServletResponse res = (HttpServletResponse) response;
//        HttpServletRequest req = (HttpServletRequest) request;
//        res.setContentType("text/html;charset=UTF-8");
//
//        res.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
//
//        res.setHeader("Access-Control-Allow-Methods", "POST, GET, HEAD, DELETE, PUT, PATCH, OPTIONS");
//
//        res.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
//
//        res.setHeader("Access-Control-Allow-Credentials", "true");
//
//        res.setHeader("XDomainRequestAllowed","1");
//
//        chain.doFilter(request, response);
//
//    }
//
//    public void init(FilterConfig config) throws ServletException {
//
//    }
//
//}
