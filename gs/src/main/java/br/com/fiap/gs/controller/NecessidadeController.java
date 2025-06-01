package br.com.fiap.gs.controller;


import br.com.fiap.gs.dto.necessidade.NecessidadeDto;
import br.com.fiap.gs.dto.necessidade.NecessidadeGetDto;
import br.com.fiap.gs.service.NecessidadeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/necessidade")
public class NecessidadeController {

    @Autowired
    private NecessidadeService necessidadeService;

    @PostMapping
    @Transactional
    public void post(@RequestBody @Valid NecessidadeDto dto) {
        necessidadeService.save(dto);
    }

    @GetMapping
    public List<NecessidadeGetDto> getAll() {
        return necessidadeService.getAll();
    }
    @GetMapping("/{id}")
    public NecessidadeGetDto getById(@PathVariable Long id) {
        return necessidadeService.getId(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public NecessidadeGetDto update(@PathVariable Long id, @RequestBody NecessidadeGetDto dto){
        return necessidadeService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        necessidadeService.delete(id);
    }
}