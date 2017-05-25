package br.gov.sp.fatec.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.model.Login;
import br.gov.sp.fatec.model.Questao;
import br.gov.sp.fatec.model.Respostas;
import br.gov.sp.fatec.model.Resultado;
import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.repository.QuestaoRepository;
import br.gov.sp.fatec.repository.UsuarioRepository;
import br.gov.sp.fatec.service.LoginService;
import br.gov.sp.fatec.service.ResultadoService;
import br.gov.sp.fatec.view.View;

@RestController
@RequestMapping(value = "/agua")
public class ProjetoController {

	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private QuestaoRepository questaoRepo;
	@Autowired
	private ResultadoService resultadoService;
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/perguntas")
	@JsonView(View.Tudo.class)
	public synchronized ResponseEntity<List<Questao>> get() {
		List<Questao> questao = questaoRepo.findAll();

		return new ResponseEntity<List<Questao>>(questao, HttpStatus.OK);
	}

	@RequestMapping(value = "/getById")
	@JsonView(View.Tudo.class)
	public synchronized ResponseEntity<Usuario> get(@RequestParam(value = "id", defaultValue = "1") Long id) {
		Usuario usuario = usuarioRepo.findById(id);
		if (usuario == null) {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(View.Tudo.class)
	@ResponseStatus(HttpStatus.CREATED)
	public synchronized Usuario salvarUsuario(@RequestBody Usuario usuario, HttpServletRequest request,
			HttpServletResponse response) {

		usuario = usuarioRepo.save(usuario);
		if (usuario.getId() != null) {
			usuario.setStatus(true);
		}
		response.addHeader("Location", request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/agua/getById?id=" + usuario.getId());
		return usuario;
	}
	
	@RequestMapping(value = "/logar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(View.Tudo.class)
	public synchronized Usuario logar(@RequestBody Login login, HttpServletRequest request,
			HttpServletResponse response) {

		Usuario usuario = loginService.logar(login.getEmail(), login.getSenha());
		response.addHeader("Location", request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/agua/getById?id=" + usuario.getId());
		return usuario;
	}

	@RequestMapping(value = "/responder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(View.Tudo.class)
	public synchronized ResponseEntity<Resultado> responder(@RequestBody Respostas respostas, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("achou");
		Resultado r = resultadoService.resultado(respostas.getAlternativas());

		return new ResponseEntity<Resultado>(r, HttpStatus.OK);
	}

	public UsuarioRepository getUsuarioRepo() {
		return usuarioRepo;
	}

	public void setUsuarioRepo(UsuarioRepository usuarioRepo) {
		this.usuarioRepo = usuarioRepo;
	}

	public QuestaoRepository getQuestaoRepo() {
		return questaoRepo;
	}

	public void setQuestaoRepo(QuestaoRepository questaoRepo) {
		this.questaoRepo = questaoRepo;
	}

	public ResultadoService getResultadoService() {
		return resultadoService;
	}

	public void setResultadoService(ResultadoService resultadoService) {
		this.resultadoService = resultadoService;
	}
	
	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

}
