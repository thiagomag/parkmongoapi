package com.fiap.parkmongoapi.dto;

import java.util.List;

public record PageResponseDTO<T>(
        List<T> content,      // Lista de itens da página atual
        long totalElements,   // Total de elementos disponíveis
        int totalPages,       // Total de páginas
        int page,             // Página atual
        int size              // Tamanho da página
) { }
