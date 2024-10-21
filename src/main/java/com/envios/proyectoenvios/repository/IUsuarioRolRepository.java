package com.envios.proyectoenvios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envios.proyectoenvios.model.UsuarioRol;
@Repository
public interface IUsuarioRolRepository extends JpaRepository<UsuarioRol, Integer> {

}
