package enzo.com.br.orcamento.repositories.Cliente;

import enzo.com.br.orcamento.model.Cliente;
import enzo.com.br.orcamento.repositories.filters.ClienteFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteRepositoryQuery{
    public Page<Cliente> filtrar(ClienteFilter clienteFilter, Pageable pageable);
}