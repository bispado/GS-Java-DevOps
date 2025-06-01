package br.com.fiap.gs.dto.necessidade;


import br.com.fiap.gs.model.enums.PrioridadeNecessidade;
import br.com.fiap.gs.model.enums.StatusNecessidade;
import br.com.fiap.gs.model.enums.TipoNecessidade;


public record NecessidadeDto(
        Long id,

        TipoNecessidade tipo,

        Integer quantidade,


        PrioridadeNecessidade prioridade,


        StatusNecessidade status


) {

}
