package ar.com.codigomariano.enums;

public enum OpcionBinaria implements Opcion{
	V("Verdadero", true), 
	F("Falso", false);
	
	private String texto;
	private boolean valor;
	
	
	private OpcionBinaria(String texto, boolean valor) {
		this.texto = texto;
		this.valor = valor;
	}
	
	
	public static OpcionBinaria getFromValue(boolean value) {
		return (value ? OpcionBinaria.V : OpcionBinaria.F);
	}
	
	public boolean getValor() {
		return valor;
	}

	@Override
	public String displayText() {
		return this.texto;
	}
}
