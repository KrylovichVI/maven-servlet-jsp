package com.krylovichVI.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class EncodingFilter implements Filter {
    private static final String UTF_8 = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest rq, ServletResponse rs, FilterChain filterChain) throws IOException, ServletException {
        rq.setCharacterEncoding(UTF_8);
        filterChain.doFilter(rq, rs);
        rs.setCharacterEncoding(UTF_8);
    }

    @Override
    public void destroy() {

    }
}
