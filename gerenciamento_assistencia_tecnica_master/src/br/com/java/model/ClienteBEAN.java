package br.com.java.model;

public class ClienteBEAN extends PessoaBEAN{

	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}
	

}
