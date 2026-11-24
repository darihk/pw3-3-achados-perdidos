package br.com.brunosarah.achadosperdidos.controller;

import br.com.brunosarah.achadosperdidos.dto.ItemPerdidoDTO;
import br.com.brunosarah.achadosperdidos.dto.StatusItemDTO;
import br.com.brunosarah.achadosperdidos.entity.ItemPerdido;
import br.com.brunosarah.achadosperdidos.enums.StatusItemEnum;
import br.com.brunosarah.achadosperdidos.service.ItemPerdidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.Map;

import java.util.List;


@RestController
@RequestMapping("/api/itens")
@CrossOrigin(origins = "http://localhost:4200")
public class ItemPerdidoController {

    @Autowired
    private ItemPerdidoService itemPerdidoService;

    @GetMapping
    public List<ItemPerdidoDTO> findItens(@RequestParam(required = false)StatusItemEnum status){
        return itemPerdidoService.findItens(status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPerdidoDTO> findById(@PathVariable Long id){
        var result = itemPerdidoService.findById(id);
        if(result.isPresent()){
            return ResponseEntity.ok(result.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ItemPerdidoDTO> createItem(@RequestBody ItemPerdido itemPerdido){
        var result = itemPerdidoService.postItem(itemPerdido);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPerdidoDTO> updateItem(@PathVariable Long id, @RequestBody ItemPerdido itemPerdido){
        var result = itemPerdidoService.putItem(id, itemPerdido);

        if(result.isPresent()){
            return ResponseEntity.ok(result.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ItemPerdidoDTO> deleteItem(@PathVariable Long id){
        var result = itemPerdidoService.deleteItem(id);
        if(result){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }

    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ItemPerdidoDTO> statusUpdate(@PathVariable Long id, @RequestBody StatusItemDTO statusItemDTO){
        var result = itemPerdidoService.atualizarStatus(id, statusItemDTO.statusItem());

        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result.get());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, String>> tratarErroDeRegraDeNegocio(
            IllegalStateException erro) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("mensagem", erro.getMessage()));
    }g

}
