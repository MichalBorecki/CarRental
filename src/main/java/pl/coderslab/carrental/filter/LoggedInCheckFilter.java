package pl.coderslab.carrental.filter;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.OncePerRequestFilter;

import pl.coderslab.carrental.entity.User;

// @WebFilter registers filter immediately without any other configuration
@WebFilter(description = "Check if user is logged in and if not then redirect", urlPatterns = { "/*" })
public class LoggedInCheckFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final HttpServletRequest req = (HttpServletRequest) request;
		final String url = req.getRequestURL().toString();

		if (allowedToEveryone(url)) {
			filterChain.doFilter(request, response);
			return;
		}
		HttpSession sess = req.getSession();
		User user = (User) sess.getAttribute("user");
		
		/*
		 * if user null then redirect to login page
		 */
		if (user == null) {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect(req.getContextPath() + "/user/login");
			return;
		}
		filterChain.doFilter(request, response);
	}

	
	/*
	 * Allowed url's for not logged users (+ get from resources)
	 */
	private boolean allowedToEveryone(final String url) {
		if (url.endsWith("/user/register") || url.endsWith("/user/login") || url.endsWith("/") || url.contains("resources")) {
			return true;
		}
		return false;
	}
}