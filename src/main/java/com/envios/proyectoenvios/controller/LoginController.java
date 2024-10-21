package com.envios.proyectoenvios.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.envios.proyectoenvios.model.EstadoEnvio;
import com.envios.proyectoenvios.model.EstadoPago;
import com.envios.proyectoenvios.model.MetodoPago;
import com.envios.proyectoenvios.model.TipoEnvio;
import com.envios.proyectoenvios.model.Usuario;
import com.envios.proyectoenvios.model.UsuarioRol;
import com.envios.proyectoenvios.repository.IEstadoEnvioRepository;
import com.envios.proyectoenvios.repository.IEstadoPagoRepository;
import com.envios.proyectoenvios.repository.IMetodoPagoRepository;
import com.envios.proyectoenvios.repository.ITipoEnvioRepository;
import com.envios.proyectoenvios.repository.IUsuarioRepository;
import com.envios.proyectoenvios.repository.IUsuarioRolRepository;

import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController {
	
	@Autowired
	private IUsuarioRepository repository;
	@Autowired
	private IUsuarioRolRepository usuarioRolRepository;
	@Autowired
	private IEstadoEnvioRepository envioRepository;
	@Autowired
	private IMetodoPagoRepository metodoPagoRepository;
	@Autowired
	private IEstadoPagoRepository estadoPagoRepository;
	@Autowired
	private ITipoEnvioRepository tipoEnvioRepository;
	
	@GetMapping({"/", "/login"})
	public String getLogin() {
		UsuarioRol rol = new UsuarioRol();
		rol.setId(1);
		rol.setRol("admind");
		usuarioRolRepository.save(rol);
		Usuario usuario = new Usuario();
		usuario.setCodigo("A001");
		usuario.setDni("78332323");
		usuario.setEstado(true);
		usuario.setFechaModificacion(new Date());
		usuario.setFechaRegistro(new Date());
		usuario.setId(1);
		usuario.setNombre("Admind");
		usuario.setPassword("123");
		usuario.setTelefono("984562342");
		usuario.setUsuarioRol(rol);
		repository.save(usuario);
		rol.setId(2);
		rol.setRol("trabajador");
		usuarioRolRepository.save(rol);
		usuario.setCodigo("T001");
		usuario.setDni("745453433");
		usuario.setEstado(true);
		usuario.setFechaModificacion(new Date());
		usuario.setFechaRegistro(new Date());
		usuario.setId(2);
		usuario.setNombre("Trabajador");
		usuario.setPassword("123");
		usuario.setTelefono("984562232");
		usuario.setUsuarioRol(rol);
		repository.save(usuario);
		EstadoEnvio estadoEnvio = new EstadoEnvio();
		estadoEnvio.setId(1);
		estadoEnvio.setEstadoE("En Origen");
		envioRepository.save(estadoEnvio);
		estadoEnvio.setId(2);
		estadoEnvio.setEstadoE("En Transito");
		envioRepository.save(estadoEnvio);
		estadoEnvio.setId(3);
		estadoEnvio.setEstadoE("En Destino");
		envioRepository.save(estadoEnvio);
		estadoEnvio.setId(4);
		estadoEnvio.setEstadoE("Entregado");
		envioRepository.save(estadoEnvio);
		MetodoPago metodoPago = new MetodoPago();
		metodoPago.setId(1);
		metodoPago.setMetodo("Efectivo");
		metodoPagoRepository.save(metodoPago);
		metodoPago.setId(2);
		metodoPago.setMetodo("Tarjeta");
		metodoPagoRepository.save(metodoPago);
		EstadoPago estadoPago = new EstadoPago();
		estadoPago.setId(1);
		estadoPago.setEstadoP("Pagado");
		estadoPagoRepository.save(estadoPago);
		estadoPago.setId(2);
		estadoPago.setEstadoP("Falta Pago");
		estadoPagoRepository.save(estadoPago);
		TipoEnvio tipoEnvio = new TipoEnvio();
		tipoEnvio.setId(1);
		tipoEnvio.setTipo("Normal");
		tipoEnvioRepository.save(tipoEnvio);
		tipoEnvio.setId(2);
		tipoEnvio.setTipo("Express");
		tipoEnvioRepository.save(tipoEnvio);
		return "login";
	}
	
	@PostMapping("/login")
	public String getLogin(String codigo, String password, Model model, HttpSession session) {
		Usuario usuario = repository.login(codigo, password);
		if(usuario != null) {
			session.setAttribute("usuario", usuario);
			return "redirect:/inicio";
		}
		return "login";
	}
	@GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
