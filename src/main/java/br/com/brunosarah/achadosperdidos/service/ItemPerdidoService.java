package br.com.brunosarah.achadosperdidos.service;

import br.com.brunosarah.achadosperdidos.dto.ItemPerdidoDTO;
import br.com.brunosarah.achadosperdidos.entity.ItemPerdido;
import br.com.brunosarah.achadosperdidos.enums.StatusItemEnum;
import br.com.brunosarah.achadosperdidos.repository.ItemPerdidoRepository;
import br.com.brunosarah.achadosperdidos.mapper.ItemPerdidoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPerdidoService {

    @Autowired
    private ItemPerdidoRepository itemPerdidoRepository;

    @Autowired
    private ItemPerdidoMapper itemPerdidoMapper;

    public Optional<ItemPerdidoDTO> findById(Long id){
        return itemPerdidoRepository.findByIdItemPerdido(id).map(itemPerdido -> itemPerdidoMapper.toItemReponseDTO(itemPerdido));
    }

    public List<ItemPerdido> findItens(){
        var itens = itemPerdidoRepository.findItens();
        return itemPerdidoMapper.toItensResponseDTO(itens);
    }

    public ItemPerdido postItem(ItemPerdido itemPerdido){
        itemPerdido.setStatusItem(StatusItemEnum.PERDIDO);
        return itemPerdidoRepository.save(itemPerdido);
    }

    public ItemPerdido putItem(ItemPerdido itemPerdido){
        return itemPerdidoRepository.save(itemPerdido);
    }

    public Optional<ItemPerdido> deleteItem(Long id){
        var item = itemPerdidoRepository.findByIdItemPerdido(id);
        return itemPerdidoRepository.deleteAll(item);

    }



}
