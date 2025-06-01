package br.com.fiap.gs.service;


import br.com.fiap.gs.dto.abrigo.AbrigoDto;
import br.com.fiap.gs.dto.abrigo.AbrigoGetDto;
import br.com.fiap.gs.model.entities.Abrigo;
import br.com.fiap.gs.model.entities.Necessidade;
import br.com.fiap.gs.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import br.com.fiap.gs.model.entities.Usuario;

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository abrigoRepository;


    public Abrigo save(AbrigoDto dto) {

        return abrigoRepository.save(new Abrigo(dto));
    }

    public List<AbrigoGetDto> getAll() {
        return abrigoRepository.findAll().stream().map(AbrigoGetDto::new).toList();
    }

    public AbrigoGetDto getId(Long id) {

        Optional<Abrigo> abrigo = abrigoRepository.findById(id);

        if (abrigo.isPresent()) {
            Abrigo a = abrigo.get();

            return new AbrigoGetDto(a.getId(), a.getNome(), a.getEndereco(), a.getCapacidadeMaxima(),
                    a.getUsuario()
                    .stream().map(Usuario::getNome).toList(),
                    a.getNecessidades().stream().map(Necessidade::getTipo).toList());
        } else {
            return null;
        }
    }

    public AbrigoGetDto update(Long id, AbrigoGetDto dto) {
        Abrigo abrigo = abrigoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Abrigo não encontrado"));

        abrigo.setNome(dto.nome());
        abrigo.setEndereco(dto.endereco());
        abrigo.setCapacidadeMaxima(dto.capacidadeMaxima());

        /*abrigo.setUsuario(dto.usuario());
        abrigo.setNecessidades(dto.necessidades());*/

        Abrigo atualizado = abrigoRepository.save(abrigo);
        return new AbrigoGetDto(atualizado);
    }


    public void delete(Long id) {
        abrigoRepository.deleteById(id);
    }


}


