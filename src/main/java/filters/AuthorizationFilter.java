package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Triton on 07.04.2015.
 */
public class AuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Hello from Filter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
            if (request.getSession(true).getAttribute("USER") != null) {
                System.out.println("Authorization checked");
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                System.out.println("Authorization error");
                servletRequest.setAttribute("Error", "You need to login first!");
                request.getRequestDispatcher("/Login.jsp").forward(servletRequest, servletResponse);
                //response.sendRedirect(request.getContextPath()+"/Login.jsp");
            }

    }

    @Override
    public void destroy() {

    }
}
