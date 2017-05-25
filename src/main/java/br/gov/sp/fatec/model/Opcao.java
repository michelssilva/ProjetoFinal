package br.gov.sp.fatec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.view.View;

@Entity
@Table(name = "OP_OPCAO")
public class Opcao {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "OP_ID")
	private Long id;
	
	@Column(name = "OP_ALTERNATIVA", unique = false, length = 4, nullable = false)
	@JsonView({View.Tudo.class, View.Comum.class})
	private Long alternativa;
	
	@Column(name = "OP_CONSUMO", length = 4, nullable = false)
	private Integer consumo;

	@ManyToOne
	@JoinColumn(name="QUE_ID")
	private Questao questao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}
	
	public Long getAlternativa() {
		return alternativa;
	}

	public void setAlternativa(Long alternativa) {
		this.alternativa = alternativa;
	}
	
	public Integer getConsumo() {
		return consumo;
	}

	public void setConsumo(Integer consumo) {
		this.consumo = consumo;
	}

}
