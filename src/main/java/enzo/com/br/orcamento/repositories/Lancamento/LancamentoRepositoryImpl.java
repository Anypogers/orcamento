package enzo.com.br.orcamento.repositories.Lancamento;

import enzo.com.br.orcamento.dto.LancamentoDto;
import enzo.com.br.orcamento.repositories.filters.LancamentoFilter;
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

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery{
  @PersistenceContext
  private EntityManager manager;
  @Override
  public Page<LancamentoDto> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable){
    CriteriaBuilder builder = manager.getCriteriaBuilder();
    CriteriaQuery<LancamentoDto> criteria = builder.createQuery(LancamentoDto.class);
    Root<LancamentoDto> root = criteria.from(LancamentoDto.class);

    /* DTO */
    criteria.select(builder.construct(LancamentoDto.class,
            root.get("datalancamento"),
            root.get("tipolancamento"),
            root.get("valorlancamento")
    ));

    Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
    criteria.where(predicates);
    criteria.orderBy(builder.asc(root.get("nome")));

    TypedQuery<LancamentoDto> query = manager.createQuery(criteria);
    adicionarRestricoesPaginacao(query, pageable);
    return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
  }

  private Long total(LancamentoFilter lancamentoFilter) {
    CriteriaBuilder builder = manager.getCriteriaBuilder();
    CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
    Root<LancamentoDto> root = criteria.from(LancamentoDto.class);

    Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
    criteria.where(predicates);

    criteria.select(builder.count(root));
    return manager.createQuery(criteria).getSingleResult();
  }

  private void adicionarRestricoesPaginacao(TypedQuery<LancamentoDto> query, Pageable pageable) {
    int paginalAtual = pageable.getPageNumber();
    int totalRegistrosPorPagina = pageable.getPageSize();
    int primeiroRegistroDaPagina = paginalAtual * totalRegistrosPorPagina;

    query.setFirstResult(primeiroRegistroDaPagina);
    query.setMaxResults(totalRegistrosPorPagina);
  }

  private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder, Root<LancamentoDto> root) {
    List<Predicate> predicates = new ArrayList<>();
    if (!StringUtils.isEmpty(lancamentoFilter.getCliente())) {
      predicates.add(builder.like(builder.lower(root.get("cliente").get("nome")), "%" + lancamentoFilter.getCliente().toLowerCase() + "%"));
    }
    if (!StringUtils.isEmpty(lancamentoFilter.getTipolancamento())) {
      predicates.add(builder.like(builder.lower(root.get("tipolancamento")), "%" + lancamentoFilter.getTipolancamento().toLowerCase() + "%"));
    }
    return predicates.toArray(new Predicate[predicates.size()]);
  }
}
