package com;

public class CuentaDeBanco {
	
	private String domicilioCliente, domicilioBanco, eMailCliente, nombreCliente, nombreBanco;
	private int edadCliente, numeroDeCuenta;
	private long numeroContactoCliente;
	private double  saldoDisponible, saldoMinimo, saldoMaximo;
	private boolean cuentaActiva;
	public static int ultimoNumeroDeCuenta = 0;
	
	
	public CuentaDeBanco(ClienteDeBanco cliente, double saldoDisponible, double saldoMinimo, double saldoMaximo) {
		this.domicilioCliente = cliente.getDomicilio();
		this.eMailCliente = cliente.geteMail();
		this.nombreCliente = cliente.getNombre();
		this.numeroContactoCliente = cliente.getNumeroContacto();
		this.edadCliente = cliente.getEdad();
		this.numeroDeCuenta = ultimoNumeroDeCuenta + (int)Math.floor(Math.random()*100);
		CuentaDeBanco.ultimoNumeroDeCuenta += this.numeroDeCuenta;
		this.saldoDisponible = saldoDisponible;
		this.saldoMinimo = saldoMinimo;
		this.saldoMaximo = saldoMaximo;
		this.cuentaActiva = true;
	}



	public String getDomicilioCliente() {
		return domicilioCliente;
	}



	public void setDomicilioCliente(String domicilioCliente) {
		this.domicilioCliente = domicilioCliente;
	}


	public String geteMailCliente() {
		return eMailCliente;
	}



	public void seteMailCliente(String eMailCliente) {
		this.eMailCliente = eMailCliente;
	}



	public String getNombreCliente() {
		return nombreCliente;
	}



	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}


	public String getNombreBanco() {
		return nombreBanco;
	}
	
	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}
	
	public String getDomicilioBanco() {
		return domicilioBanco;
	}
	
	public void setDomicilioBanco(String domicilioBanco) {
		this.domicilioBanco= domicilioBanco;
	}


	public long getNumeroContactoCliente() {
		return numeroContactoCliente;
	}



	public void setNumeroContactoCliente(int numeroContactoCliente) {
		this.numeroContactoCliente = numeroContactoCliente;
	}



	public int getEdadCliente() {
		return edadCliente;
	}



	public void setEdadCliente(int edadCliente) {
		this.edadCliente = edadCliente;
	}


	public int getNumeroDeCuenta() {
		return numeroDeCuenta;
	}


	public double getSaldoDisponible() {
		return saldoDisponible;
	}


	public double getSaldoMinimo() {
		return saldoMinimo;
	}



	public void setSaldoMinimo(double saldoMinimo) {
		this.saldoMinimo = saldoMinimo;
	}



	public double getSaldoMaximo() {
		return saldoMaximo;
	}



	public void setSaldoMaximo(double saldoMaximo) {
		this.saldoMaximo = saldoMaximo;
	}



	public void setSaldoDisponible(double saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}
	
	public boolean getCuentaActiva() {
		return cuentaActiva;
	}



	@Override
	public String toString() {
		return "CuentaDeBanco [domicilioCliente=" + domicilioCliente + ", domicilioBanco=" + domicilioBanco
				+ ", eMailCliente=" + eMailCliente + ", nombreCliente=" + nombreCliente + ", nombreBanco=" + nombreBanco
				+ ", numeroContactoCliente=" + numeroContactoCliente + ", edadCliente=" + edadCliente
				+ ", numeroDeCuenta=" + numeroDeCuenta + ", saldoDisponible=" + saldoDisponible + ", saldoMinimo="
				+ saldoMinimo + ", saldoMaximo=" + saldoMaximo + ", cuentaActiva=" + cuentaActiva + "]";
	}
	
	public void retirar(double cantidad) {
		if((this.getSaldoDisponible() - cantidad) > this.getSaldoMinimo()) {
		    this.setSaldoDisponible((this.getSaldoDisponible() - cantidad));
		    System.out.println("Hola " + this.getNombreCliente() +", acaba de retirar: " + cantidad);
		    System.out.println("Nuevo saldo disponible: " + this.getSaldoDisponible());
		    ComprobanteDeTransaccionBancaria.hacerComprobante(this, "retiro");
		} else {
			System.out.println("Hola " + this.getNombreCliente() + ", su saldo es insuficiente");
			System.out.println("Saldo disponible: " + this.getSaldoDisponible());
		}
	}
	
	public void depositar(double cantidad) {
		if((this.getSaldoDisponible() + cantidad) > this.getSaldoMaximo()) {
			System.out.println("Lo sentimos, la cantidad a depositar excede el saldo maximo de la cuenta");
		} else {
			this.setSaldoDisponible((this.getSaldoDisponible() + cantidad));
			System.out.println("Nuevo saldo disponible: " + this.getSaldoDisponible());
			ComprobanteDeTransaccionBancaria.hacerComprobante(this, "deposito");
		}
	}
	
	private boolean cuentaValida() {
		if (!this.getCuentaActiva()) {
			return false;
		} else {
			return true;
		}
	}
	
	
	public void transferir(CuentaDeBanco cuentaDestino, double monto) {
		if(monto <= 0) {
			System.out.println("El monto a depositar debe ser mayor a 0");
		} else {
			if(!this.cuentaValida() || !cuentaDestino.cuentaValida()) {
				System.out.println("Las cuentas deben ser activas para hacer esta transaccion");
			} else if(this.cuentaValida() && cuentaDestino.cuentaValida()) {
				double saldoFinalCuentaOrigen = this.getSaldoDisponible() - monto;
				double saldoFinalCuentaDestino = cuentaDestino.getSaldoDisponible() + monto;
				if(saldoFinalCuentaOrigen < this.getSaldoMinimo()) {
					System.out.println("Lo sentimos, la cuenta origen no cuenta con el saldo suficiente");
				} else if(saldoFinalCuentaDestino > cuentaDestino.getSaldoMaximo()) {
					System.out.println("Lo sentimos, el monto excede el saldo maximo de la cuenta a despositar");
				} else {
					this.setSaldoDisponible(saldoFinalCuentaOrigen);
					cuentaDestino.setSaldoDisponible(saldoFinalCuentaDestino);
					ComprobanteDeTransaccionBancaria.hacerComprobante(this, "deposito", cuentaDestino);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		
		
		Banco banamex = new Banco("Banamex", "Calle del banco #123, CiudadBanco");
		System.out.println("Cuentas activas en Banamex: " + banamex.getListaDeCuentas().toString());
		ClienteDeBanco clienteValido = new ClienteDeBanco("Nombre Del Cliente", 25, "Calle del cliente #147, CiudadBanco", 6666556644L, "clienteValido@gmail.com");
		System.out.println("Crear dos cuentas para transacciones:");
		CuentaDeBanco cuenta1 = new CuentaDeBanco(clienteValido, 1000, 0, 23000);
		banamex.agregarCuenta(cuenta1);
		CuentaDeBanco cuenta2 = new CuentaDeBanco(clienteValido, 1000, 0, 100000);
		banamex.agregarCuenta(cuenta2);
		System.out.println("Cuentas activas en Banamex despues de la apertura de dos clientes: ");
		System.out.println("Cuentas activas en Banamex: " + banamex.getListaDeCuentas().toString());
		for(int i = 0; i<banamex.getListaDeCuentas().size(); i++) {
			System.out.println(banamex.getListaDeCuentas().get(i).toString());
		}
		CuentaDeBanco cuentaClienteValido = banamex.getListaDeCuentas().get(0);
		CuentaDeBanco cuentaClienteValido2 = banamex.getListaDeCuentas().get(1);
		
		System.out.println("\nRetiro de $100 de cuentaClienteValido con saldo disponible de $1000:");
		cuentaClienteValido.retirar(100);
		
		System.out.println("\nIntento de retiro de $901 de cuentaClienteValido con saldo disponible de $900:");
		cuentaClienteValido.retirar(901);
		
		System.out.println("\nTransferencia de cuentaClienteValido por un monto de 800 a la cuentaClienteValido2");
		cuentaClienteValido.transferir(cuentaClienteValido2, 800);
		System.out.println("Saldo de cuentaClienteValido: " + cuentaClienteValido.getSaldoDisponible());
		System.out.println("Saldo de cuentaClienteValido2: " + cuentaClienteValido2.getSaldoDisponible());
		
		System.out.println("\nIntento de transferencia de cuentaClienteValido por un monto de 101 a la cuentaClienteValido2");
		cuentaClienteValido.transferir(cuentaClienteValido2, 101);
		System.out.println("Saldo de cuentaClienteValido: " + cuentaClienteValido.getSaldoDisponible());
		System.out.println("Saldo de cuentaClienteValido2: " + cuentaClienteValido2.getSaldoDisponible());
		
		System.out.println("\nIntento de deposito por una cantidad que hace exceder saldo maximo de cuentaClienteValido");
		cuentaClienteValido.depositar(22901);
		
		System.out.println("\nDeposito por 15000 a cuentaClienteValido");
		cuentaClienteValido.depositar(15000);
		
		System.out.println("\nIntento de transferencia de cuentaClienteValido por un monto de 101 a la cuentaClienteValido2");
		cuentaClienteValido.transferir(cuentaClienteValido2, 101);
		System.out.println("Saldo de cuentaClienteValido: " + cuentaClienteValido.getSaldoDisponible());
		System.out.println("Saldo de cuentaClienteValido2: " + cuentaClienteValido2.getSaldoDisponible());
	}
}
