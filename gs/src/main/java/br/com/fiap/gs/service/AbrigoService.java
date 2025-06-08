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
import br.com.fiap.gs.repository.NecessidadeRepository;
import br.com.fiap.gs.repository.UsuarioRepository;
import br.com.fiap.gs.dto.Usuario.UsuarioDto;
import br.com.fiap.gs.dto.necessidade.NecessidadeDto;
import java.util.ArrayList;

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository abrigoRepository;
    // @Autowired
    // private UsuarioRepository usuarioRepository;
    // @Autowired
    // private NecessidadeRepository necessidadeRepository;


    public Abrigo save(AbrigoDto dto) {
        Abrigo abrigo = new Abrigo(
                dto.nome(),
                dto.endereco(),
                dto.capacidadeMaxima(),
                new ArrayList<>(), // Inicializa a lista de usuarios
                new ArrayList<>() // Inicializa a lista de necessidades
        );

        if (dto.usuario() != null) {
            for (UsuarioDto usuarioDto : dto.usuario()) {
                Usuario usuario = new Usuario(usuarioDto);
                usuario.setAbrigo(abrigo);
                abrigo.getUsuario().add(usuario);
            }
        }

        if (dto.necessidades() != null) {
            for (NecessidadeDto necessidadeDto : dto.necessidades()) {
                Necessidade necessidade = new Necessidade(necessidadeDto);
                necessidade.setAbrigo(abrigo);
                abrigo.getNecessidades().add(necessidade);
            }
        }

        return abrigoRepository.save(abrigo);
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


