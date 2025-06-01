package br.com.fiap.gs.dto.abrigo;

import br.com.fiap.gs.model.entities.Abrigo;
import br.com.fiap.gs.model.entities.Necessidade;
import br.com.fiap.gs.model.entities.Usuario;
import br.com.fiap.gs.model.enums.PerfilUsuario;
import br.com.fiap.gs.model.enums.TipoNecessidade;

import java.util.List;

public record AbrigoGetDto(
        Long id,
        String nome,
        String endereco,
        Integer capacidadeMaxima,
        List<String> usuario,
        List<TipoNecessidade> necessidades
) {
    public AbrigoGetDto(Abrigo abrigo) {
        this(
                abrigo.getId(),
                abrigo.getNome(),
                abrigo.getEndereco(),
                abrigo.getCapacidadeMaxima(),
                abrigo.getUsuario().stream().map(Usuario::getNome).toList(),
                abrigo.getNecessidades().stream().map(Necessidade::getTipo).toList()
        );
    }


}