package br.gov.sp.fatec.model;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.cripto.Seguranca;
import br.gov.sp.fatec.view.View;

@Entity
@Table(name = "USU_USUARIO")
public class Usuario {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@JsonView({View.Tudo.class})
	@Column(name = "USU_ID")
	private Long id;
	
	@Column(name = "USU_EMAIL", unique = true, length = 30, nullable = false)
	@JsonView({View.Tudo.class, View.Comum.class})
	private String email;
	
	@Column(name = "USU_SENHA", unique = false, length = 300, nullable = false)
	private String senha;
	
	@Column(name = "USU_NOME", unique = false, length = 50, nullable = false)
	@JsonView({View.Tudo.class, View.Comum.class})
	private String nome;
	
	@Transient
	@JsonView({View.Tudo.class, View.Comum.class})
	private boolean status;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		Seguranca seguranca = new Seguranca();
		try {
			this.senha = seguranca.criptografarSenha(senha);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
