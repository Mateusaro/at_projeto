package com.example.atribuicao.service;

import com.example.atribuicao.DTO.AtribuicaoDTO;
import com.example.atribuicao.DTO.TarefaDTO; // Importar o DTO
import com.example.atribuicao.feign.BancoClient; // Importar o Feign Client do banco de dados
import com.example.atribuicao.feign.TarefaClient; // Importar o Feign Client de tarefas
import com.example.atribuicao.models.Atribuicao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AtribuicaoService {

    @Autowired
    private BancoClient bancoClient;

    @Autowired
    private TarefaClient tarefaClient;

    @Autowired
    private NotificacaoService notificacaoService; // Injetando o serviço de notificação

    public Atribuicao atribuirTarefa(AtribuicaoDTO atribuicaoDTO) {
        TarefaDTO tarefa = tarefaClient.buscarTarefaPorId(atribuicaoDTO.getIdTarefa());

        if (tarefa == null) {
            throw new RuntimeException("Tarefa não encontrada");
        }

        // Criar a atribuição com a tarefa existente
        Atribuicao atribuicao = new Atribuicao();
        atribuicao.setIdTarefa(atribuicaoDTO.getIdTarefa());
        atribuicao.setUsuarioAtribuido(atribuicaoDTO.getUsuarioAtribuido());
        atribuicao.setDataAtribuicao(LocalDateTime.now());

        // Salvar a atribuição no banco de dados
        Atribuicao atribuicaoSalva = bancoClient.criarAtribuicao(atribuicao);

        // Enviar a notificação ao usuário
        //notificacaoService.notificarUsuario(atribuicaoDTO.getUsuarioAtribuido(), tarefa.getTitulo()); // Assumindo que TarefaDTO tem um método getTitulo()

        return atribuicaoSalva;
    }

    public List<Atribuicao> listarAtribuicoes() {
        return bancoClient.listarAtribuicoes();
    }
}
