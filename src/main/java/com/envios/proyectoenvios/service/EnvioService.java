package com.envios.proyectoenvios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.envios.proyectoenvios.model.VistaEnvio;
import com.envios.proyectoenvios.repository.IEnvioRepository;

@Service
public class EnvioService {
	@Autowired
	private IEnvioRepository envioRepository;
	public List<VistaEnvio> listarEnvios(String codigo, String descripcion, String estadoEnvio){
		if(codigo == null) {
			codigo = "";
		}
		if(descripcion == null) {
			descripcion = "";
		}
		if(estadoEnvio == null) {
			estadoEnvio = "";
		}
		return envioRepository.listarEnvios(codigo, descripcion, estadoEnvio);
	}
}
