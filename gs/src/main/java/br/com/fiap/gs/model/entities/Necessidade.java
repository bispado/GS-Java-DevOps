package br.com.fiap.gs.model.entities;



import br.com.fiap.gs.dto.abrigo.AbrigoDto;
import br.com.fiap.gs.dto.necessidade.NecessidadeDto;
import br.com.fiap.gs.model.entities.Abrigo;
import br.com.fiap.gs.model.enums.PrioridadeNecessidade;
import br.com.fiap.gs.model.enums.StatusNecessidade;
import br.com.fiap.gs.model.enums.TipoNecessidade;
import jakarta.persistence.Entity;



import jakarta.persistence.*;

@Entity
@Table(name = "TAB_NECESSIDADE")
@SequenceGenerator(name = "filial", sequenceName = "SQ_TAB_NECESSIDADE", allocationSize = 1)
public class Necessidade {

    @Id
    @Column(name = "id_filial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "filial")
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoNecessidade tipo;

    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    private PrioridadeNecessidade prioridade;

    @Enumerated(EnumType.STRING)
    private StatusNecessidade status;

    @ManyToOne
    @JoinColumn(name = "abrigo_id")
    private Abrigo abrigo;

    public Necessidade() {
    }

    public Necessidade(Long id, TipoNecessidade tipo, Integer quantidade, PrioridadeNecessidade prioridade, StatusNecessidade status, Abrigo abrigo) {
        this.id = id;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.prioridade = prioridade;
        this.status = status;
        this.abrigo = abrigo;
    }

    public Necessidade(NecessidadeDto dto) {
        this.tipo = dto.tipo();
        this.quantidade = dto.quantidade();
        this.prioridade = dto.prioridade();
        this.status = dto.status();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoNecessidade getTipo() {
        return tipo;
    }

    public void setTipo(TipoNecessidade tipo) {
        this.tipo = tipo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public PrioridadeNecessidade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(PrioridadeNecessidade prioridade) {
        this.prioridade = prioridade;
    }

    public StatusNecessidade getStatus() {
        return status;
    }

    public void setStatus(StatusNecessidade status) {
        this.status = status;
    }

    public Abrigo getAbrigo() {
        return abrigo;
    }

    public void setAbrigo(Abrigo abrigo) {
        this.abrigo = abrigo;
    }
}


