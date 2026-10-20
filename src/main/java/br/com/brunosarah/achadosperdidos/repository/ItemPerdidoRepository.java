package br.com.brunosarah.achadosperdidos.repository;

import br.com.brunosarah.achadosperdidos.entity.ItemPerdido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPerdidoRepository extends JpaRepository<ItemPerdido, Long> {
}
