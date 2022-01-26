package com;

public class ClienteDeBanco {
	private String nombre, domicilio, eMail;
	private int edad;
	private long numeroContacto;
	
	public ClienteDeBanco(String nombre, int edad, String domicilio, long numeroContacto, String eMail) {
		this.nombre = nombre;
		this.edad = edad;
		this.domicilio = domicilio;
		this.numeroContacto = numeroContacto;
		this.eMail = eMail;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	
	public long getNumeroContacto() {
		return numeroContacto;
	}

	public void setNumeroContacto(int numeroContacto) {
		this.numeroContacto = numeroContacto;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	@Override
	public String toString() {
		return "ClienteDeBanco [nombre=" + nombre + ", domicilio=" + domicilio + ", eMail=" + eMail + ", edad=" + edad
				+ ", numeroContacto=" + numeroContacto + "]";
	}
}
