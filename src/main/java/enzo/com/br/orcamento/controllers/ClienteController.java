package enzo.com.br.orcamento.controllers;

import enzo.com.br.orcamento.model.Cliente;
import enzo.com.br.orcamento.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listarTodasCliente() {
        return clienteRepository.findAll(Sort.by("__campo__").ascending());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPeloCodigo(@PathVariable int id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.isPresent() ? ResponseEntity.ok(cliente.get()) : ResponseEntity.notFound().build();
    }
    @PostMapping()
    public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente){
        Cliente clienteSalva = clienteService.salvar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalva);
    }
}
