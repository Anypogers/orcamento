package enzo.com.br.orcamento.repositories.Cliente;

import enzo.com.br.orcamento.dto.ClienteDto;
import enzo.com.br.orcamento.repositories.filters.ClienteFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteRepositoryQuery {
  public Page<ClienteDto> filtrar(ClienteFilter clienteFilter, Pageable pageable);
}