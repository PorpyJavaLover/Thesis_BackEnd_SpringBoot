package com.thesis.scheduling.businesslevel.config;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

public class SecurityUtil {
	
	private SecurityUtil() {

    }

	public static Optional<String> getCurrentUserId() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return Optional.empty();
        }

        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return Optional.empty();
        }

        Object principal = authentication.getPrincipal();
        if (principal == null) {
            return Optional.empty();
        }

        String userId = (String) principal;
        return Optional.of(userId);
    }
	
	
	public static Optional<String> getCurrentUserRole() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return Optional.empty();
        }

        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return Optional.empty();
        }
        
        Object[] author = authentication.getAuthorities().toArray();
        if(author[0] == null) {
        	return Optional.empty();
        }

        String userRole = author[0].toString();
        return Optional.of(userRole);
	}
	
}
