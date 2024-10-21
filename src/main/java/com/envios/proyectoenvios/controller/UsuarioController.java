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

import com.envios.proyectoenvios.model.Usuario;
import com.envios.proyectoenvios.model.VistaUsuario;
import com.envios.proyectoenvios.repository.IUsuarioRepository;
import com.envios.proyectoenvios.repository.IUsuarioRolRepository;
import com.envios.proyectoenvios.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private IUsuarioRepository usuarioRepository;
	@Autowired
	UsuarioService usuarioService;

	@GetMapping("/listar")
	public String getUsuarios(Model model, String dni, String id, String nombre, Integer p) {
		if(p == null) {
			p = 0;
		}
		int f = 10;
		int start = f * p;
		int end = f * p + f;
		List<VistaUsuario> listaUsuarios = usuarioService.listarUsuarios(dni, id, nombre);
		int c = listaUsuarios.size(); 
		List<VistaUsuario> listaUsuarios2 = listaUsuarios.subList(start, end < c ? end : c);
		int npags = c % f == 0 ? c / f : c / f + 1;
		model.addAttribute("p", p);
		model.addAttribute("npags", npags);
		model.addAttribute("listaUsuarios", listaUsuarios2);
		model.addAttribute("dni", dni);
		model.addAttribute("id", id);
		model.addAttribute("nombre", nombre);
		return "/usuarios/listarUsuarios";
	}

	@GetMapping("/informacion/{id}")
	public String informacionCliente(@PathVariable int id, Model model) {
		VistaUsuario usuario = usuarioRepository.buscarUsuario(id);
		System.out.println(usuario.toString());
		model.addAttribute("usuario", usuario);
		return "/usuarios/informacionUsuario";
	}
	
	@Autowired
	private IUsuarioRolRepository usuarioRolRepository;

	@GetMapping("/registrar")
	public String getRegitrar(Model model) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("listaUsuarioRol", usuarioRolRepository.findAll());
		return "/usuarios/ingresarUsuario";
	}

	@PostMapping("/registrar")
	public String registrarUsuario(@ModelAttribute Usuario usuario, Model model) {
		usuario.setFechaRegistro(new Date());
		usuario.setFechaModificacion(new Date());
		usuarioRepository.save(usuario);
		return "redirect:/usuarios/listar";

	}
	
	@GetMapping("/editar/{id}")
	public String getEditar(@PathVariable int id, Model model) {
		Usuario usuario = usuarioRepository.findById(id).get();
		model.addAttribute("listaUsuarioRol", usuarioRolRepository.findAll());
		model.addAttribute("usuario", usuario);
		return "/usuarios/editarUsuario";
	}

	@PostMapping("/editar/{id}")
	public String editarCliente(@PathVariable int id, @ModelAttribute Usuario usuario) {
		Usuario usuarioA = usuarioRepository.findById(id).get();
		usuarioA.setNombre(usuario.getNombre());
		usuarioA.setCodigo(usuario.getCodigo());
		usuarioA.setTelefono(usuario.getTelefono());
		usuarioA.setDni(usuario.getDni());
		usuarioA.setPassword(usuario.getPassword());
		usuarioA.setUsuarioRol(usuario.getUsuarioRol());
		usuarioA.setFechaModificacion(new Date());
		usuarioA.setEstado(usuario.isEstado());
		usuarioRepository.save(usuarioA);
		return "redirect:/usuarios/listar";
	}
	
	@GetMapping("/eliminar/{id}")
	public String actualizarEstudiante(@PathVariable int id) {
		usuarioRepository.deleteById(id);
		return "redirect:/usuarios/listar";
	}

}
