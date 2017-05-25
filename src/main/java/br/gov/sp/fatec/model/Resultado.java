package br.gov.sp.fatec.model;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.view.View;

public class Resultado {

	@JsonView({ View.Tudo.class, View.Comum.class })
	private Long resultado;
	
	public Long getResultado() {
		return resultado;
	}

	public void setResultado(Long resultado) {
		this.resultado = resultado;
	}

}
