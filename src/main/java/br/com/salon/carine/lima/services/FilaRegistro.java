package br.com.salon.carine.lima.services;

public interface FilaRegistro<T> {
	
	T proximo(T tipo);
	T anterior(T tipo);
	boolean isUnicoRegistroLista();
	boolean isAtualUltimoLista(T tipo);
	boolean isAtualPrimeiroLista(T tipo);
	T buscarPrimeiroLista();
	T buscarUltimoLista();

}
