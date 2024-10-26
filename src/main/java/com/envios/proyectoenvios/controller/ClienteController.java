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

import com.envios.proyectoenvios.model.Cliente;
import com.envios.proyectoenvios.model.Usuario;
import com.envios.proyectoenvios.repository.IClienteRepository;
import com.envios.proyectoenvios.service.ClienteService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;


@Controller
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	private IClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;

	@GetMapping("/listar")
	public String getClientes(Model model, String dni, String nombre, String apellido, Integer p) {           
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
		return "clientes/listarClientes";
	}
	
	@GetMapping("/informacion/{codigo}")
	public String informacionCliente(@PathVariable int codigo, Model model, HttpSession session) {
		Cliente cliente = clienteRepository.findById(codigo).get();
		model.addAttribute("cliente", cliente);
		return "clientes/informacionCliente";
	}

	@GetMapping("/registrar")
	public String getRegitrar(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		model.addAttribute("usuario", usuario);
		model.addAttribute("cliente", new Cliente());
		return "clientes/ingresarCliente";
	}

	@PostMapping("/registrar")
	public String registrarCliente(@ModelAttribute Cliente cliente, Model model, HttpSession session) {
		cliente.setFechaRegistro(new Date());
		cliente.setFechaModificacion(new Date());
		if(clienteRepository.buscarClienteDNI(cliente.getDni()) != null) {
			model.addAttribute("cliente", cliente);
			model.addAttribute("error", true);
			return "clientes/ingresarCliente";
		}
		clienteRepository.save(cliente);
		return "redirect:/clientes/listar";
	}

	@GetMapping("/editar/{codigo}")
	public String getEditar(@PathVariable int codigo, Model model, HttpSession session) {
		Cliente cliente = clienteRepository.findById(codigo).get();
		model.addAttribute("cliente", cliente);
		return "clientes/editarCliente";
	}

	@PostMapping("/editar/{codigo}")
	public String editarCliente(@PathVariable int codigo, @ModelAttribute Cliente cliente, Model model) {        
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
	public String actualizarEstudiante(@PathVariable int codigo, Model model) {
		clienteRepository.deleteById(codigo);
		return "redirect:/clientes/listar";
	}
	
	
	 @Autowired
	    private JdbcTemplate jdbcTemplate;
	@RequestMapping(value = "/ClienteReport", method = RequestMethod.GET)
	@ResponseBody
	public void PacienteReport(HttpServletResponse response) throws JRException, IOException, SQLException {
	    Connection connection = jdbcTemplate.getDataSource().getConnection();
	    InputStream jasperStream = this.getClass().getResourceAsStream("/reporte/ReporteClientes.jasper");
	    Map<String, Object> params = new HashMap<String, Object>();
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connection);
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=reporteClientes.pdf");
	    final OutputStream outputStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	    connection.close();
	}
}
