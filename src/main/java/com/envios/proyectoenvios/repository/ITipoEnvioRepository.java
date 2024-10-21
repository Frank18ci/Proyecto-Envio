package com.envios.proyectoenvios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envios.proyectoenvios.model.TipoEnvio;
@Repository
public interface ITipoEnvioRepository extends JpaRepository<TipoEnvio, Integer> {

}
