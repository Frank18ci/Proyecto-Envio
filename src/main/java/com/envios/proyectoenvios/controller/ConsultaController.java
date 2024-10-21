package com.envios.proyectoenvios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.envios.proyectoenvios.model.Envio;
import com.envios.proyectoenvios.repository.IEnvioRepository;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {
	@Autowired
	private IEnvioRepository envioRepository;
	
	@GetMapping("/consultar")
	public String getConsultar(Model model) {
		return "/consulta/ConsultarEnvios";
	}
	
	@PostMapping("/consultar")
	public String consultar(String codigo, Model model) {
		Envio envio =  envioRepository.buscarCodigo(codigo);
		model.addAttribute("codigo", codigo);
		model.addAttribute("envio", envio);
		return "/consulta/ConsultarEnvios";
	}
}
