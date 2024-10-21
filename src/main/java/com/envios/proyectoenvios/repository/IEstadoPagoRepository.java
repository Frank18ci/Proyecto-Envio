package com.envios.proyectoenvios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envios.proyectoenvios.model.EstadoPago;
@Repository
public interface IEstadoPagoRepository extends JpaRepository<EstadoPago, Integer> {

}
