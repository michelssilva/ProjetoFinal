package br.gov.sp.fatec.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.Opcao;
import br.gov.sp.fatec.model.Questao;

public interface OpcaoRepository extends CrudRepository<Opcao, Long> {

	public List<Opcao> findByQuestao(Questao questao);

	public List<Opcao> findAll();

	public Opcao findById(Long id);

}
