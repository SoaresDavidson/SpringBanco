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
        // Verifique se já existe um item com o mesmo código (num)
        Optional<MenuModel> existingMenu = menuRepository.findById(obj.getNum());
        
        // Se já existir, lançar uma exceção ou retornar erro
        if (existingMenu.isPresent()) {
            throw new RuntimeException("Item com o código " + obj.getNum() + " já existe no banco de dados!");
        }
        
        // Se não existir, criar e salvar o novo item
        MenuModel menu = convertToEntity(obj);
        menuRepository.save(menu);
    }
    
}
