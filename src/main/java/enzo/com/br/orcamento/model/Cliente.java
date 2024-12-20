package enzo.com.br.orcamento.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cliente")
public class Cliente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String endereco;
  private String numero;
  private String bairro;
  private String telefone;
  private String celular;

  @OneToMany(mappedBy = "cliente")
  private List<Lancamento> lancamentoLista = new ArrayList<>();

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "id_municipio")
  private Municipio municipio;

  // GETTER AND SETTER

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public String getBairro() {
    return bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public Municipio getMunicipio() {
    return municipio;
  }

  public void setMunicipio(Municipio municipio) {
    this.municipio = municipio;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getCelular() {
    return celular;
  }

  public void setCelular(String celular) {
    this.celular = celular;
  }

  // EQUALS AND HASHCODE

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Cliente cliente = (Cliente) o;
    return id == cliente.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
