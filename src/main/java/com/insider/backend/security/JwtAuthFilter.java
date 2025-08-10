package com.insider.backend.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.insider.backend.util.JwtUtil;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter {
	
	private JwtUtil jwtutil;
	
	public JwtAuthFilter(JwtUtil jwtutil) {
		this.jwtutil = jwtutil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String header = request.getHeader("Authorization");
		
		if(header != null && header.startsWith("Bearer ")) {
			String token = header.substring(7);
			Claims claims = null;
			
			try {
				claims = jwtutil.extractAllClaims(token);
			}
			catch(Exception e) {
				 e.getMessage();
			}
			if(claims != null && !jwtutil.isTokenExpired(token)) {
				String Sapid = claims.getSubject();
				String role = (String) claims.get("role");
				
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+role);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(Sapid, null, List.of(authority));
				
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		filterChain.doFilter(request, response);
	}
	
}