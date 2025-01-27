package com.example.poo.banco.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.poo.banco.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.poo.banco.DTO.MenuDTO;
import com.example.poo.banco.model.MenuModel;
import com.example.poo.banco.repository.MenuRepository;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuRepository menuRepository;
    
    @PostMapping("/add-item")
    public ResponseEntity<String> addItem(@RequestBody MenuDTO obj) {
        try {
            menuService.addItem(obj);
            return ResponseEntity.ok("Pedido added successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{numItem}")
    public MenuModel getMenuItemByNum(@PathVariable int numItem) {
        return menuRepository.findById(numItem).orElseThrow(() -> new RuntimeException("Item não encontrado"));
    }

    @DeleteMapping(value = "/remove/{num}")
    public ResponseEntity<?> removeConta(@PathVariable("num") int num){
        menuService.removeItem(num);
        return ResponseEntity.ok().build();
    }
}
