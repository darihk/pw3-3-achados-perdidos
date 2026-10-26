package br.com.brunosarah.achadosperdidos.entity;

import br.com.brunosarah.achadosperdidos.enums.StatusItemEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Item_perdido")
public class ItemPerdido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private Long id;

    @Column(name = "nome_item")
    private String nomeItem;

    @Column(name = "local_encontrado")
    private String localEncontrado;

    @Column(name = "data_achado")
    private LocalDateTime dataAchado;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_item")
    private StatusItemEnum statusItem;

    @Column(name = "descricao_devolucao")
    private String descricaoDevolucao;
}
