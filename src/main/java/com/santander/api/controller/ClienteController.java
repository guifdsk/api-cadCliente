package com.santander.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.santander.api.model.Cliente;
import com.santander.api.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    public ClienteService clienteService;

    @GetMapping("/todos")
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> todosClientes = clienteService.localizaTodos();
        return new ResponseEntity<>(todosClientes, HttpStatus.OK);
    }

    @GetMapping("/buscaId/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable long id) {
        return new ResponseEntity<>(clienteService.localizaId(id), HttpStatus.OK);
    }

    @GetMapping("/buscaCliente/{nome}")
    public ResponseEntity<List<Cliente>> findByNome(@PathVariable String nome) {
        List<Cliente> clientesPorNome = clienteService.localizaNome(nome);
        return new ResponseEntity<>(clientesPorNome, HttpStatus.OK);    
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Cliente> post(@Valid @RequestBody Cliente cliente) {
        Cliente clienteCadastrado = clienteService.cadastrar(cliente);
        return new ResponseEntity<>(clienteCadastrado, HttpStatus.CREATED);
    }

    @PutMapping("/alterar")
    public ResponseEntity<Cliente> put(@Valid @RequestBody Cliente cliente) {
        Cliente clienteAtualizado = clienteService.atualizar(cliente);
        return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public void delete(@PathVariable long id) {
        clienteService.deletar(id);
    }
    
}
