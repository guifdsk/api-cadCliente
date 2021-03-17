package com.santander.api.repository;

import java.util.List;

import com.santander.api.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    Cliente findById(long id);
    Cliente findByCpf(String cpf);
    Cliente findByRg(String rg);
    List<Cliente> findByNomeContainingIgnoreCase(String nome);
}
