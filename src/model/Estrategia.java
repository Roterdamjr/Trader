package model;

import java.math.BigDecimal;

public class Estrategia {
	String ativo;
	BigDecimal start;
	BigDecimal stop;
	BigDecimal gain;
	BigDecimal gainParcial;
	BigDecimal quantidadeSugerida;
	BigDecimal quantidade;

	public Estrategia(){
		
	}


	public Estrategia(String ativo, BigDecimal start, BigDecimal stop,
			BigDecimal gain, BigDecimal gainParcial,
			BigDecimal quantidadeSugerida, BigDecimal quantidade) {
		super();
		this.ativo = ativo;
		this.start = start;
		this.stop = stop;
		this.gain = gain;
		this.gainParcial = gainParcial;
		this.quantidadeSugerida = quantidadeSugerida;
		this.quantidade = quantidade;
	}


	public BigDecimal getQuantidadeSugerida() {
		return quantidadeSugerida;
	}


	public void setQuantidadeSugerida(BigDecimal quantidadeSugerida) {
		this.quantidadeSugerida = quantidadeSugerida;
	}


	public BigDecimal getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}


	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public BigDecimal getStart() {
		return start;
	}

	public void setStart(BigDecimal start) {
		this.start = start;
	}


	public BigDecimal getStop() {
		return stop;
	}

	public void setStop(BigDecimal stop) {
		this.stop = stop;
	}


	public BigDecimal getGain() {
		return gain;
	}

	public void setGain(BigDecimal gain) {
		this.gain = gain;
	}

	public BigDecimal getGainParcial() {
		return gainParcial;
	}

	public void setGainParcial(BigDecimal gainParcial) {
		this.gainParcial = gainParcial;
	}


	public BigDecimal getValor() {
		return quantidadeSugerida;
	}

	public void setValor(BigDecimal valor) {
		this.quantidadeSugerida = valor;
	}

	@Override
	public String toString() {
		return "Negociacao [ativo=" + ativo + ", start=" + start + ", stop="
				+ stop + ", gain=" + gain + ", gainParcial=" + gainParcial
				+ "]";
	}
	
	
	
}
