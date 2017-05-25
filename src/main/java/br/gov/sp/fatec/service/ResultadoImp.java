package br.gov.sp.fatec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.model.Opcao;
import br.gov.sp.fatec.model.Questao;
import br.gov.sp.fatec.model.Resultado;
import br.gov.sp.fatec.repository.QuestaoRepository;

@Service("resultadoService")
public class ResultadoImp implements ResultadoService {
	
	@Autowired
	private QuestaoRepository questaoRepo;

	public QuestaoRepository getQuestaoRepo() {
		return questaoRepo;
	}

	public void setQuestaoRepo(QuestaoRepository questaoRepo) {
		this.questaoRepo = questaoRepo;
	}

	
	public Resultado resultado(List<Long> inteiros) {

		Long resultado = (long) 0;
		int index = 0;

		List<Questao> questoes = questaoRepo.findAll();
		for (Questao q : questoes) {

			for (Opcao op : q.getOpcoes()) {
				if(op.getAlternativa() == inteiros.get(index)){
					resultado += op.getConsumo();
				}
			}
			
			index++;
		}
		
		Resultado r = new Resultado();
		r.setResultado(resultado);

		return r;
	}

}
