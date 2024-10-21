package com.envios.proyectoenvios.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
@Entity
@Table(name = "estado_envio")
public class EstadoEnvio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String estadoE;
	@OneToMany(mappedBy = "estadoEnvio")
	private List<Envio> envios;
	@Temporal(TemporalType.DATE)
	private Date fechaRegistro;
	@Temporal(TemporalType.DATE)
	private Date fechaModificacion;
	private boolean estado;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEstadoE() {
		return estadoE;
	}
	public void setEstadoE(String estadoE) {
		this.estadoE = estadoE;
	}
	public List<Envio> getEnvios() {
		return envios;
	}
	public void setEnvios(List<Envio> envios) {
		this.envios = envios;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
}
