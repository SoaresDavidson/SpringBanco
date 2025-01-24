package com.example.poo.banco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.poo.banco.DTO.ContaDTO;
import com.example.poo.banco.model.ContaModel;
import com.example.poo.banco.repository.ContaRepository;

import jakarta.transaction.Transactional;

@Service
public class ContaService {
    
    @Autowired
    private ContaRepository ContaRepository;

    // public ContaDTO getContaByNumConta(int numConta) throws ContaInexistente {
    //     ContaModel conta = ContaRepository.findById(numConta).orElseThrow(() -> new ContaInexistente());
    //     return convertToDTO(conta);

    // }
    @Transactional
    public void saveConta(ContaDTO ContaDTO) {
        ContaModel conta = convertToEntity(ContaDTO);
        ContaRepository.save(conta);
    }
    @Transactional
    public void saveConta(ContaModel obj) {
        ContaRepository.save(obj);
    }

    private ContaDTO convertToDTO(ContaModel contaModel) {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setNumConta(contaModel.getNumConta());
        contaDTO.setNomeCliente(contaModel.getNomeCliente());
        contaDTO.setContaFechada(contaModel.isContaFechada());
        contaDTO.setValorPago(contaModel.getValorPago());
        contaDTO.setGorjetaComida(contaModel.getGorjetaComida());
        contaDTO.setGorjetaBebida(contaModel.getGorjetaBebida());
        return contaDTO;
    }

    private ContaModel convertToEntity(ContaDTO contaDTO) {
        ContaModel contaModel = new ContaModel();
        contaModel.setNumConta(contaDTO.getNumConta());
        contaModel.setNomeCliente(contaDTO.getNomeCliente());
        contaModel.setContaFechada(contaDTO.isContaFechada());
        contaModel.setValorPago(contaDTO.getValorPago());
        contaModel.setGorjetaComida(contaDTO.getGorjetaComida());
        contaModel.setGorjetaBebida(contaDTO.getGorjetaBebida());
        return contaModel;
    }

    public void addPedido(int numConta, int numItem, int quant) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addPedido'");
    }
}
