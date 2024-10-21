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
@Table(name = "clientes")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	private String nombre;
	private String apellido;
	private String telefono;
    @Column(unique = true)
	private String dni; 
	@OneToMany(mappedBy = "enviante")
	private List<Envio> enviosHechos;
	@OneToMany(mappedBy = "receptor")
	private List<Envio> enviosRecibidos;
	@Temporal(TemporalType.DATE)
	private Date fechaRegistro;
	@Temporal(TemporalType.DATE)
	private Date fechaModificacion;
	private boolean estado;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public List<Envio> getEnviosHechos() {
		return enviosHechos;
	}
	public void setEnviosHechos(List<Envio> enviosHechos) {
		this.enviosHechos = enviosHechos;
	}
	public List<Envio> getEnviosRecibidos() {
		return enviosRecibidos;
	}
	public void setEnviosRecibidos(List<Envio> enviosRecibidos) {
		this.enviosRecibidos = enviosRecibidos;
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
