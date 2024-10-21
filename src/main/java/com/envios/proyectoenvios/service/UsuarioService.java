package com.envios.proyectoenvios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.envios.proyectoenvios.model.VistaUsuario;
import com.envios.proyectoenvios.repository.IUsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private IUsuarioRepository usuarioRepository;
	public List<VistaUsuario> listarUsuarios(String dni, String codigo, String nombre) {
		if(dni == null) {
			dni = "";
		}
		if(codigo == null) {
			codigo = "";
		}
		if(nombre == null) {
			nombre = "";
		}
		List<VistaUsuario> vistaUsuarios = usuarioRepository.listarUsuarios(dni, codigo, nombre);
		return vistaUsuarios;
	}
	VistaUsuario buscarUsuario(int id) {
		return usuarioRepository.buscarUsuario(id);
	}
	
}
