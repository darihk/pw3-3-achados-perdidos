package br.com.brunosarah.achadosperdidos.dto;

import br.com.brunosarah.achadosperdidos.enums.StatusItemEnum;

import java.time.LocalDateTime;

public record ItemPerdidoDTO(
        Long id,
        String nomeItem,
        String localEncontrado,
        LocalDateTime dataAchado,
        StatusItemEnum statusItem,
        String descricaoDevolucao
) { }
