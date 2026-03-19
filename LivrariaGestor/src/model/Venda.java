package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma venda realizada na livraria.
 * Contém os itens vendidos, o cliente e o funcionário responsável.
 */
public class Venda {
    private int idVenda;
    private LocalDateTime dataHora;
    private Cliente cliente;
    private Funcionario funcionario;
    private List<ItemVenda> itens = new ArrayList<>();
    private int idCliente;
    private int idFuncionario;
    private double valorTotal;

    /**
     * Construto da classe ItemVenda.
     */
    public Venda(){
        
    }
    
    public Venda(int idVenda, LocalDateTime dataHora, Cliente cliente, Funcionario funcionario, List<ItemVenda> itens, int idCliente, int idFuncionario, double valorTotal) {
        this.idVenda = idVenda;
        this.dataHora = dataHora;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.itens = itens;
        this.idCliente = idCliente;
        this.idFuncionario = idFuncionario;
        this.valorTotal = valorTotal;
    }
    
    //Getters

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
    
    public void setDataHora(LocalDateTime dataHora){
        this.dataHora = dataHora;
    }

    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    
    public int getIdCliente() {
        return idCliente;
    }
    
    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }
    
    public int getIdFuncionario() {
        return idFuncionario;
    }
    
    public void setFuncionario(Funcionario funcionario){
        this.funcionario = funcionario;
    }
    
    public void setIdFuncionario(int idFuncionario){
        this.idFuncionario = idFuncionario;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }
    
    
    
    /**
     * Adicionar um item à venda.
     */
    public void adcionarItem(ItemVenda item){
        this.itens.add(item);
    }
    
    public double getValorTotal(){
        return valorTotal;
    }
    
    public void setValorTotal(double valorTotal){
        this.valorTotal = valorTotal;
    }
    
    @Override
    public String toString(){
        String itensStr = "";
        
        for (ItemVenda item : itens) {
            itensStr += "  - " + item + "\n";
        }
        
        return "Venda {\n" +
               " Data/Hora: " + dataHora + "\n" +
               " Cliente: '" + cliente.getNome() + "\n" +
               " Funcionário: " + funcionario.getNome() + "\n" +
               " Itens: \n" + itensStr +
               " Valor Total R$" + getValorTotal() + "\n" +
               '}';
    }

}
