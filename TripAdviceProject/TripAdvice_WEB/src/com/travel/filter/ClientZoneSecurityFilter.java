package com.travel.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.web.mbeans.AuthenticationBean;



@WebFilter("/pages/*")
public class ClientZoneSecurityFilter implements Filter {

    public ClientZoneSecurityFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		AuthenticationBean authBean = (AuthenticationBean) req.getSession().getAttribute("authBean");
		
		boolean letGo = false;
		
		if ((authBean != null) && (authBean.isLoggedIn()))
		{
			letGo = true;
		}
		
		if (letGo) {
			chain.doFilter(request, response);
		}else{
			resp.sendRedirect(req.getContextPath()+ "/login.jsf");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
