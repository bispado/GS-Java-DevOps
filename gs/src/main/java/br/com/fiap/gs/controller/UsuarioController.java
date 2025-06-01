package br.com.fiap.gs.controller;

import br.com.fiap.gs.dto.Usuario.UsuarioDto;
import br.com.fiap.gs.dto.Usuario.UsuarioGetDto;
import br.com.fiap.gs.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public void post(@RequestBody @Valid UsuarioDto dto) {
        usuarioService.save(dto);
    }

    @GetMapping
    public List<UsuarioGetDto> getAll() {
        return usuarioService.getAll();
    }
    @GetMapping("/{id}")
    public UsuarioGetDto getById(@PathVariable Long id) {
        return usuarioService.getId(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public UsuarioGetDto update(@PathVariable Long id, @RequestBody UsuarioGetDto dto){
        return usuarioService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        usuarioService.delete(id);
    }
}