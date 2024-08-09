package hhplus.e_commerce.support.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Filter init");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        log.info("요청 Url : {}", req.getRequestURL());

        log.info("Filter 시작입니다.");
        chain.doFilter(request, response);
        log.info("Filter 끝입니다.");
    }

    @Override
    public void destroy() {
        log.info("Filter destroy");
        Filter.super.destroy();
    }
}
