package br.com.fiap.gs.dto.abrigo;

// import br.com.fiap.gs.model.entities.Necessidade;
// import br.com.fiap.gs.model.entities.Usuario;

import java.util.List;
import br.com.fiap.gs.dto.Usuario.UsuarioDto;
import br.com.fiap.gs.dto.necessidade.NecessidadeDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record AbrigoDto(
        // Long id, // Removido para a criação de novos abrigos
        String nome,

        String endereco,

        Integer capacidadeMaxima,

        @NotNull @Valid
        List<UsuarioDto> usuario,
        @NotNull @Valid
        List<NecessidadeDto> necessidades
) {
}
