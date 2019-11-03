package model;

import java.math.BigDecimal;

public class Operacao {
	String ativo;
	BigDecimal vlorCorrente;
	BigDecimal start;
	BigDecimal stop;
	BigDecimal gain;
	BigDecimal gainParcial;
	BigDecimal quantidade;
	BigDecimal risco;

	public Operacao(){		
	}

	public Operacao(String ativo, BigDecimal vlorCorrente, BigDecimal start,
			BigDecimal stop, BigDecimal gainParcial,BigDecimal quantidade,
			BigDecimal gain, BigDecimal risco) {
		super();
		this.ativo = ativo;
		this.vlorCorrente = vlorCorrente;
		this.start = start;
		this.stop = stop;
		this.gain = gain;
		this.gainParcial = gainParcial;
		this.quantidade = quantidade;
		this.risco = risco;
	}

	public BigDecimal getVlorCorrente() {
		return vlorCorrente;
	}


	public void setVlorCorrente(BigDecimal vlorCorrente) {
		this.vlorCorrente = vlorCorrente;
	}

	public BigDecimal getRisco() {
		return risco;
	}

	public void setRisco(BigDecimal risco) {
		this.risco = risco;
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



	@Override
	public String toString() {
		return "Negociacao [ativo=" + ativo + ", start=" + start + ", stop="
				+ stop + ", gain=" + gain + ", gainParcial=" + gainParcial
				+ "]";
	}
	
	
	
}
