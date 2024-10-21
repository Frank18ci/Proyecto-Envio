package com.envios.proyectoenvios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.envios.proyectoenvios.model.Envio;
import com.envios.proyectoenvios.model.VistaEnvio;
@Repository
public interface IEnvioRepository extends JpaRepository<Envio, Integer> {
	
	@Query("SELECT new com.envios.proyectoenvios.model.VistaEnvio(e.id, e.codigo, u.nombre, "
			+ "CONCAT(ce.nombre, ' ', ce.apellido), "
			+ "CONCAT(cr.nombre, ' ', cr.apellido), "
			+ "e.descripcion, e.direccionSalida, e.direccionEnvio, e.telefonoContacto, e.correo,"
			+ "ep.estadoP, e.costoEnvio, mp.metodo, ee.estadoE, te.tipo, "
			+ "e.fechaEstimacionEntrega, e.fechaRegistro, e.fechaModificacion, e.estado) "
	        + "FROM Envio e "
	        + "INNER JOIN Usuario u ON u.id = e.usuario.id "
	        + "INNER JOIN Cliente ce ON ce.codigo = e.enviante.codigo "
			+ "INNER JOIN Cliente cr ON cr.codigo = e.receptor.codigo "
			+ "INNER JOIN EstadoPago ep ON ep.id = e.estadoPago.id "
			+ "INNER JOIN MetodoPago mp ON mp.id = e.metodoPago.id "
			+ "INNER JOIN EstadoEnvio ee ON ee.id = e.estadoEnvio.id "
			+ "INNER JOIN TipoEnvio te ON te.id = e.tipoEnvio.id "
			+ "WHERE e.id = ?1")
	public VistaEnvio buscarEnvio(int id);
	
	@Query("SELECT e FROM Envio e where e.codigo = ?1")
	public Envio buscarCodigo(String codigo);
	@Query("SELECT e FROM Envio e where e.correo = ?1")
	public Envio buscarCorreo(String correo);
	
	
	@Query("SELECT new com.envios.proyectoenvios.model.VistaEnvio(e.id, e.codigo, u.nombre, "
			+ "CONCAT(ce.nombre, ' ', ce.apellido), "
			+ "CONCAT(cr.nombre, ' ', cr.apellido), "
			+ "e.descripcion, e.direccionSalida, e.direccionEnvio, e.telefonoContacto, e.correo,"
			+ "ep.estadoP, e.costoEnvio, mp.metodo, ee.estadoE, te.tipo, "
			+ "e.fechaEstimacionEntrega, e.fechaRegistro, e.fechaModificacion, e.estado) "
	        + "FROM Envio e "
	        + "INNER JOIN Usuario u ON u.id = e.usuario.id "
	        + "INNER JOIN Cliente ce ON ce.codigo = e.enviante.codigo "
			+ "INNER JOIN Cliente cr ON cr.codigo = e.receptor.codigo "
			+ "INNER JOIN EstadoPago ep ON ep.id = e.estadoPago.id "
			+ "INNER JOIN MetodoPago mp ON mp.id = e.metodoPago.id "
			+ "INNER JOIN EstadoEnvio ee ON ee.id = e.estadoEnvio.id "
			+ "INNER JOIN TipoEnvio te ON te.id = e.tipoEnvio.id "
			+ "WHERE e.codigo LIKE CONCAT('%', ?1, '%') AND e.descripcion LIKE CONCAT('%', ?2, '%') AND e.estadoEnvio.estadoE LIKE CONCAT('%', ?3, '%')")
	public List<VistaEnvio> listarEnvios(String codigo, String descripcion, String estadoEnvio);
	 
}
