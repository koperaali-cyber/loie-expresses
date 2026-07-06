package com.loieexpresses.config;

import com.loieexpresses.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Ikiwa admin aliyeingia bado ana 'mustChangePassword = true',
 * anaelekezwa kwenye ukurasa wa kubadilisha password kabla ya kuendelea.
 */
@Component
@RequiredArgsConstructor
public class PasswordChangeInterceptor implements HandlerInterceptor {

    private final UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String uri = request.getRequestURI();
        // Ruhusu ukurasa wa kubadilisha password na logout wenyewe
        if (uri.startsWith("/admin/change-password") || uri.startsWith("/logout")) {
            return true;
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()
                && !"anonymousUser".equals(auth.getPrincipal())) {
            boolean mustChange = userRepository.findByUsername(auth.getName())
                    .map(u -> u.isMustChangePassword())
                    .orElse(false);
            if (mustChange) {
                response.sendRedirect(request.getContextPath() + "/admin/change-password");
                return false;
            }
        }
        return true;
    }
}
