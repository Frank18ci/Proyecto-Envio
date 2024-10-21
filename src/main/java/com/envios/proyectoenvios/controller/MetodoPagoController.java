package com.envios.proyectoenvios.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.envios.proyectoenvios.model.MetodoPago;
import com.envios.proyectoenvios.repository.IMetodoPagoRepository;


@Controller
@RequestMapping("/metodopagos")
public class MetodoPagoController {
	@Autowired
	private IMetodoPagoRepository metodoPagoRepository;
	@GetMapping("/registrar")
	public String getRegitrarTipo(Model model) {
		model.addAttribute("metodoPago", new MetodoPago());
		return "envios/ingresarMetodoPago";
	}

	@PostMapping("/registrar")
	public String registrarTipo(@ModelAttribute MetodoPago metodoPago, Model model) {
		metodoPago.setFechaRegistro(new Date());
		metodoPago.setFechaModificacion(new Date());
		metodoPagoRepository.save(metodoPago);
		return "redirect:/envios/listar";
	}
}
