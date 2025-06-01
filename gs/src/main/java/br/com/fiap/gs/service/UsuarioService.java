package br.com.fiap.gs.service;

import br.com.fiap.gs.dto.Usuario.UsuarioDto;
import br.com.fiap.gs.dto.Usuario.UsuarioGetDto;
import br.com.fiap.gs.repository.UsuarioRepository;
import br.com.fiap.gs.model.entities.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;


    public Usuario save(UsuarioDto dto) {

        return usuarioRepository.save(new Usuario(dto));
    }

    public List<UsuarioGetDto> getAll() {
        return usuarioRepository.findAll().stream().map(UsuarioGetDto::new).toList();
    }

    public UsuarioGetDto getId(Long id) {

        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()) {
            Usuario u = usuario.get();

            return new UsuarioGetDto(u.getId(), u.getNome(), u.getEmail(), u.getSenha(),u.getPerfil());
        } else {
            return null;
        }
    }

    public UsuarioGetDto update(Long id, UsuarioGetDto dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        usuario.setPerfil(dto.perfil());


        Usuario atualizado = usuarioRepository.save(usuario);
        return new UsuarioGetDto(atualizado);
    }


    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }


}