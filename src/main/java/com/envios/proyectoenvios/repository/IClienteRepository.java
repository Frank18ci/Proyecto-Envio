package com.envios.proyectoenvios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.envios.proyectoenvios.model.Cliente;
@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Integer> {
	@Query("SELECT c FROM Cliente c WHERE c.dni LIKE CONCAT('%', ?1, '%') AND c.nombre LIKE CONCAT('%', ?2, '%') AND c.apellido LIKE CONCAT('%', ?3, '%')")
	public List<Cliente> buscarClientes(String dni, String nombre, String apellido);
	@Query("SELECT c FROM Cliente c WHERE c.dni = ?1")
	public Cliente buscarClienteDNI(String dni);
}
