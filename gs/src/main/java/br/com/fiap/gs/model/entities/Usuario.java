package br.com.fiap.gs.model.entities;


import br.com.fiap.gs.dto.Usuario.UsuarioDto;
import br.com.fiap.gs.dto.abrigo.AbrigoDto;
import jakarta.persistence.*;
import br.com.fiap.gs.model.enums.PerfilUsuario;


@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;

    public Abrigo getAbrigo() {
        return abrigo;
    }

    public void setAbrigo(Abrigo abrigo) {
        this.abrigo = abrigo;
    }

    @ManyToOne
    @JoinColumn(name = "id_abrigo")
    private Abrigo abrigo;

    @Enumerated(EnumType.STRING)
    private PerfilUsuario perfil;

    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String senha, PerfilUsuario perfil) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
    }

    public Usuario(UsuarioDto dto) {
        this.nome = dto.nome();
        this.email = dto.email();
        this.senha = dto.senha();
        this.perfil = dto.perfil();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilUsuario perfil) {
        this.perfil = perfil;
    }
}

