package com.project.interview.filter;

import com.project.interview.utils.BlackIpUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "blackIpFilter")
public class BlackIpFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String remoteAddr = servletRequest.getRemoteAddr();
        if (BlackIpUtils.isBlackIp(remoteAddr)) {
            servletResponse.setCharacterEncoding("UTF-8");
            servletResponse.setContentType("text/html;charset=UTF-8");
            servletResponse.getWriter().write("<p style = \"text-align:center\" >您的IP已被拉黑，请联系管理员</p>");
            return;
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
