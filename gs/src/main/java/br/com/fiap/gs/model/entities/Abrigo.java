package br.com.fiap.gs.model.entities;

import br.com.fiap.gs.dto.abrigo.AbrigoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "TAB_ABRIGO")
@SequenceGenerator(name = "abrigo", sequenceName = "SQ_TAB_ABRIGO", allocationSize = 1)
public class Abrigo {

    @Id @Column(name = "id_abrigo")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "abrigo")
    private Long id;

    private String nome;

    private String endereco;

    private Integer capacidadeMaxima;

    @OneToMany(mappedBy = "abrigo", cascade = CascadeType.ALL)
    private List<Usuario> usuario;

    @OneToMany(mappedBy = "abrigo", cascade = CascadeType.ALL)
    private List<Necessidade> necessidades;

    public Abrigo() {
    }

    public Abrigo(Long id, String nome, String endereco, Integer capacidadeMaxima, List<Usuario> usuario, List<Necessidade> necessidades) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.capacidadeMaxima = capacidadeMaxima;
        this.usuario = usuario;
        this.necessidades = necessidades;
    }

    public Abrigo(String nome, String endereco, Integer capacidadeMaxima, List<Usuario> usuario, List<Necessidade> necessidades) {
        this.nome = nome;
        this.endereco = endereco;
        this.capacidadeMaxima = capacidadeMaxima;
        this.usuario = usuario;
        this.necessidades = necessidades;
    }

    public Abrigo(AbrigoDto dto) {
        this.nome = dto.nome();
        this.endereco = dto.endereco();
        this.capacidadeMaxima = dto.capacidadeMaxima();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(Integer capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public List<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<Usuario> usuario) {
        this.usuario = usuario;
    }

    public List<Necessidade> getNecessidades() {
        return necessidades;
    }

    public void setNecessidades(List<Necessidade> necessidades) {
        this.necessidades = necessidades;
    }



}
