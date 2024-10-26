package com.envios.proyectoenvios.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

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
		return "envios/listarEnvios";
	}
	@GetMapping("/informacion/{id}")
	public String informacionCliente(@PathVariable int id, Model model) {
		VistaEnvio envio = envioRepository.buscarEnvio(id);
		model.addAttribute("envio", envio);
		return "envios/informacionEnvio";
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
		return "envios/ingresarEnvio";
	}
	@PostMapping("/registrar")
	public String registrarUsuario(@ModelAttribute Envio envio, Model model) {
		envio.setFechaRegistro(new Date());
		envio.setFechaModificacion(new Date());
		if(envioRepository.buscarCodigo(envio.getCodigo()) != null
			) {
			model.addAttribute("envio", envio);
			model.addAttribute("listaEstadoEnvio", estadoEnvioRepository.findAll());
			model.addAttribute("listaCliente", clienteRepository.findAll());
			model.addAttribute("listaEstadoPago", estadoPagoRepository.findAll());
			model.addAttribute("listaMetodoPago", metodoPagoRepository.findAll());
			model.addAttribute("listaTipoEnvio", tipoEnvioRepository.findAll());
			model.addAttribute("listaUsuario", usuarioRepository.findAll());
			model.addAttribute("error", true);
			return "/envios/ingresarEnvio";
		}
		envioRepository.save(envio);
		return "redirect:listar";

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
		return "envios/editarEnvio";
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
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
@RequestMapping(value = "/EnvioReport", method = RequestMethod.GET)
@ResponseBody
public void PacienteReport(HttpServletResponse response) throws JRException, IOException, SQLException {
    Connection connection = jdbcTemplate.getDataSource().getConnection();
    InputStream jasperStream = this.getClass().getResourceAsStream("/reporte/ReporteEnvios.jasper");
    Map<String, Object> params = new HashMap<String, Object>();
    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connection);
    response.setContentType("application/x-pdf");
    response.setHeader("Content-disposition", "inline; filename=reporteEnvios.pdf");
    final OutputStream outputStream = response.getOutputStream();
    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
    connection.close();
}
}
