package com.envios.proyectoenvios.model;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class VistaEnvio {
	private int id;
	private String codigo;
	private String usuario;
	private String enviante;
	private String receptor;
	private String descripcion;
	private String direccionSalida;
	private String direccionEnvio;
	private String telefonoContacto;
	private String correo;
	private String estadoPago;
	private double costoEnvio;
	private String metodoPago;
	private String estadoEnvio;
	private String tipoEnvio;
	@Temporal(TemporalType.DATE)
	private Date fechaEstimacionEntrega;
	@Temporal(TemporalType.DATE)
	private Date fechaRegistro;
	@Temporal(TemporalType.DATE)
	private Date fechaModificacion;
	private boolean estado;
	public VistaEnvio(int id, String codigo, String usuario, String enviante, String receptor, String descripcion,
			String direccionSalida, String direccionEnvio, String telefonoContacto, String correo, String estadoPago,
			double costoEnvio, String metodoPago, String estadoEnvio, String tipoEnvio, Date fechaEstimacionEntrega,
			Date fechaRegistro, Date fechaModificacion, boolean estado) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.usuario = usuario;
		this.enviante = enviante;
		this.receptor = receptor;
		this.descripcion = descripcion;
		this.direccionSalida = direccionSalida;
		this.direccionEnvio = direccionEnvio;
		this.telefonoContacto = telefonoContacto;
		this.correo = correo;
		this.estadoPago = estadoPago;
		this.costoEnvio = costoEnvio;
		this.metodoPago = metodoPago;
		this.estadoEnvio = estadoEnvio;
		this.tipoEnvio = tipoEnvio;
		this.fechaEstimacionEntrega = fechaEstimacionEntrega;
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
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getEnviante() {
		return enviante;
	}
	public void setEnviante(String enviante) {
		this.enviante = enviante;
	}
	public String getReceptor() {
		return receptor;
	}
	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDireccionSalida() {
		return direccionSalida;
	}
	public void setDireccionSalida(String direccionSalida) {
		this.direccionSalida = direccionSalida;
	}
	public String getDireccionEnvio() {
		return direccionEnvio;
	}
	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}
	public String getTelefonoContacto() {
		return telefonoContacto;
	}
	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getEstadoPago() {
		return estadoPago;
	}
	public void setEstadoPago(String estadoPago) {
		this.estadoPago = estadoPago;
	}
	public double getCostoEnvio() {
		return costoEnvio;
	}
	public void setCostoEnvio(double costoEnvio) {
		this.costoEnvio = costoEnvio;
	}
	public String getMetodoPago() {
		return metodoPago;
	}
	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}
	public String getEstadoEnvio() {
		return estadoEnvio;
	}
	public void setEstadoEnvio(String estadoEnvio) {
		this.estadoEnvio = estadoEnvio;
	}
	public String getTipoEnvio() {
		return tipoEnvio;
	}
	public void setTipoEnvio(String tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}
	public Date getFechaEstimacionEntrega() {
		return fechaEstimacionEntrega;
	}
	public void setFechaEstimacionEntrega(Date fechaEstimacionEntrega) {
		this.fechaEstimacionEntrega = fechaEstimacionEntrega;
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
