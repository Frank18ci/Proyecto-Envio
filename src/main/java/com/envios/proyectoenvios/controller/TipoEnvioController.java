package com.envios.proyectoenvios.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.envios.proyectoenvios.model.TipoEnvio;
import com.envios.proyectoenvios.repository.ITipoEnvioRepository;

@Controller
@RequestMapping("/tipoenvios")
public class TipoEnvioController {
	@Autowired
	private ITipoEnvioRepository tipoEnvioRepository;
	@GetMapping("/registrar")
	public String getRegitrarTipo(Model model) {
		model.addAttribute("tipoEnvio", new TipoEnvio());
		return "envios/ingresarTipoEnvio";
	}

	@PostMapping("/registrar")
	public String registrarTipo(@ModelAttribute TipoEnvio tipoEnvio, Model model) {
		tipoEnvio.setFechaRegistro(new Date());
		tipoEnvio.setFechaModificacion(new Date());
		tipoEnvioRepository.save(tipoEnvio);
		return "redirect:/envios/listar";
	}
}
