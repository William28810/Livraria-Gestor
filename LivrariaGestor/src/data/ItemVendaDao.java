/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.ItemVenda;

/**
 *
 * @author sidne
 */
public class ItemVendaDao {
    
    public int inserirItemVenda(ItemVenda item){
        int idGerado = 0;
        
        Connection conn = Conexao.conectar();
        
        String sql = "INSERT INTO item_venda (id_venda, id_livro, quantidade, preco_unitario) VALUES(?,?,?,?)";
        
        try {
            
            PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            
            stmt.setInt(1,item.getIdVenda());
            stmt.setInt(2,item.getIdLivro());
            stmt.setInt(3,item.getQuantidade());
            stmt.setDouble(4,item.getPrecoReal());
            
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            
            if(rs.next()){
                idGerado = rs.getInt(1);
            }
            
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            
            System.out.println("Erro ao inserir item da venda: " + e.getMessage());
            
        }finally{
            Conexao.desconectar(conn);
        }
        
        return idGerado;
    }
    
    public List<ItemVenda> listarItensVenda(int idVenda){
        List<ItemVenda> lista = new ArrayList<>();
        
        ItemVenda itemVenda = null;
        
        Connection conexao = Conexao.conectar();
    
        String sql = "SELECT * FROM item_venda WHERE id_venda=?";
        
        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setInt(1, idVenda);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                itemVenda = new ItemVenda();
                
                itemVenda.setIdItemVenda(rs.getInt("id_itemvenda"));
                itemVenda.setIdVenda(rs.getInt("id_venda"));
                itemVenda.setIdLivro(rs.getInt("id_livro"));
                itemVenda.setQuantidade(rs.getInt("quantidade"));
                itemVenda.setPrecoReal(rs.getDouble("preco_unitario"));
                
                lista.add(itemVenda);
            }
        }catch(Exception e){
            System.out.println("Erro ao listar itens da venda: " + e.getMessage());
        
        }finally{
            Conexao.desconectar(conexao);
        }
        
        return lista;
    }
    
    
}
