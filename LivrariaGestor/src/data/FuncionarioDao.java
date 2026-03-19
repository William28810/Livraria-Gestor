/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import model.Funcionario;
import model.Historico;

/**
 *
 * @author sidne
 */
public class FuncionarioDao {
    private Connection conexao;
    
    public void cadastrarFuncionario(Funcionario funcionario){
        String sql = "INSERT INTO usuario (nome_funcionario, cargo, nivel_permissao, login, senha) VALUES(?,?,?,?,?)";
        
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1,funcionario.getNome());
            stmt.setString(2,funcionario.getCargo());
            stmt.setString(3,funcionario.getNivelPermissao());
            stmt.setString(4,funcionario.getLogin());
            stmt.setString(5,funcionario.getSenha());
            
            stmt.executeUpdate();
            
            //Pegar o ID do cliente gerado
            ResultSet rs = stmt.getGeneratedKeys();
            int idFuncionario = 0;
            
            if(rs.next()){
                idFuncionario = rs.getInt(1);
            }
            
            //Registrar histórico
            Historico historico = new Historico("CADASTRO", "usuario", idFuncionario, null, 1);
            HistoricoDao hdao = new HistoricoDao();
            hdao.cadastrarHistorico(historico);
            
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            
            System.out.println("Erro ao cadastrar usuario: " + e.getMessage());
            
        }
    }
    
    public List<Funcionario> listarFuncionario(){
        
        List<Funcionario> lista = new ArrayList<>();
        
        conexao = Conexao.conectar();
        
        String sql = "SELECT * FROM  usuario";
        
        try{
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Funcionario f = new Funcionario();
                
                f.setIdFuncionario(rs.getInt("id_usuario"));
                f.setNome(rs.getString("nome_funcionario"));
                f.setCargo(rs.getString("cargo"));
                f.setNivelPermissao(rs.getString("nivel_permissao"));
                f.setLogin(rs.getString("login"));
                f.setSenha(rs.getString("senha"));
                
                lista.add(f);
            }
        }catch(Exception e){
            
            e.printStackTrace();
        
        }finally{
            Conexao.desconectar(conexao);
        }
        
        return lista;
    }
    
    public void excluirFuncionario(int idFuncionario){
        Connection conexao = Conexao.conectar();
        
        String sql = "DELETE from usuario WHERE id_usuario = ?";
        
        try{
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idFuncionario);
            
            stmt.executeUpdate();
            
            //Registrar histórico
            Historico historico = new Historico("EXCLUSAO", "usuario", idFuncionario, null, 1);
            HistoricoDao hdao = new HistoricoDao();
            hdao.cadastrarHistorico(historico);
            
        }catch(Exception e){
            
            e.printStackTrace();
            
        }finally{
            
            Conexao.desconectar(conexao);
            
        }
    }
    
    public void editarFuncionario(Funcionario funcionario){
        
        int resposta = 0;
        
        try{
            
            Connection conexao = Conexao.conectar();
            
            String sql = "UPDATE usuario SET nome_funcionario=?, cargo=?, nivel_permissao=?, login=?, senha=? WHERE id_usuario=?";
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1,funcionario.getNome());
            stmt.setString(2,funcionario.getCargo());
            stmt.setString(3,funcionario.getNivelPermissao());
            stmt.setString(4,funcionario.getLogin());
            stmt.setString(5,funcionario.getSenha());
            stmt.setInt(6, funcionario.getIdFuncionario());
            
            stmt.executeUpdate();
            
            //Registrar histórico
            Historico historico = new Historico("EDICAO", "cliente", funcionario.getIdFuncionario(), null, 1);
            HistoricoDao hdao = new HistoricoDao();
            hdao.cadastrarHistorico(historico);
            
        }catch (Exception e){
            
            System.out.println("Erro ao editar usuario: " + e.getMessage());
            
        }finally{
            
            Conexao.desconectar(conexao);
            
        }
    }
    
    public Funcionario buscarFuncionarioPorId(int id) {
        
        Funcionario funcionario = null;

        try {
            Connection conn = Conexao.conectar();
            
            String SQL = "SELECT * FROM usuario WHERE id_usuario LIKE ?";
            
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();          
            
            if (rs.next()) {

                funcionario = new Funcionario(

                    rs.getString("nome_funcionario"),
                    rs.getString("cargo"),
                    rs.getString("nivel_permissao"),
                    rs.getString("login"),
                    rs.getString("senha")
                    
                );

                funcionario.setIdFuncionario(rs.getInt("id_Usuario"));
            }
            
            conn.close();

        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuario: " + e.getMessage());
        }finally {
            Conexao.desconectar(conexao);
        }

        return funcionario;
    }
}
