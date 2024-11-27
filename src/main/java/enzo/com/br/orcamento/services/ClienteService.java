package enzo.com.br.orcamento.services;

import enzo.com.br.orcamento.model.Cliente;
import enzo.com.br.orcamento.repositories.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
  @Autowired
  private ClienteRepository clienteRepository;

  public Cliente salvar(Cliente cliente) {
    return clienteRepository.save(cliente);
  }

  public Cliente atualizar(Long id, Cliente cliente) {
    Cliente clienteSalva = buscarCategoriaExistente(id);
    BeanUtils.copyProperties(cliente, clienteSalva, "id");
    return clienteRepository.save(clienteSalva);
  }

  private Cliente buscarCategoriaExistente(Long id) {
    Optional<Cliente> clienteSalva = clienteRepository.findById(id);
    if (!clienteSalva.isPresent()) {
      throw new IllegalArgumentException();
    }
    return clienteSalva.get();
  }
}
