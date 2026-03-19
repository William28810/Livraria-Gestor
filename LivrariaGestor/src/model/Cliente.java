package model;

/**
 * Representa um cliente da livraria.
 * Contém os dados básicos necessários para cadastro,
 * consulta e registro de venda.
 */
public class Cliente {
    private int IdCliente;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String endereco;
    
    
    /**
     * Construção da classe Clientes
     */
    public Cliente(){
        
    }
    
    public Cliente (String nome, String cpf, String telefone, String email, String endereco){
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    // Getters e Setters
    
    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    /**
     * Retornar um representação textual do livro,
     * facilitando a visualização no console.
     */
    @Override
    public String toString(){
       return "Cliente {\n" +
               "Nome = '" + nome + "\n" +
               "CPF = '" + cpf + "\n" +
               "Telefone = '" + telefone + "\n" +
               "E-mail = '" + email + "\n" +
               "Endereço = '" + endereco + "\n" +
               '}';
    }
}
