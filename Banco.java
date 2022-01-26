package com;

import java.util.ArrayList;
import java.util.List;

public class Banco {
	private String nombre, domicilio;
	private List<CuentaDeBanco> listaDeCuentas;

	public Banco(String nombre, String domicilio) {
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.listaDeCuentas  = new ArrayList<CuentaDeBanco>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDomicilio() {
		return domicilio;
	}
	
	public List<CuentaDeBanco> getListaDeCuentas() {
		return listaDeCuentas;
	}


	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	
	public boolean cuentaExistente(CuentaDeBanco cuenta) {
		boolean result = false;
		for(int i = 0; i < this.getListaDeCuentas().size(); i++) {
			if(this.getListaDeCuentas().get(i).getNumeroDeCuenta() == cuenta.getNumeroDeCuenta()) {
				result = true;
			}
		}
		return result;
	}
	
	public void agregarCuenta(CuentaDeBanco cuenta) {
		if(cuentaExistente(cuenta)) {
			System.out.println("La cuenta para agregar ya existe en el banco");
		} else {
			this.getListaDeCuentas().add(cuenta);
			cuenta.setDomicilioBanco(this.getDomicilio());
			cuenta.setNombreBanco(this.getNombre());
			System.out.println("Cuenta agregada con exito");
		}
	}

	@Override
	public String toString() {
		return "Banco [nombre=" + nombre + ", domicilio=" + domicilio + "]";
	}
}
