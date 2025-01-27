package com.example.poo.banco.repository;
import com.example.poo.banco.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface PedidoRepository extends JpaRepository<PedidoModel, Integer>{
    @Query("SELECT p FROM PedidoModel p WHERE p.num_conta = :numConta")
    List<PedidoModel> findByNumConta(@Param("numConta") int numConta);
}
