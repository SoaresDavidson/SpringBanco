package com.example.poo.banco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.poo.banco.DTO.ContaDTO;
import com.example.poo.banco.model.ContaModel;
import com.example.poo.banco.service.ContaService;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Min;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping("/create-conta")
    public ResponseEntity<String> createConta(@RequestBody ContaDTO obj) {
        contaService.addConta(obj);
        return ResponseEntity.ok("Conta criada com sucesso");
    }

    @PutMapping(value = "/{num_conta}/fechar-conta")
    @Transactional
    public ResponseEntity<String> fecharConta(@PathVariable("num_conta") @Min(0) int num_conta) {
        try {
            boolean success = contaService.fecharConta(num_conta);
            if (success) {
                return ResponseEntity.ok("Conta fechada com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A conta já está fechada ou não existe");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao fechar a conta: " + e.getMessage());
        }
    }

    @PutMapping(value = "/{num_conta}/registrarPagamento")
    @Transactional
    public ResponseEntity<String> pagamento(
        @PathVariable("num_conta") @Min(0) int num_conta,
        @RequestParam double pagamento,
        @RequestParam double valorDaConta
        ) {
        try {
            boolean success = contaService.pagar(num_conta, pagamento, valorDaConta);
            if (success) {
                return ResponseEntity.ok("Conta fechada com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A conta já está fechada ou não existe");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao fechar a conta: " + e.getMessage());
        }
    }

    @GetMapping(value = "/{num_conta}")
    public ResponseEntity<ContaModel> findById(@PathVariable("num_conta") int num_conta) {
        ContaModel obj = contaService.findById(num_conta);
        return ResponseEntity.ok().body(obj);
    }
    

    @DeleteMapping(value = "/{num_conta}")
    public ResponseEntity<?> removeConta(@PathVariable("num_conta") int num_conta){
        contaService.removeConta(num_conta);
        return ResponseEntity.ok().build();
    }
}


