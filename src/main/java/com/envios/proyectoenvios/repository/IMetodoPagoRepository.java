package com.envios.proyectoenvios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envios.proyectoenvios.model.MetodoPago;
@Repository
public interface IMetodoPagoRepository extends JpaRepository<MetodoPago, Integer> {

}
