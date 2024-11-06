package enzo.com.br.orcamento.repositories.Lancamento;

import enzo.com.br.orcamento.dto.LancamentoDto;
import enzo.com.br.orcamento.repositories.filters.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LancamentoRepositoryQuery {
  public Page<LancamentoDto> filtrar(LancamentoFilter clienteFilter, Pageable pageable);
}
