package com.envios.proyectoenvios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.envios.proyectoenvios.model.Cliente;
import com.envios.proyectoenvios.repository.IClienteRepository;

@Service
public class ClienteService {
	@Autowired
	private IClienteRepository clienteRepository;
	
	public List<Cliente> busquedaCliente(String dni, String nombre, String apellido){
		if(dni == null) {
			dni = "";
		}
		if(nombre == null) {
			nombre = "";
		}
		if(apellido == null) {
			apellido = "";
		}
		return clienteRepository.buscarClientes(dni, nombre, apellido);
	}
}
