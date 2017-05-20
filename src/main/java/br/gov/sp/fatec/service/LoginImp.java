package br.gov.sp.fatec.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.cripto.Seguranca;
import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.repository.UsuarioRepository;

@Service("loginService")
public class LoginImp implements LoginService {

	@Autowired
	private UsuarioRepository usuarioRepo;

	public UsuarioRepository getUsuarioRepo() {
		return usuarioRepo;
	}

	public void setUsuarioRepo(UsuarioRepository usuarioRepo) {
		this.usuarioRepo = usuarioRepo;
	}

	public Usuario logar(String email, String senha) {
		Seguranca seguranca = new Seguranca();
		Usuario usuario = usuarioRepo.findByEmail(email);
		try {
			if (usuario.getSenha().equals(seguranca.criptografarSenha(senha))) {
				usuario.setStatus(true);
				return usuario;
			}
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return null;
	}

}
