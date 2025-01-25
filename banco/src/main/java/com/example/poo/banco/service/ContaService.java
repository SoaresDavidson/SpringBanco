package com.example.poo.banco.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.poo.banco.DTO.ContaDTO;
import com.example.poo.banco.model.ContaModel;
import com.example.poo.banco.repository.ContaRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Min;

@Service
public class ContaService {
    
    @Autowired
    private ContaRepository contaRepository;

    // public ContaDTO getContaByNumConta(int numConta) throws ContaInexistente {
    //     ContaModel conta = ContaRepository.findById(numConta).orElseThrow(() -> new ContaInexistente());
    //     return convertToDTO(conta);

    // }
    @Transactional
    public void saveConta(ContaDTO ContaDTO) {
        ContaModel conta = convertToEntity(ContaDTO);
        contaRepository.save(conta);
    }

    private ContaDTO convertToDTO(ContaModel contaModel) {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setNumConta(contaModel.getNumConta());
        contaDTO.setCpf(contaModel.getCpf());
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
        contaModel.setCpf(contaDTO.getCpf());
        contaModel.setNomeCliente(contaDTO.getNomeCliente());
        contaModel.setContaFechada(contaDTO.isContaFechada());
        contaModel.setValorPago(contaDTO.getValorPago());
        contaModel.setGorjetaComida(contaDTO.getGorjetaComida());
        contaModel.setGorjetaBebida(contaDTO.getGorjetaBebida());
        return contaModel;
    }

    public ContaModel findById(int num_conta) {
        Optional<ContaModel> obj = contaRepository.findById(num_conta);
        return obj.get();
    }

    public void removeConta(int num_conta) {
        contaRepository.findById(num_conta).orElseThrow(() -> new RuntimeException("Conta não existe!"));
        contaRepository.deleteById(num_conta);

    }

    public void addConta(ContaDTO obj) {
        try {
            contaRepository.findById(obj.getNumConta()).orElseThrow(() -> new Exception());
            //throw new Exception();// TODO: criar exceção personalizada
        } catch (Exception e) {
            ContaModel conta = convertToEntity(obj);
            contaRepository.save(conta);
        }
        
    }

    public boolean fecharConta(int num_conta) throws Exception {
        Optional<ContaModel> contaOpt = contaRepository.findById(num_conta);

        if (contaOpt.isPresent()) {
            ContaModel conta = contaOpt.get();

            if (!conta.isContaFechada()) {
                conta.setContaFechada(true);
                contaRepository.save(conta);
                return true;
            }
            return false; // Account is already closed
        }
        throw new Exception("Conta com num_conta " + num_conta + " não encontrada");
    }

    public boolean pagar(int num_conta,double pagamento, double valorDaConta) throws Exception {
        Optional<ContaModel> contaOpt = contaRepository.findById(num_conta);

        if (contaOpt.isPresent()) {
            ContaModel conta = contaOpt.get();

            if (pagamento < valorDaConta) {
                conta.setValorPago(pagamento+valorDaConta);
                contaRepository.save(conta);
                return true;
            }
            return false; // Account is already closed
        }
        throw new Exception("Conta com num_conta " + num_conta + " não encontrada");
    }
}
