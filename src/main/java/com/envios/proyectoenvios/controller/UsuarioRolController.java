package com.envios.proyectoenvios.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.envios.proyectoenvios.model.UsuarioRol;
import com.envios.proyectoenvios.repository.IUsuarioRolRepository;

@Controller
@RequestMapping("/roles")
public class UsuarioRolController {
	@Autowired
	private IUsuarioRolRepository usuarioRolRepository;
	@GetMapping("/registrarRol")
	public String getRegitrarTipo(Model model) {
		model.addAttribute("usuarioRol", new UsuarioRol());
		return "usuarios/ingresarRolUsuario";
	}

	@PostMapping("/registrarRol")
	public String registrarTipo(@ModelAttribute UsuarioRol rol, Model model) {
		rol.setFechaRegistro(new Date());
		rol.setFechaModificacion(new Date());
		usuarioRolRepository.save(rol);
		return "redirect:/usuarios/listar";
	}
}
