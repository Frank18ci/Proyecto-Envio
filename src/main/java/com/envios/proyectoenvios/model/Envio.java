package com.envios.proyectoenvios.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "envios")
public class Envio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String codigo;
	@ManyToOne
	private Usuario usuario;
	@ManyToOne
	private Cliente enviante;
	@ManyToOne
	private Cliente receptor;
	private String descripcion;
	private String direccionSalida;
	private String direccionEnvio;
	private String telefonoContacto;
	@Column(unique = true)
	private String correo;
	@ManyToOne
	private EstadoPago estadoPago;
	private double costoEnvio;
	@ManyToOne
	private MetodoPago metodoPago;
	@ManyToOne
	private EstadoEnvio estadoEnvio;
	@ManyToOne
	private TipoEnvio tipoEnvio;
	@Temporal(TemporalType.DATE)
	private Date fechaEstimacionEntrega;
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
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Cliente getEnviante() {
		return enviante;
	}
	public void setEnviante(Cliente enviante) {
		this.enviante = enviante;
	}
	public Cliente getReceptor() {
		return receptor;
	}
	public void setReceptor(Cliente receptor) {
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
	public EstadoPago getEstadoPago() {
		return estadoPago;
	}
	public void setEstadoPago(EstadoPago estadoPago) {
		this.estadoPago = estadoPago;
	}
	public double getCostoEnvio() {
		return costoEnvio;
	}
	public void setCostoEnvio(double costoEnvio) {
		this.costoEnvio = costoEnvio;
	}
	public MetodoPago getMetodoPago() {
		return metodoPago;
	}
	public void setMetodoPago(MetodoPago metodoPago) {
		this.metodoPago = metodoPago;
	}
	public EstadoEnvio getEstadoEnvio() {
		return estadoEnvio;
	}
	public void setEstadoEnvio(EstadoEnvio estadoEnvio) {
		this.estadoEnvio = estadoEnvio;
	}
	public TipoEnvio getTipoEnvio() {
		return tipoEnvio;
	}
	public void setTipoEnvio(TipoEnvio tipoEnvio) {
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
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	
}
