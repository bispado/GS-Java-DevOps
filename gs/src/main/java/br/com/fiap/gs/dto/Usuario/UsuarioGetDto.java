package br.com.fiap.gs.dto.Usuario;

import br.com.fiap.gs.model.entities.Usuario;
import br.com.fiap.gs.model.enums.PerfilUsuario;

public record UsuarioGetDto(
        Long id,

        String nome,


        String email,

        String senha,


        PerfilUsuario perfil
) {
    public UsuarioGetDto(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getPerfil());
    }
}
