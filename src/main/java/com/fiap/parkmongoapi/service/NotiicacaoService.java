package com.fiap.parkmongoapi.service;

public interface NotiicacaoService {

    void verificarNotificacoes();

    void enviarNotificacao(String email, String mensagem);
}
