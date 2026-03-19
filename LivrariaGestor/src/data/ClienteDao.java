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
import model.Cliente;
import model.Historico;

/**
 *
 * @author sidne
 */
public class ClienteDao {
    
    private Connection conexao;
    
    public void cadastrarCliente(Cliente cliente){
        String sql = "INSERT INTO cliente (nome_cliente, cpf, telefone, email, endereco) VALUES(?,?,?,?,?)";
        
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1,cliente.getNome());
            stmt.setString(2,cliente.getCpf());
            stmt.setString(3,cliente.getTelefone());
            stmt.setString(4,cliente.getEmail());
            stmt.setString(5,cliente.getEndereco());
            
            stmt.executeUpdate();
            
            //Pegar o ID do cliente gerado
            ResultSet rs = stmt.getGeneratedKeys();
            int idCliente = 0;
            
            if(rs.next()){
                idCliente = rs.getInt(1);
            }
            
            //Registrar histórico
            Historico historico = new Historico("CADASTRO", "cliente", idCliente, null, 1);
            HistoricoDao hdao = new HistoricoDao();
            hdao.cadastrarHistorico(historico);
            
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
            
        }
    }
    
    public List<Cliente> listarClientes(){
        
        List<Cliente> lista = new ArrayList<>();
        
        conexao = Conexao.conectar();
        
        String sql = "SELECT * FROM  cliente";
        
        try{
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Cliente c = new Cliente();
                
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setNome(rs.getString("nome_cliente"));
                c.setCpf(rs.getString("cpf"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                c.setEndereco(rs.getString("endereco"));
                
                lista.add(c);
            }
        }catch(Exception e){
            
            e.printStackTrace();
        
        }finally{
            Conexao.desconectar(conexao);
        }
        
        return lista;
    }
    
    public void excluirCliente(int idCliente){
        Connection conexao = Conexao.conectar();
        
        String sql = "DELETE from cliente WHERE id_cliente = ?";
        
        try{
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            
            stmt.executeUpdate();
            
            //Registrar histórico
            Historico historico = new Historico("EXCLUSAO", "cliente", idCliente, null, 1);
            HistoricoDao hdao = new HistoricoDao();
            hdao.cadastrarHistorico(historico);
        
        }catch(Exception e){
            e.printStackTrace();
            
        }finally{
            
            Conexao.desconectar(conexao);
            
        }
    }
    
    public void editarCliente(Cliente cliente){
        
        Connection conexao = Conexao.conectar();
        
        String sql = "UPDATE cliente SET nome_cliente=?, cpf=?, telefone=?, email=?, endereco=? WHERE id_cliente=?";
        
        try{
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1,cliente.getNome());
            stmt.setString(2,cliente.getCpf());
            stmt.setString(3,cliente.getTelefone());
            stmt.setString(4,cliente.getEmail());
            stmt.setString(5,cliente.getEndereco());
            stmt.setInt(6, cliente.getIdCliente());
            
            stmt.executeUpdate();
            
            //Registrar histórico
            Historico historico = new Historico("EDICAO", "cliente", cliente.getIdCliente(), null, 1);
            HistoricoDao hdao = new HistoricoDao();
            hdao.cadastrarHistorico(historico);
            
        }catch (Exception e){
            
            System.out.println("Erro ao editar cliente: " + e.getMessage());
            
        }finally{
            
            Conexao.desconectar(conexao);
            
        }
        
        
    }
    
    public boolean cpfExiste(String cpf, int idCliente){
            
        Connection conexao = Conexao.conectar();
            
        String sql = "SELECT id_cliente FROM cliente WHERE cpf=? AND id_cliente!=?";
            
        try{
                
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cpf);
                
            ResultSet rs = stmt.executeQuery();
                
            return rs.next();
                
        }catch (Exception e){
                
            return false;
                
        }finally{
            Conexao.desconectar(conexao);
        }
    }
    
    public List<Cliente> buscarCliente(String texto) {

        List<Cliente> lista = new ArrayList<>();
        
        Connection conexao = Conexao.conectar();
        
        String sql = "SELECT * FROM cliente WHERE nome_cliente LIKE ? OR cpf LIKE ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
           
            
            stmt.setString(1, "%" + texto + "%");
            stmt.setString(2, "%" + texto + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome_cliente"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setEndereco(rs.getString("endereco"));

                lista.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Conexao.desconectar(conexao);
        }

        return lista;
    }
}


