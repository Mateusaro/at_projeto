package com.example.atribuicao.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Atribuicao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long idTarefa;

    @NotNull
    private String usuarioAtribuido;

    private LocalDateTime dataAtribuicao;

    public Atribuicao() {}

    public Atribuicao(Long idTarefa, String usuarioAtribuido, LocalDateTime dataAtribuicao) {
        this.idTarefa = idTarefa;
        this.usuarioAtribuido = usuarioAtribuido;
        this.dataAtribuicao = dataAtribuicao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(Long idTarefa) {
        this.idTarefa = idTarefa;
    }

    public String getUsuarioAtribuido() {
        return usuarioAtribuido;
    }

    public void setUsuarioAtribuido(String usuarioAtribuido) {
        this.usuarioAtribuido = usuarioAtribuido;
    }

    public LocalDateTime getDataAtribuicao() {
        return dataAtribuicao;
    }

    public void setDataAtribuicao(LocalDateTime dataAtribuicao) {
        this.dataAtribuicao = dataAtribuicao;
    }
}
