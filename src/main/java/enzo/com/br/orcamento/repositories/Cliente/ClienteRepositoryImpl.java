package enzo.com.br.orcamento.repositories.Cliente;

import enzo.com.br.orcamento.dto.ClienteDto;
import enzo.com.br.orcamento.repositories.filters.ClienteFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepositoryImpl implements ClienteRepositoryQuery {
  @PersistenceContext
  private EntityManager manager;

  @Override
  public Page<ClienteDto> filtrar(ClienteFilter clienteFilter, Pageable pageable) {
    CriteriaBuilder builder = manager.getCriteriaBuilder();
    CriteriaQuery<ClienteDto> criteria = builder.createQuery(ClienteDto.class);
    Root<ClienteDto> root = criteria.from(ClienteDto.class);

    criteria.select(builder.construct(ClienteDto.class,
            root.get("nome"),
            root.get("endereco"),
            root.get("numero"),
            root.get("bairro"),
            root.get("municipio").get("nome")
    ));

    Predicate[] predicates = criarRestricoes(clienteFilter, builder, root);
    criteria.where(predicates);
    criteria.orderBy(builder.asc(root.get("nome")));

    TypedQuery<ClienteDto> query = manager.createQuery(criteria);
    adicionarRestricoesPaginacao(query, pageable);
    return new PageImpl<>(query.getResultList(), pageable, total(clienteFilter));
  }

  private Long total(ClienteFilter clienteFilter) {
    CriteriaBuilder builder = manager.getCriteriaBuilder();
    CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
    Root<ClienteDto> root = criteria.from(ClienteDto.class);

    Predicate[] predicates = criarRestricoes(clienteFilter, builder, root);
    criteria.where(predicates);

    criteria.select(builder.count(root));
    return manager.createQuery(criteria).getSingleResult();
  }

  private void adicionarRestricoesPaginacao(TypedQuery<ClienteDto> query, Pageable pageable) {
    int paginalAtual = pageable.getPageNumber();
    int totalRegistrosPorPagina = pageable.getPageSize();
    int primeiroRegistroDaPagina = paginalAtual * totalRegistrosPorPagina;

    query.setFirstResult(primeiroRegistroDaPagina);
    query.setMaxResults(totalRegistrosPorPagina);
  }

  private Predicate[] criarRestricoes(ClienteFilter clienteFilter, CriteriaBuilder builder, Root<ClienteDto> root) {
    List<Predicate> predicates = new ArrayList<>();
    if (!StringUtils.isEmpty(clienteFilter.getNome())) {
      predicates.add(builder.like(builder.lower(root.get("nome")), "%" + clienteFilter.getNome().toLowerCase() + "%"));
    }
    if (!StringUtils.isEmpty(clienteFilter.getBairro())) {
      predicates.add(builder.like(builder.lower(root.get("bairro")), "%" + clienteFilter.getBairro().toLowerCase() + "%"));
    }
    if (!StringUtils.isEmpty(clienteFilter.getMunicipio())) {
      predicates.add(builder.like(builder.lower(root.get("municipio").get("nome")), "%" + clienteFilter.getMunicipio().toLowerCase() + "%"));
    }
    return predicates.toArray(new Predicate[predicates.size()]);
  }
}