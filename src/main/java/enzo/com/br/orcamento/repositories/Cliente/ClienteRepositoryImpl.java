package enzo.com.br.orcamento.repositories.Cliente;

import enzo.com.br.orcamento.model.Cliente;
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
  public Page<Cliente> filtrar(ClienteFilter clienteFilter, Pageable pageable) {
    CriteriaBuilder builder = manager.getCriteriaBuilder();
    CriteriaQuery<Cliente> criteria = builder.createQuery(Cliente.class);
    Root<Cliente> root = criteria.from(Cliente.class);

    Predicate[] predicates = criarRestricoes(clienteFilter, builder, root);
    criteria.where(predicates);
    criteria.orderBy(builder.asc(root.get("nome")));

    TypedQuery<Cliente> query = manager.createQuery(criteria);
    adicionarRestricoesPaginacao(query, pageable);
    return new PageImpl<>(query.getResultList(), pageable, total(clienteFilter));
  }

  private Long total(ClienteFilter clienteFilter) {
    CriteriaBuilder builder = manager.getCriteriaBuilder();
    CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
    Root<Cliente> root = criteria.from(Cliente.class);

    Predicate[] predicates = criarRestricoes(clienteFilter, builder, root);
    criteria.where(predicates);

    criteria.select(builder.count(root));
    return manager.createQuery(criteria).getSingleResult();
  }

  private void adicionarRestricoesPaginacao(TypedQuery<Cliente> query, Pageable pageable) {
    int paginalAtual = pageable.getPageNumber();
    int totalRegistrosPorPagina = pageable.getPageSize();
    int primeiroRegistroDaPagina = paginalAtual * totalRegistrosPorPagina;

    query.setFirstResult(primeiroRegistroDaPagina);
    query.setMaxResults(totalRegistrosPorPagina);
  }

  private Predicate[] criarRestricoes(ClienteFilter clienteFilter, CriteriaBuilder builder, Root<Cliente> root) {
    List<Predicate> predicates = new ArrayList<>();
    if (!StringUtils.isEmpty(clienteFilter.getNome())) {
      predicates.add(builder.like(builder.lower(root.get("nome")), "%" + clienteFilter.getNome().toLowerCase() + "%"));
    }
    if (!StringUtils.isEmpty(clienteFilter.getEndereco())) {
      predicates.add(builder.like(builder.lower(root.get("endereco")), "%" + clienteFilter.getEndereco().toLowerCase() + "%"));
    }
    if (!StringUtils.isEmpty(clienteFilter.getBairro())) {
      predicates.add(builder.like(builder.lower(root.get("bairro")), "%" + clienteFilter.getBairro().toLowerCase() + "%"));
    }
    return predicates.toArray(new Predicate[predicates.size()]);
  }
}