package br.com.brunosarah.achadosperdidos.repository;

import br.com.brunosarah.achadosperdidos.entity.ItemPerdido;
import br.com.brunosarah.achadosperdidos.enums.StatusItemEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemPerdidoRepository extends JpaRepository<ItemPerdido, Long> {

    Optional<ItemPerdido> findByIdItemPerdido(Long idItemPerdido);
    List<ItemPerdido> findItens();
}
