/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.worshipass.middleware;

import api.worshipass.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 *
 * @author David
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
    private final UsuarioService usuarioService;
    
    public AuthInterceptor(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        
        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token ausente ou invalido");
            return false;
        }
        
        String token = authorization.substring(7);
        
        boolean validToken = usuarioService.validateToken(token);

        if (!validToken) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Acesso nao autorizado");
            return false;
        }

        return true;
    }
}
