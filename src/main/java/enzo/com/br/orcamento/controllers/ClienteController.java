package enzo.com.br.orcamento.controllers;

import enzo.com.br.orcamento.model.Cliente;
import enzo.com.br.orcamento.repositories.ClienteRepository;
import enzo.com.br.orcamento.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
  @Autowired
  private ClienteService clienteService;
  @Autowired
  private ClienteRepository clienteRepository;

  @GetMapping
  public List<Cliente> listarTodasCliente() {
    return clienteRepository.findAll(Sort.by("name").ascending());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Cliente> buscarPeloCodigo(@PathVariable Long id) {
    Optional<Cliente> cliente = clienteRepository.findById(id);
    return cliente.isPresent() ? ResponseEntity.ok(cliente.get()) : ResponseEntity.notFound().build();
  }

  @PostMapping()
  public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente) {
    Cliente clienteSalva = clienteService.salvar(cliente);
    return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalva);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
    Cliente clienteSalva = clienteService.atualizar(id, cliente);
    return ResponseEntity.ok(clienteSalva);
  }
}
