package com.example.poo.banco.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.poo.banco.DTO.MenuDTO;
import com.example.poo.banco.model.MenuModel;
import com.example.poo.banco.repository.MenuRepository;

import jakarta.transaction.Transactional;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Transactional
    public void saveMenu(MenuDTO menuDTO) {
        MenuModel menu = convertToEntity(menuDTO);
        menuRepository.save(menu);
    }

    private MenuModel convertToEntity(MenuDTO menuDTO) {
        MenuModel menuModel = new MenuModel();
        menuModel.setNum(menuDTO.getNum());
        menuModel.setNome(menuDTO.getNome());
        menuModel.setPreco(menuDTO.getPreco());
        menuModel.setTipo(menuDTO.getTipo());
        return menuModel;
    }

        public MenuModel findById(int num) {
        Optional<MenuModel> obj = menuRepository.findById(num);
        return obj.get();
    }

    public void removeItem(int num) {
        menuRepository.findById(num).orElseThrow(() -> new RuntimeException("Item não existe!"));
        menuRepository.deleteById(num);

    }

    public void addItem(MenuDTO obj) {
        try {
            menuRepository.findById(obj.getNum()).orElseThrow(() -> new Exception());
        } catch (Exception e) {
            MenuModel menu = convertToEntity(obj);
            menuRepository.save(menu);
        }
        
    }
}
