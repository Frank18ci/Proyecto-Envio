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

import com.envios.proyectoenvios.model.Envio;
import com.envios.proyectoenvios.model.VistaEnvio;
import com.envios.proyectoenvios.repository.IClienteRepository;
import com.envios.proyectoenvios.repository.IEnvioRepository;
import com.envios.proyectoenvios.repository.IEstadoEnvioRepository;
import com.envios.proyectoenvios.repository.IEstadoPagoRepository;
import com.envios.proyectoenvios.repository.IMetodoPagoRepository;
import com.envios.proyectoenvios.repository.ITipoEnvioRepository;
import com.envios.proyectoenvios.repository.IUsuarioRepository;
import com.envios.proyectoenvios.service.EnvioService;

@Controller
@RequestMapping("/envios")
public class EnvioController {
	@Autowired
	private IEnvioRepository envioRepository;
	@Autowired
	private EnvioService envioService;
	@Autowired
	private IEstadoEnvioRepository estadoEnvioRepository;
	@Autowired
	private IClienteRepository clienteRepository;
	@Autowired
	private IEstadoPagoRepository estadoPagoRepository;
	@Autowired
	private IMetodoPagoRepository metodoPagoRepository;
	@Autowired
	private ITipoEnvioRepository tipoEnvioRepository;
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	
	@GetMapping("/listar")
	public String getEnvios(Model model, String codigo, String descripcion, String estadoEnvio, Integer p){
		if(p == null) {
			p = 0;
		}
		int f = 10;
		int start = f * p;
		int end = f * p + f;
		List<VistaEnvio> listaEnvios = envioService.listarEnvios(codigo, descripcion, estadoEnvio);
		int c = listaEnvios.size();
		List<VistaEnvio> listaEnvios2 = listaEnvios.subList(start, end < c ? end : c);
		int npags = c % f == 0 ? c / f : c / f + 1;
		model.addAttribute("p", p);
		model.addAttribute("npags", npags);
		model.addAttribute("listaEnvios", listaEnvios2);
		model.addAttribute("codigo", codigo);
		model.addAttribute("descripcion", descripcion);
		model.addAttribute("estadoEnvio", estadoEnvio);
		model.addAttribute("listaEstadoEnvio", estadoEnvioRepository.findAll());
		return "/envios/listarEnvios";
	}
	@GetMapping("/informacion/{id}")
	public String informacionCliente(@PathVariable int id, Model model) {
		VistaEnvio envio = envioRepository.buscarEnvio(id);
		model.addAttribute("envio", envio);
		return "/envios/informacionEnvio";
	}

	@GetMapping("/registrar")
	public String getRegitrar(Model model) {
		model.addAttribute("envio", new Envio());
		model.addAttribute("listaEstadoEnvio", estadoEnvioRepository.findAll());
		model.addAttribute("listaCliente", clienteRepository.findAll());
		model.addAttribute("listaEstadoPago", estadoPagoRepository.findAll());
		model.addAttribute("listaMetodoPago", metodoPagoRepository.findAll());
		model.addAttribute("listaTipoEnvio", tipoEnvioRepository.findAll());
		model.addAttribute("listaUsuario", usuarioRepository.findAll());
		return "/envios/ingresarEnvio";
	}
	@PostMapping("/registrar")
	public String registrarUsuario(@ModelAttribute Envio envio, Model model) {
		envio.setFechaRegistro(new Date());
		envio.setFechaModificacion(new Date());
		if(envioRepository.buscarCodigo(envio.getCodigo()) != null || envioRepository.buscarCorreo(envio.getCorreo()) != null) {
			model.addAttribute("envio", envio);
			model.addAttribute("listaEstadoEnvio", estadoEnvioRepository.findAll());
			model.addAttribute("listaCliente", clienteRepository.findAll());
			model.addAttribute("listaEstadoPago", estadoPagoRepository.findAll());
			model.addAttribute("listaMetodoPago", metodoPagoRepository.findAll());
			model.addAttribute("listaTipoEnvio", tipoEnvioRepository.findAll());
			model.addAttribute("listaUsuario", usuarioRepository.findAll());
			return "/envios/ingresarEnvio";
		}
		envioRepository.save(envio);
		return "redirect:/envios/listar";

	}
	
	@GetMapping("/editar/{id}")
	public String getEditar(@PathVariable int id, Model model) {
		Envio envio = envioRepository.findById(id).get();
		model.addAttribute("listaEstadoEnvio", estadoEnvioRepository.findAll());
		model.addAttribute("listaCliente", clienteRepository.findAll());
		model.addAttribute("listaEstadoPago", estadoPagoRepository.findAll());
		model.addAttribute("listaMetodoPago", metodoPagoRepository.findAll());
		model.addAttribute("listaTipoEnvio", tipoEnvioRepository.findAll());
		model.addAttribute("listaUsuario", usuarioRepository.findAll());
		model.addAttribute("envio", envio);
		return "/envios/editarEnvio";
	}

	@PostMapping("/editar/{id}")
	public String editarCliente(@PathVariable int id, @ModelAttribute Envio envio) {
		Envio envioA = envioRepository.findById(id).get();
		envioA.setCodigo(envio.getCodigo());
		envioA.setUsuario(envio.getUsuario());
		envioA.setEnviante(envio.getEnviante());
		envioA.setReceptor(envio.getReceptor());
		envioA.setDescripcion(envio.getDescripcion());
		envioA.setDireccionSalida(envio.getDireccionSalida());
		envioA.setDireccionEnvio(envio.getDireccionEnvio());
		envioA.setTelefonoContacto(envio.getTelefonoContacto());
		envioA.setCorreo(envio.getCorreo());
		envioA.setEstadoPago(envio.getEstadoPago());
		envioA.setCostoEnvio(envio.getCostoEnvio());
		envioA.setMetodoPago(envio.getMetodoPago());
		envioA.setEstadoEnvio(envio.getEstadoEnvio());
		envioA.setTipoEnvio(envio.getTipoEnvio());
		envioA.setFechaModificacion(new Date());
		envioA.setEstado(envio.isEstado());
		envioRepository.save(envioA);
		return "redirect:/envios/listar";
	}
	
	@GetMapping("/eliminar/{id}")
	public String actualizarEstudiante(@PathVariable int id) {
		envioRepository.deleteById(id);
		return "redirect:/envios/listar";
	}
}
