package enzo.com.br.orcamento.dto;

import enzo.com.br.orcamento.model.Municipio;

public class ClienteDto {
  private String nome;
  private String endereco;
  private String numero;
  private String bairro;
  private String municipio;

  public ClienteDto(String nome, String endereco, String numero, String bairro, String municipio) {
    this.nome = nome;
    this.endereco = endereco;
    this.numero = numero;
    this.bairro = bairro;
    this.municipio = municipio;
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

  public String getMunicipio() {
    return municipio;
  }

  public void setMunicipio(String municipio) {
    this.municipio = municipio;
  }
}
