package com.envios.proyectoenvios.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.envios.proyectoenvios.model.EstadoEnvio;
import com.envios.proyectoenvios.repository.IEstadoEnvioRepository;

@Controller
@RequestMapping("/estadoenvios")
public class EstadoEnvioController {
	@Autowired
	private IEstadoEnvioRepository estadoEnvioRepository;
	@GetMapping("/registrar")
	public String getRegitrarTipo(Model model) {
		model.addAttribute("estadoEnvio", new EstadoEnvio());
		return "envios/ingresarEstadoEnvio";
	}

	@PostMapping("/registrar")
	public String registrarTipo(@ModelAttribute EstadoEnvio estadoEnvio, Model model) {
		estadoEnvio.setFechaRegistro(new Date());
		estadoEnvio.setFechaModificacion(new Date());
		estadoEnvioRepository.save(estadoEnvio);
		return "redirect:/envios/listar";
	}
}
