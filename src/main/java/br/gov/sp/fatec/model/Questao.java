package br.gov.sp.fatec.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.view.View;

@Entity
@Table(name = "QUE_QUESTAO")
public class Questao {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@JsonView({View.Tudo.class})
	@Column(name = "QUE_ID")
	private Long id;

	@Column(name = "QUE_PERGUNTA", unique = false, length = 300, nullable = false)
	@JsonView({View.Tudo.class, View.Comum.class})
	private String pergunta;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "questao")
	@JsonView({View.Tudo.class, View.Comum.class})
	private List<Opcao> opcoes = new LinkedList<Opcao>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public List<Opcao> getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(List<Opcao> opcoes) {
		this.opcoes = opcoes;
	}
	
}
