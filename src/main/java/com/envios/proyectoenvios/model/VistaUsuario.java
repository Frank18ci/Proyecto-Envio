package com.envios.proyectoenvios.model;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class VistaUsuario {
	private int id;
    private String nombre;
    private String telefono;
    private String dni;
    private String codigo;
    private String password;
    private String usuarioRol;
    
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    
    private boolean estado;
    
    public VistaUsuario(int id, String nombre, String telefono, String dni, String codigo, String password, String usuarioRol, Date fechaRegistro, Date fechaModificacion, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.dni = dni;
        this.codigo = codigo;
        this.password = password;
        this.usuarioRol = usuarioRol;
        this.fechaRegistro = fechaRegistro;
        this.fechaModificacion = fechaModificacion;
        this.estado = estado;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsuarioRol() {
		return usuarioRol;
	}

	public void setUsuarioRol(String usuarioRol) {
		this.usuarioRol = usuarioRol;
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
