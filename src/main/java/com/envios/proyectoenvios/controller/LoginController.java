package com.envios.proyectoenvios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.envios.proyectoenvios.model.Usuario;

import com.envios.proyectoenvios.repository.IUsuarioRepository;

import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController {
	
	@Autowired
	private IUsuarioRepository repository;
	@GetMapping({"/", "/login"})
	public String getLogin() {
		return "login";
	}
	
	@PostMapping("/login")
	public String getLogin(String codigo, String password, Model model, HttpSession session) {
		Usuario usuario = repository.login(codigo, password);
		if(usuario != null) {
			session.setAttribute("usuario", usuario);
			return "redirect:/inicio";
		}
		model.addAttribute("error", true);
		return "login";
	}
	@GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
