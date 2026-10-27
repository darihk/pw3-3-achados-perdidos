package br.com.brunosarah.achadosperdidos.controller;

import br.com.brunosarah.achadosperdidos.dto.ItemPerdidoDTO;
import br.com.brunosarah.achadosperdidos.entity.ItemPerdido;
import br.com.brunosarah.achadosperdidos.service.ItemPerdidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
@CrossOrigin("*")
public class ItemPerdidoController {

    @Autowired
    private ItemPerdidoService itemPerdidoService;

    @GetMapping
    public List<ItemPerdidoDTO> findItens(){
        return itemPerdidoService.findItens();
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

    @PutMapping
    public ResponseEntity<ItemPerdidoDTO> updateItem(@RequestBody ItemPerdido itemPerdido){
        var result = itemPerdidoService.putItem(itemPerdido);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ItemPerdidoDTO> deleteItem(@PathVariable Long id){
        var result = itemPerdidoService.deleteItem(id);

    }

    @PatchMapping
    public ResponseEntity<ItemPerdidoDTO> statusUpdate(@RequestBody ItemPerdido itemPerdido){
        var item = itemPerdidoService.findById()
    }

}
