package com.santander.api.service;

import java.util.List;

import com.santander.api.error.ConflictException;
import com.santander.api.error.NotFoundException;
import com.santander.api.model.Cliente;
import com.santander.api.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> localizaTodos() {
        return clienteRepository.findAll();
    }

    public Cliente localizaId(long id) {
        return clienteRepository.findById(id);
    }

    public List<Cliente> localizaNome(String nome) {
        return clienteRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Cliente cadastrar(Cliente cliente) {

        Cliente confereCpf = clienteRepository.findByCpf(cliente.getCpf());
        Cliente confereRg = clienteRepository.findByRg(cliente.getRg());

        if (confereCpf != null || confereRg != null) {
            throw new ConflictException("Esse cliente já foi cadastrado");
        }

        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Cliente cliente) {
        Cliente clienteAtualizar = localizaId(cliente.getId());
        
        if (clienteAtualizar == null) {
            throw new NotFoundException("Cliente não encontrado!");
        }
        
        return clienteRepository.save(cliente);
    }

    public void deletar(long id) {
        Cliente clienteEncontrado = localizaId(id);
        if (clienteEncontrado == null) {
            throw new NotFoundException("Id não encontrado!");
        }

        clienteRepository.delete(clienteEncontrado);
    }
}
