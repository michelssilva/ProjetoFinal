package br.gov.sp.fatec.service;

import br.gov.sp.fatec.model.Usuario;

public interface LoginService {
	
	public Usuario logar(String email, String senha);

}
