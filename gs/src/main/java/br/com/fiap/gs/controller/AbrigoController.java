package br.com.fiap.gs.controller;


import br.com.fiap.gs.dto.abrigo.AbrigoDto;
import br.com.fiap.gs.dto.abrigo.AbrigoGetDto;
import br.com.fiap.gs.service.AbrigoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.transaction.Transactional;
import java.util.List;


@RestController
@RequestMapping("/abrigo")
public class AbrigoController {

    @Autowired
    private AbrigoService abrigoService;

    @PostMapping
    @Transactional
    public void post(@RequestBody @Valid AbrigoDto dto) {
        abrigoService.save(dto);
    }

    @GetMapping
    public List<AbrigoGetDto> getAll() {
        return abrigoService.getAll();
    }
    @GetMapping("/{id}")
    public AbrigoGetDto getById(@PathVariable Long id) {
        return abrigoService.getId(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public AbrigoGetDto update(@PathVariable Long id, @RequestBody AbrigoGetDto dto){
        return abrigoService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        abrigoService.delete(id);
    }
}
