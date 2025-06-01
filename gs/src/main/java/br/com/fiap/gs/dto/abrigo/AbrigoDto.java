package br.com.fiap.gs.dto.abrigo;

import br.com.fiap.gs.model.entities.Necessidade;
import br.com.fiap.gs.model.entities.Usuario;

import java.util.List;

public record AbrigoDto(
        String nome,

        String endereco,

        Integer capacidadeMaxima,

        List<Usuario> usuario,
        List<Necessidade> necessidades
) {
}
