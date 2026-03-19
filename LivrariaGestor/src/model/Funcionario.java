package model;

/**
 * Representa um funcionario da livraria.
 * Pode exercer diferentes cargos e possui
 * nível de permissão para acesso ao sistema.
 */
public class Funcionario {
    
    private int idFuncionario;
    private String nome;
    private String cargo;
    private String nivelPermissao;
    private String login;
    private String senha;
    
    
    /**
     * Construção da classe Funcionario
     */
    public Funcionario (){
        
    }
    
    public Funcionario (String nome, String cargo, String nivelPermissao, String login, String senha){
        this.nome = nome;
        this.cargo = cargo;
        this.nivelPermissao = nivelPermissao;
        this.login = login;
        this.senha = senha;
    }

    // Getters e Setters
    
    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    public String getNivelPermissao() {
        return nivelPermissao;
    }

    public void setNivelPermissao(String nivelPermissao) {
        this.nivelPermissao = nivelPermissao;
    }
    
    public String getLogin() {
        return login;
    }

    /**
     * Define o login do funcionário
     */
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getSenha() {
        return senha;
    }

    /**
     * Define a senha do funcionário
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    /**
     * Retornar um representação textual do livro,
     * facilitando a visualização no console.
     */
    @Override
    public String toString(){
       return "Funcionario {\n" +
               "Nome = '" + nome + "\n" +
               "Cargo = '" + cargo + "\n" +
               "Pemissão = '" + nivelPermissao + "\n" +
               "Login = '" + login + "\n" +
               "Senha = '" + senha + "\n" +
               '}';
    }
}
