package com;

import java.util.Date;

public class ComprobanteDeTransaccionBancaria {
	private String transaccion;
	private Date fechaDeTransaccion;
	public CuentaDeBanco cuentaOrigen, cuentaDestino;
	public ComprobanteDeTransaccionBancaria(CuentaDeBanco cuenta, String transaccion) {
		this.cuentaOrigen = cuenta;
		this.transaccion = transaccion;
		this.fechaDeTransaccion = new Date();
	}
	
	public ComprobanteDeTransaccionBancaria(CuentaDeBanco cuentaOrigen, String transaccion, CuentaDeBanco cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
		this.cuentaOrigen = cuentaOrigen;
		this.transaccion = transaccion;
		this.fechaDeTransaccion = new Date();
	}
	
	public String getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(String transaccion) {
		this.transaccion = transaccion;
	}

	public Date getFechaDeTransaccion() {
		return fechaDeTransaccion;
	}

	public static void hacerComprobante(CuentaDeBanco cuenta, String transaccion) {
		ComprobanteDeTransaccionBancaria comprobante = new ComprobanteDeTransaccionBancaria(cuenta, transaccion);
		System.out.println("Comprobante de " + comprobante.getTransaccion() + " a la cuenta " + cuenta.getNumeroDeCuenta() + " el " + comprobante.getFechaDeTransaccion());
	}
	
	public static void hacerComprobante(CuentaDeBanco cuentaOrigen, String transaccion, CuentaDeBanco cuentaDestino) {
		ComprobanteDeTransaccionBancaria comprobante = new ComprobanteDeTransaccionBancaria(cuentaOrigen, transaccion, cuentaDestino);
		System.out.println("Comprobante de " + comprobante.getTransaccion() +
				           " a la cuenta " + cuentaDestino.getNumeroDeCuenta() +
				           " desde la cuenta " + cuentaOrigen.getNumeroDeCuenta() +
				           " el " + comprobante.getFechaDeTransaccion());
	}
}
