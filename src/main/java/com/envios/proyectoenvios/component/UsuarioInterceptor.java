package com.envios.proyectoenvios.component;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.envios.proyectoenvios.model.Usuario;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class UsuarioInterceptor implements HandlerInterceptor {
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object usuario = session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("/login");
            return false;
        }

        if (request.getRequestURI().startsWith("/empleados")) {
            Usuario u = (Usuario) usuario;
            if (u.getUsuarioRol() == null || !"admind".equals(u.getUsuarioRol().getRol())) {
                response.sendRedirect("/login");
                return false;
            }
        }

        return true;
    }
}
