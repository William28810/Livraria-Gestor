package model;

import java.time.LocalDateTime;

/**
 * Representa um registro de histórico do sistema.
 * Armazena informações sobre ações realiadas
 * pelos funcionários, permitindo auditoria.
 */
public class Historico {
    
    private int idHistorico;
    private String tipoAcao;
    private LocalDateTime dataHora;
    private String tabelaMovimentada;
    private int idRegistroMovimento;
    private Funcionario funcionario;
    private int idFuncionario;

    /**
     * Construção da classe Historico
     */
    public Historico(){
        
    }
    
    public Historico(String tipoAcao, String tabelaMovimentada, int idRegistroMovimento, Funcionario funcionario, int idFuncionario) {
        this.tipoAcao = tipoAcao;
        this.tabelaMovimentada = tabelaMovimentada;
        this.idRegistroMovimento = idRegistroMovimento;
        this.funcionario = funcionario;
        this.dataHora = LocalDateTime.now();
        this.idFuncionario = idFuncionario;
    }
    
    // Getters e Setters

    public int getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(int idHistorico) {
        this.idHistorico = idHistorico;
    }

    public String getTipoAcao() {
        return tipoAcao;
    }

    public void setTipoAcao(String tipoAcao) {
        this.tipoAcao = tipoAcao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getTabelaMovimentada() {
        return tabelaMovimentada;
    }

    public void setTabelaMovimentada(String tabelaMovimentada) {
        this.tabelaMovimentada = tabelaMovimentada;
    }

    public int getIdRegistroMovimento() {
        return idRegistroMovimento;
    }

    public void setIdRegistroMovimento(int idRegistroMovimento) {
        this.idRegistroMovimento = idRegistroMovimento;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
    
    /**
     * Retorna uma representação textual do histórico,
     * facilitando a visualização no console.
     */
    @Override
    public String toString(){
       return "Historico {\n" +
               "Ação = '" + tipoAcao + "\n" +
               ", Entidade = '" + tabelaMovimentada + "\n" +
               ", IdEntidade = '" + idRegistroMovimento + "\n" +
               ", Funcionário = R$'" + funcionario.getNome()+ "\n" +
               ", Data/Hora = '" + dataHora + "\n" +
               '}';
    }
}
