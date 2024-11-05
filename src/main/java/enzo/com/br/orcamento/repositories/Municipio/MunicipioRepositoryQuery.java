package enzo.com.br.orcamento.repositories.Municipio;

import enzo.com.br.orcamento.model.Municipio;
import enzo.com.br.orcamento.repositories.filters.MunicipioFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MunicipioRepositoryQuery {
  public Page<Municipio> filtrar(MunicipioFilter municipioFilter, Pageable pageable);
}
