package com.envios.proyectoenvios.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.envios.proyectoenvios.model.Usuario;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@GetMapping("/inicio")
	public String getInicio(Model model, HttpSession session) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = new Date();
        String fd = df.format(fecha);
        model.addAttribute("fecha", fd);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        if (usuario == null) {
        	return "redirect:/login";
        }            
        return "home";
	}
}
