package br.gov.sp.fatec.model;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.view.View;

public class Resultado {

	@JsonView({ View.Tudo.class, View.Comum.class })
	Integer resultado;

	public Integer getResultado() {
		return resultado;
	}

	public void setResultado(Integer resultado) {
		this.resultado = resultado;
	}

}
