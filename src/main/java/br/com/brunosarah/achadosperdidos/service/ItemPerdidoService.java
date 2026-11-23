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
        return itemPerdidoRepository.findById(id).map(itemPerdido -> itemPerdidoMapper.toItemReponseDTO(itemPerdido));
    }

    public List<ItemPerdidoDTO> findItens(StatusItemEnum status){
        List<ItemPerdido> itens;
        if(status == StatusItemEnum.PERDIDO){
            itens = itemPerdidoRepository.findByStatusItem((StatusItemEnum.PERDIDO));
        }
        else{
            itens = itemPerdidoRepository.findAll();
        }
        return itemPerdidoMapper.toItensResponseDTO(itens);
    }

    public ItemPerdidoDTO postItem(ItemPerdido itemPerdido){
        itemPerdido.setStatusItem(StatusItemEnum.PERDIDO);
        var item = itemPerdidoRepository.save(itemPerdido);
        return itemPerdidoMapper.toItemReponseDTO(item);
    }

    public Optional<ItemPerdidoDTO> putItem(Long id, ItemPerdido itemPerdido){
        return itemPerdidoRepository.findById(id).map(item -> {
            item.setNomeItem(itemPerdido.getNomeItem());
            item.setDataAchado(itemPerdido.getDataAchado());
            item.setDescricaoDevolucao(itemPerdido.getDescricaoDevolucao());
            item.setLocalEncontrado(itemPerdido.getLocalEncontrado());

            var itemAtualizado = itemPerdidoRepository.save(item);
            return itemPerdidoMapper.toItemReponseDTO(itemAtualizado);
        });

    }

    public boolean deleteItem(Long id){
        var item = itemPerdidoRepository.findById(id);
        if(item.isEmpty()){
            return false;
        }

        itemPerdidoRepository.delete(item.get());
        return true;

    }

    public Optional<ItemPerdidoDTO> atualizarStatus(Long id, StatusItemEnum novoStatus){
        return itemPerdidoRepository.findById(id).map(item -> {
            if(item.getStatusItem() == StatusItemEnum.DEVOLVIDO && novoStatus != StatusItemEnum.DEVOLVIDO){
                throw new IllegalStateException("Item já devolvido ao dono não pode ter o status alterado.");
            }

            if(item.getStatusItem() == StatusItemEnum.PERDIDO && novoStatus != StatusItemEnum.REIVINDICADO){
                throw new IllegalStateException("Um item perdido precisa ser reivindicado antes de ser devolvido.");
            }

            if(item.getStatusItem() == StatusItemEnum.REIVINDICADO && novoStatus != StatusItemEnum.DEVOLVIDO){
                throw new IllegalStateException("Um item reivindicado só pode ser marcado como devolvido.");
            }

            item.setStatusItem(novoStatus);

            var itemSalvo = itemPerdidoRepository.save(item);
            return itemPerdidoMapper.toItemReponseDTO(itemSalvo);
        });
    }



}
