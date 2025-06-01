package br.com.fiap.gs.dto.necessidade;

import br.com.fiap.gs.model.entities.Abrigo;
import br.com.fiap.gs.model.entities.Necessidade;
import br.com.fiap.gs.model.enums.PrioridadeNecessidade;
import br.com.fiap.gs.model.enums.StatusNecessidade;
import br.com.fiap.gs.model.enums.TipoNecessidade;



public record NecessidadeGetDto(
        Long id,

        TipoNecessidade tipo,

        Integer quantidade,


        PrioridadeNecessidade prioridade,


        StatusNecessidade status,


        Abrigo abrigo
) {
    public NecessidadeGetDto(Necessidade necessidade) {
        this(necessidade.getId(), necessidade.getTipo(), necessidade.getQuantidade(),necessidade.getPrioridade(),necessidade.getStatus(),necessidade.getAbrigo());
    }

}
