package com.envios.proyectoenvios.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.envios.proyectoenvios.model.Usuario;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cuenta")
public class CuentaController {
	@GetMapping("/cuenta")
	public String getCuenta(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		model.addAttribute("usuario", usuario);
	    if (usuario == null) {
	        	return "redirect:/login";
	    }            
	    return "cuenta/cuenta";
	}
}
