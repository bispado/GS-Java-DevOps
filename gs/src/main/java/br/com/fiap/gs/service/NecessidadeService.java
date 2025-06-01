package br.com.fiap.gs.service;

import br.com.fiap.gs.dto.necessidade.NecessidadeDto;
import br.com.fiap.gs.dto.necessidade.NecessidadeGetDto;
import br.com.fiap.gs.model.entities.Necessidade;
import br.com.fiap.gs.repository.NecessidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NecessidadeService {
    @Autowired
    private NecessidadeRepository necessidadeRepository;


    public Necessidade save(NecessidadeDto dto) {

        return necessidadeRepository.save(new Necessidade(dto));
    }

    public List<NecessidadeGetDto> getAll() {
        return necessidadeRepository.findAll().stream().map(NecessidadeGetDto::new).toList();
    }

    public NecessidadeGetDto getId(Long id) {

        Optional<Necessidade> necessidade = necessidadeRepository.findById(id);

        if (necessidade.isPresent()) {
            Necessidade n = necessidade.get();

            return new NecessidadeGetDto(n.getId(), n.getTipo(), n.getQuantidade(),n.getPrioridade(), n.getStatus(), n.getAbrigo());
        } else {
            return null;
        }

    }

    public NecessidadeGetDto update(Long id, NecessidadeGetDto dto) {
        Necessidade necessidade = necessidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Necessidade não encontrado"));

        necessidade.setId(dto.id());
        necessidade.setTipo(dto.tipo());
        necessidade.setQuantidade(dto.quantidade());
        necessidade.setPrioridade(dto.prioridade());
        necessidade.setStatus(dto.status());
        necessidade.setAbrigo(dto.abrigo());


        Necessidade atualizado = necessidadeRepository.save(necessidade);
        return new NecessidadeGetDto(atualizado);
    }



    public void delete(Long id) {
        necessidadeRepository.deleteById(id);
    }



}
