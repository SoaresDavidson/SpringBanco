package com.example.poo.banco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.poo.banco.repository.ContaRepository;
import com.example.poo.banco.repository.MenuRepository;
import com.example.poo.banco.DTO.PedidoDTO;
import com.example.poo.banco.model.MenuModel;
import com.example.poo.banco.model.PedidoModel;
import com.example.poo.banco.repository.PedidoRepository;
import com.example.poo.banco.service.exceptions.ContaInexistente;
import com.example.poo.banco.service.exceptions.ItemNaoEncontradoException;
import jakarta.transaction.Transactional;
import java.util.List;
@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private MenuRepository menuRepository;
    
    @Transactional
    public PedidoModel addPedido(PedidoDTO obj) throws ContaInexistente, ItemNaoEncontradoException {
        if (!contaRepository.existsById(obj.getNum_conta())) {
            throw new ContaInexistente();
        }

        if (!menuRepository.existsById(obj.getNum_item())) {
            throw new ItemNaoEncontradoException("Número do item não encontrado!");
        }
        PedidoModel pedido = convertToEntity(obj); 
        return pedidoRepository.save(pedido);
    }
    private PedidoModel convertToEntity(PedidoDTO pedidoDTO) {
        PedidoModel pedido = new PedidoModel();
        pedido.setId(pedidoDTO.getId());
        pedido.setNum_conta(pedidoDTO.getNum_conta());
        pedido.setNum_item(pedidoDTO.getNum_item());
        pedido.setQuant(pedidoDTO.getQuant());
        return pedido;
    }

    public String calcularExtrato(int numConta) throws ContaInexistente {
        // Verificar se a conta existe
        if (!contaRepository.existsById(numConta)) {
            throw new ContaInexistente();
        }

        // Buscar todos os pedidos da conta
        List<PedidoModel> pedidos = pedidoRepository.findByNumConta(numConta);

        // Calcular o valor total
        double valorTotal = 0.0;
        StringBuilder extrato = new StringBuilder();
        extrato.append("Extrato da conta ").append(numConta).append(":\n");

        for (PedidoModel pedido : pedidos) {
            // Buscar informações sobre o item no menu
            MenuModel menuItem = menuRepository.findById(pedido.getNum_item()).orElse(null);

            if (menuItem != null) {
                double valorItem = menuItem.getPreco();
                double totalItem = valorItem * pedido.getQuant();

                // Adicionar as informações do pedido ao extrato
                extrato.append("Item: ").append(menuItem.getNome())
                       .append(" | Quantidade: ").append(pedido.getQuant())
                       .append(" | Preço: R$ ").append(valorItem)
                       .append(" | Total: R$ ").append(totalItem).append("\n");

                // Acumular o valor total
                valorTotal += totalItem;
            } else {
                extrato.append("Item com código ").append(pedido.getNum_item()).append(" não encontrado no menu.\n");
            }
        }

        // Exibir o valor total
        extrato.append("Valor Total: R$ ").append(valorTotal);
        return extrato.toString();
    }

}
