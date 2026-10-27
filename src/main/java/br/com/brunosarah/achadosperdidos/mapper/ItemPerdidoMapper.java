package br.com.brunosarah.achadosperdidos.mapper;

import br.com.brunosarah.achadosperdidos.dto.ItemPerdidoDTO;
import br.com.brunosarah.achadosperdidos.entity.ItemPerdido;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemPerdidoMapper {
    List<ItemPerdido> toItensResponseDTO(List<ItemPerdido> entities);
    ItemPerdidoDTO toItemReponseDTO(ItemPerdido entity);
}
