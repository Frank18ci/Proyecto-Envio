package com.envios.proyectoenvios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.envios.proyectoenvios.model.Usuario;
import com.envios.proyectoenvios.model.VistaUsuario;
@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
	@Query("SELECT u FROM Usuario u WHERE u.codigo = ?1 AND u.password = ?2")
	public Usuario login(String codigo, String password);
	
	
	@Query("SELECT new com.envios.proyectoenvios.model.VistaUsuario(u.id, u.nombre, u.telefono, u.dni, u.codigo, u.password, ur.rol, u.fechaRegistro, u.fechaModificacion, u.estado) " +
	           "FROM Usuario u INNER JOIN UsuarioRol ur ON u.usuarioRol.id = ur.id WHERE u.dni LIKE CONCAT('%', ?1, '%') AND u.dni LIKE CONCAT('%', ?2, '%') AND u.dni LIKE CONCAT('%', ?3, '%')")
	    List<VistaUsuario> listarUsuarios(String dni, String codigo, String nombre);
	 
	@Query("SELECT new com.envios.proyectoenvios.model.VistaUsuario(u.id, u.nombre, u.telefono, u.dni, u.codigo, u.password, ur.rol, u.fechaRegistro, u.fechaModificacion, u.estado) " +
	           "FROM Usuario u INNER JOIN UsuarioRol ur ON u.usuarioRol.id = ur.id WHERE u.id = ?1")
	    VistaUsuario buscarUsuario(int id);
	 
	
}
