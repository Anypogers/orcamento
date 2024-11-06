package enzo.com.br.orcamento.repositories.filters;

import java.math.BigDecimal;

public class LancamentoFilter {
  private String cliente;
  private String tipolancamento;

  public String getCliente() {
    return cliente;
  }

  public void setCliente(String cliente) {
    this.cliente = cliente;
  }

  public String getTipolancamento() {
    return tipolancamento;
  }

  public void setTipolancamento(String tipolancamento) {
    this.tipolancamento = tipolancamento;
  }
}

