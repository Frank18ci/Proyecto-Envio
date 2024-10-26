package com.envios.proyectoenvios.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.envios.proyectoenvios.model.Cliente;
import com.envios.proyectoenvios.model.Usuario;
import com.envios.proyectoenvios.repository.IClienteRepository;
import com.envios.proyectoenvios.service.ClienteService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	private IClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;

	@GetMapping("/listar")
	public String getClientes(Model model, String dni, String nombre, String apellido, Integer p, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		model.addAttribute("usuario", usuario);
		if(p == null) {
			p = 0;
		}
		int f = 10;
		int start = f * p;
		int end = f * p + f;
		
		List<Cliente> listaClientes = clienteService.busquedaCliente(dni, nombre, apellido);
		int c = listaClientes.size(); 
		List<Cliente> listaClientes2 = listaClientes.subList(start, end < c ? end : c);
		int npags = c % f == 0 ? c / f : c / f + 1;
		model.addAttribute("p", p);
		model.addAttribute("npags", npags);
		model.addAttribute("listaclientes", listaClientes2);
		model.addAttribute("dni", dni);
		model.addAttribute("nombre", nombre);
		model.addAttribute("apellido", apellido);
		return "/clientes/listarClientes";
	}
	
	@GetMapping("/informacion/{codigo}")
	public String informacionCliente(@PathVariable int codigo, Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		model.addAttribute("usuario", usuario);
		Cliente cliente = clienteRepository.findById(codigo).get();
		model.addAttribute("cliente", cliente);
		return "/clientes/informacionCliente";
	}

	@GetMapping("/registrar")
	public String getRegitrar(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		model.addAttribute("usuario", usuario);
		model.addAttribute("cliente", new Cliente());
		return "/clientes/ingresarCliente";
	}

	@PostMapping("/registrar")
	public String registrarCliente(@ModelAttribute Cliente cliente, Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		model.addAttribute("usuario", usuario);
		cliente.setFechaRegistro(new Date());
		cliente.setFechaModificacion(new Date());
		if(clienteRepository.buscarClienteDNI(cliente.getDni()) != null) {
			model.addAttribute("cliente", cliente);
			model.addAttribute("error", true);
			return "/clientes/ingresarCliente";
		}
		clienteRepository.save(cliente);
		return "redirect:/clientes/listar";
	}

	@GetMapping("/editar/{codigo}")
	public String getEditar(@PathVariable int codigo, Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		model.addAttribute("usuario", usuario);
		Cliente cliente = clienteRepository.findById(codigo).get();
		model.addAttribute("cliente", cliente);
		return "/clientes/editarCliente";
	}

	@PostMapping("/editar/{codigo}")
	public String editarCliente(@PathVariable int codigo, @ModelAttribute Cliente cliente, Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		model.addAttribute("usuario", usuario);
		Cliente clienteA = clienteRepository.findById(codigo).get();
		clienteA.setNombre(cliente.getNombre());
		clienteA.setApellido(cliente.getApellido());
		clienteA.setTelefono(cliente.getTelefono());
		clienteA.setDni(cliente.getDni());
		clienteA.setFechaModificacion(new Date());
		clienteA.setEstado(cliente.isEstado());
		clienteRepository.save(clienteA);
		return "redirect:/clientes/listar";
	}

	@GetMapping("/eliminar/{codigo}")
	public String actualizarEstudiante(@PathVariable int codigo, Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		model.addAttribute("usuario", usuario);
		clienteRepository.deleteById(codigo);
		return "redirect:/clientes/listar";
	}

}
