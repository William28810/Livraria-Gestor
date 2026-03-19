package model;

/**
 * Representa um item de uma venda.
 * Associa um livro à venda, registrada
 * a quantidade  e o preço no momento da compra.
 */
public class ItemVenda {
    
    private  int idItemVenda;
    private int idVenda;
    private int idLivro;
    private Livro livro;
    private Venda venda;
    private int quantidade;
    private double precoReal;
    

    /**
     * Construto da classe ItemVenda.
     */
    public ItemVenda(){
        
    }
    
    public ItemVenda(int idItemVenda, Livro livro, Venda venda, int quantidade, double precoReal, int idVenda, int idLivro) {
        this.idItemVenda = idItemVenda;
        this.livro = livro;
        this.venda = venda;
        this.quantidade = quantidade;
        this.precoReal = precoReal;
        this.idVenda = venda.getIdVenda();
        this.idLivro = livro.getIdLivro();
    }
    
    // Getters e Setters

    public int getIdItemVenda() {
        return idItemVenda;
    }

    public void setIdItemVenda(int idItemVenda) {
        this.idItemVenda = idItemVenda;
    }

    public Livro getLivro() {
        return livro;
    }
    
    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    
    public Venda getVenda() {
        return venda;
    }
    
    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoReal() {
        return precoReal;
    }

    public void setPrecoReal(double precoReal) {
        this.precoReal = precoReal;
    }
    
    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }
    
     public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }
    
    /**
     * Calcula o subtotal do item.
     */
    public double getSubtotal(){
        return quantidade * precoReal;
    }
    
    @Override
    public String toString(){
       return "ItemVenda {\n" +
               "Livro = '" + livro.getTitulo() + "\n" +
               "Quantidade = '" + quantidade + "\n" +
               "Preço Unitário = R$'" + precoReal + "\n" +
               "Subtotal = R$'" + getSubtotal() + "\n" +
               '}';
    }
}
