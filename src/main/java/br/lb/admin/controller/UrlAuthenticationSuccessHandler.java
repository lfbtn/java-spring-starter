package br.lb.admin.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.transaction.annotation.Transactional;

import br.lb.admin.service.UserService;
import br.lb.admin.entity.LogEntry;
import br.lb.admin.repository.LogRepository;

public class UrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    protected Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private UserService userService;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException {
	handle(request, response, authentication);
	clearAuthenticationAttributes(request);
    }

    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
	String targetUrl = determineTargetUrl(authentication);

	if (response.isCommitted()) {
	    logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
	    return;
	}

	redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    /**
     * Builds the target URL according to the logic defined in the main class
     * Javadoc.
     */
    protected String determineTargetUrl(Authentication authentication) {
	boolean isUser = false;
	boolean isAdmin = false;
	boolean isHolerite = false;
	boolean isEstoque = false;
	Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	for (GrantedAuthority grantedAuthority : authorities) {
	    if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
		isUser = true;
		break;
	    } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
		isAdmin = true;
		break;
	    } else if (grantedAuthority.getAuthority().equals("ROLE_HOLERITE")) {
		isHolerite = true;
		break;
	    } else if (grantedAuthority.getAuthority().equals("ROLE_ESTOQUE")) {
		isEstoque = true;
		break;
	    }
	}

	LogEntry log = new LogEntry();
	// User user = userRepository.findByName(authentication.getName());
	// log.setUsuario(user.getId());
	log.setTipo("LOGIN");
	log.setInclusao(new Date());
	logRepository.save(log);

	if (isUser) {
	    return "/home.html";
	} else if (isAdmin) {
	    return "/home.html";
	} else if (isEstoque) {
	    return "/home.html";
	} else if (isHolerite) {
	    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    br.lb.admin.entity.User userJsi = userService.findByCpf(user.getUsername());
	    if (userJsi.getFirstTimeLogin()) {
		return "/user-email-register.html";
	    } else {
		return "/holerites.html";
	    }

	} else {
	    // throw new IllegalStateException();
	    return "/about.html";

	}
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
	HttpSession session = request.getSession(false);
	if (session == null) {
	    return;
	}
	session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
	this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
	return redirectStrategy;
    }
}