package ar.com.codigomariano.enums;

public enum OpcionMultiple implements Opcion{
	A, B, C, D;

	
	@Override
	public String displayText() {
		return name();
	}
}
