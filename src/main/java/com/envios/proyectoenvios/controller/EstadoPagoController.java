package com.envios.proyectoenvios.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.envios.proyectoenvios.model.EstadoPago;
import com.envios.proyectoenvios.repository.IEstadoPagoRepository;

@Controller
@RequestMapping("/estadopagos")
public class EstadoPagoController {
	@Autowired
	private IEstadoPagoRepository estadoPagoRepository;
	@GetMapping("/registrar")
	public String getRegitrarTipo(Model model) {
		model.addAttribute("estadoPago", new EstadoPago());
		return "envios/ingresarEstadoPago";
	}

	@PostMapping("/registrar")
	public String registrarTipo(@ModelAttribute EstadoPago estadoPago, Model model) {
		estadoPago.setFechaRegistro(new Date());
		estadoPago.setFechaModificacion(new Date());
		estadoPagoRepository.save(estadoPago);
		return "redirect:/envios/listar";
	}
}
