package enzo.com.br.orcamento.dto;

import java.math.BigDecimal;

public class LancamentoDto {
    private String datalancamento;
    private String tipolancamento;
    private BigDecimal valorlancamento;
    public LancamentoDto(String datalancamento, String tipolancamento, BigDecimal valorlancamento) {
        this.datalancamento = datalancamento;
        this.tipolancamento = tipolancamento;
        this.valorlancamento = valorlancamento;
    }

    public String getDatalancamento() {
        return datalancamento;
    }

    public void setDatalancamento(String datalancamento) {
        this.datalancamento = datalancamento;
    }

    public String getTipolancamento() {
        return tipolancamento;
    }

    public void setTipolancamento(String tipolancamento) {
        this.tipolancamento = tipolancamento;
    }

    public BigDecimal getValorlancamento() {
        return valorlancamento;
    }

    public void setValorlancamento(BigDecimal valorlancamento) {
        this.valorlancamento = valorlancamento;
    }
}