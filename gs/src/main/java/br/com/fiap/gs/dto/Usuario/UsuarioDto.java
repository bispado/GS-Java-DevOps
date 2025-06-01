package br.com.fiap.gs.dto.Usuario;

import br.com.fiap.gs.model.enums.PerfilUsuario;
import jakarta.persistence.*;

public record UsuarioDto(

        Long id,

        String nome,


        String email,

        String senha,


        PerfilUsuario perfil
) {

}
