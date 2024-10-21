package com.envios.proyectoenvios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envios.proyectoenvios.model.EstadoEnvio;
@Repository
public interface IEstadoEnvioRepository extends JpaRepository<EstadoEnvio, Integer> {

}
