package enzo.com.br.orcamento.repositories.Municipio;

import enzo.com.br.orcamento.model.Municipio;
import enzo.com.br.orcamento.repositories.filter.MunicipioFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MunicipioRepositoryImpl implements MunicipioRepositoryQuery{
  @PersistenceContext
  private EntityManager manager;
  @Override
  public Page<Municipio> filtrar(MunicipioFilter municipioFilter, Pageable pageable){
      CriteriaBuilder builder = manager.getCriteriaBuilder();
      CriteriaQuery<Municipio> criteria = builder.createQuery(Municipio.class);
      Root<Municipio> root = criteria.from(Municipio.class);

      Predicate[] predicates = criarRestricoes(municipioFilter, builder, root);
      criteria.where(predicates);
      criteria.orderBy(builder.asc(root.get("nome")));

      TypedQuery<Municipio> query = manager.createQuery(criteria);
      adicionarRestricoesPaginacao(query, pageable);
      return new PageImpl<>(query.getResultList(),pageable,total(municipioFilter));
  }

  public Long total(MunicipioFilter municipioFilter){
    CriteriaBuilder builder = manager.getCriteriaBuilder();
    CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
    Root<Municipio> root = criteria.from(Municipio.class);

    Predicate[] predicates = criarRestricoes(municipioFilter, builder, root);
    criteria.where(predicates);

    criteria.select(builder.count(root));
    return manager.createQuery(criteria).getSingleResult();
  }

  private void adicionarRestricoesPaginacao(TypedQuery<Municipio> query, Pageable pageable) {
      int paginalAtual = pageable.getPageNumber();
      int totalRegistrosPorPagina = pageable.getPageSize();
      int primeiroRegistroDaPagina = paginalAtual * totalRegistrosPorPagina;

      query.setFirstResult(primeiroRegistroDaPagina);
      query.setMaxResults(totalRegistrosPorPagina);
  }

  private Predicate[] criarRestricoes(MunicipioFilter municipioFilter, CriteriaBuilder builder, Root<Municipio> root){
      List<Predicate> predicates = new ArrayList<>();
      if (!StringUtils.isEmpty(municipioFilter.getNome())) {
          predicates.add(builder.like(builder.lower(root.get("nome")), "%" + municipioFilter.getNome().toLowerCase() + "%"));
      }
      return predicates.toArray(new Predicate[predicates.size()]);
  }
}
