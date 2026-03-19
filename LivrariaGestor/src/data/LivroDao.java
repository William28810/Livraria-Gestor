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
import model.Historico;
import model.Livro;

/**
 *
 * @author sidne
 */
public class LivroDao {
    
    private Connection conexao;
    
    public void cadastrarLivro(Livro livro){
        String sql = "INSERT INTO livro (titulo, autor, genero, publicacao, quantidade, preco, observacao) VALUES(?,?,?,?,?,?,?)";
        
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1,livro.getTitulo());
            stmt.setString(2,livro.getAutor());
            stmt.setString(3,livro.getGenero());
            stmt.setDate(4,java.sql.Date.valueOf(livro.getDataPublicacao()));
            stmt.setInt(5,livro.getQuantidade());
            stmt.setDouble(6,livro.getPreco());
            stmt.setString(7,livro.getObservacao());
            
            stmt.executeUpdate();
            
            //Pegar o ID do livro gerado
            ResultSet rs = stmt.getGeneratedKeys();
            int idLivro = 0;
            
            if(rs.next()){
                idLivro = rs.getInt(1);
            }
            
            //Registrar histórico
            Historico historico = new Historico("CADASTRO", "livro", idLivro, null, 1);
            HistoricoDao hdao = new HistoricoDao();
            hdao.cadastrarHistorico(historico);
            
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            
            System.out.println("Erro ao cadastrar livro: " + e.getMessage());
            
        }
    }
    
    public List<Livro> listarLivros(){
        
        List<Livro> lista = new ArrayList<>();
        
        conexao = Conexao.conectar();
        
        String sql = "SELECT * FROM  livro";
        
        try{
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Livro l = new Livro();
                
                l.setIdLivro(rs.getInt("id_livro"));
                l.setTitulo(rs.getString("titulo"));
                l.setAutor(rs.getString("autor"));
                l.setGenero(rs.getString("genero"));
                l.setDataPublicacao(rs.getDate("publicacao").toLocalDate());
                l.setQuantidade(rs.getInt("quantidade"));
                l.setPreco(rs.getDouble("preco"));
                l.setObservacao(rs.getString("observacao"));
                
                lista.add(l);
            }
        }catch(Exception e){
            
            e.printStackTrace();
        
        }finally{
            Conexao.desconectar(conexao);
        }
        
        return lista;
    }
    
    public void excluirLivro(int idLivro){
        Connection conexao = Conexao.conectar();
        
        String sql = "DELETE from livro WHERE id_livro = ?";
        
        try{
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idLivro);
            
            stmt.executeUpdate();
            
            //Registrar histórico
            Historico historico = new Historico("EXCLUSAO", "livro", idLivro, null, 1);
            HistoricoDao hdao = new HistoricoDao();
            hdao.cadastrarHistorico(historico);
            
        }catch(Exception e){
            
            e.printStackTrace();
            
        }finally{
            
            Conexao.desconectar(conexao);
            
        }
    }
    
    public void editarLivro(Livro livro){
        
        int resposta = 0;
        
        try{
            
            Connection conexao = Conexao.conectar();
            
            String sql = "UPDATE livro SET titulo=?, autor=?, genero=?, publicacao=?, quantidade=?, preco=?, observacao=? WHERE id_livro=?";
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1,livro.getTitulo());
            stmt.setString(2,livro.getAutor());
            stmt.setString(3,livro.getGenero());
            stmt.setDate(4,java.sql.Date.valueOf(livro.getDataPublicacao()));
            stmt.setInt(5,livro.getQuantidade());
            stmt.setDouble(6, livro.getPreco());
            stmt.setString(7,livro.getObservacao());
            stmt.setInt(8, livro.getIdLivro());
            
            stmt.executeUpdate();
            
            //Registrar histórico
            Historico historico = new Historico("EDICAO", "livro", livro.getIdLivro(), null, 1);
            HistoricoDao hdao = new HistoricoDao();
            hdao.cadastrarHistorico(historico);
            
        }catch (Exception e){
            
            System.out.println("Erro ao editar livro: " + e.getMessage());
            
        }finally{
            
            Conexao.desconectar(conexao);
            
        }
    }
    
    public List<Livro> buscarLivro(String texto) {

        List<Livro> lista = new ArrayList<>();
        
        try {
            Connection conexao = Conexao.conectar();
            
            String sql = "SELECT * FROM livro WHERE titulo LIKE ? OR genero LIKE ?";
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
           
            
            stmt.setString(1, "%" + texto + "%");
            stmt.setString(2, "%" + texto + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Livro l = new Livro();

                l.setIdLivro(rs.getInt("id_livro"));
                l.setTitulo(rs.getString("titulo"));
                l.setAutor(rs.getString("autor"));
                l.setGenero(rs.getString("genero"));
                l.setDataPublicacao(rs.getDate("publicacao").toLocalDate());
                l.setQuantidade(rs.getInt("quantidade"));
                l.setPreco(rs.getDouble("preco"));
                l.setObservacao(rs.getString("observacao"));

                lista.add(l);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar livro: " + e.getMessage());
        }finally {
            Conexao.desconectar(conexao);
        }

        return lista;
    }
    
    public Livro buscarLivroPorId(int id) {
        
        Livro livro = null;

        try {
            Connection conn = Conexao.conectar();
            
            String SQL = "SELECT * FROM livro WHERE id_livro LIKE ?";
            
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, id);
            
            ResultSet rst = stmt.executeQuery();          
            
            if (rst.next()) {

                livro = new Livro(

                    rst.getString("titulo"),
                    rst.getString("autor"),
                    rst.getString("genero"),
                    rst.getDate("publicacao").toLocalDate(),
                    rst.getInt("quantidade"),
                    rst.getDouble("preco"),
                    rst.getString("observacao")
                
                );

                livro.setIdLivro(rst.getInt("id_Livro"));
            }
            
            conn.close();

        } catch (SQLException e) {
            System.out.println("Erro ao buscar livro: " + e.getMessage());
        }finally {
            Conexao.desconectar(conexao);
        }

        return livro;
    }

}
