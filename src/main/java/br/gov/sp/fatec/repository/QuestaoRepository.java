package br.gov.sp.fatec.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.Opcao;
import br.gov.sp.fatec.model.Questao;

public interface QuestaoRepository extends CrudRepository<Questao, Long> {

	public List<Questao> findAll();

	public Questao findById(Long id);

}
