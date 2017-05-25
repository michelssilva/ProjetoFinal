package br.gov.sp.fatec.model;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.view.View;

public class Respostas {
	
	@JsonView({View.Tudo.class, View.Comum.class})
	private List<Long> alternativas;

	public List<Long> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(List<Long> alternativas) {
		this.alternativas = alternativas;
	}
	
	public void preencher(){
		
		List<Long> l = new LinkedList<Long>();
		for(Long i = (long) 10; i < 100; i++){
			l.add(i);
		}
		
		this.alternativas = l;
		
	}

}
