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

import br.gov.sp.fatec.model.Questao;
import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.repository.QuestaoRepository;
import br.gov.sp.fatec.repository.UsuarioRepository;
import br.gov.sp.fatec.view.View;

@RestController
@RequestMapping(value = "/agua")
public class ProjetoController {
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private QuestaoRepository questaoRepo;
	@RequestMapping(value = "/perguntas")
	@JsonView(View.Tudo.class)
	public ResponseEntity<List<Questao>> get() {
		List<Questao> questao = questaoRepo.findAll();
		
		return new ResponseEntity<List<Questao>>(questao, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getById")
	@JsonView(View.Tudo.class)
	public ResponseEntity<Usuario> get(@RequestParam(value = "id", defaultValue = "1") Long id) {
		Usuario usuario = usuarioRepo.findById(id);
		if (usuario == null) {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(View.Tudo.class)
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvarUsuario(@RequestBody Usuario usuario, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("\n\n\n\n" + usuario.getNome() + "\n\n\n");
		usuario = usuarioRepo.save(usuario);
		if(usuario.getId() != null){
			usuario.setStatus(true);
		}
		response.addHeader("Location", request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/agua/getById?id=" + usuario.getId());
		return usuario;
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


}
