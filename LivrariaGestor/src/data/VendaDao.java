/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import model.ItemVenda;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Historico;
import model.Venda;

/**
 *
 * @author sidne
 */
public class VendaDao {
    public int registroVenda(Venda venda){
        int idGerado = 0;
        
        Connection conn = Conexao.conectar();
        
        String sql = "INSERT INTO venda (data_horario, id_cliente, id_usuario, valor_total) VALUES(?,?,?,?)";
        
        try {
            
            PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            
            stmt.setTimestamp(1,java.sql.Timestamp.valueOf(venda.getDataHora()));
            stmt.setInt(2,venda.getIdCliente());
            stmt.setInt(3,venda.getIdFuncionario());
            stmt.setDouble(4, venda.getValorTotal());
            
            stmt.executeUpdate();
            
            //Registrar histórico
            Historico historico = new Historico("VENDA", "venda", idGerado, null, 1);
            HistoricoDao hdao = new HistoricoDao();
            hdao.cadastrarHistorico(historico);
            
            ResultSet rs = stmt.getGeneratedKeys();
            
            if(rs.next()){
                idGerado = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            
            System.out.println("Erro ao registrar venda: " + e.getMessage());
            
        }finally{
            Conexao.desconectar(conn);
        }
        
        return idGerado;
    }
    
    public Venda buscarVendaPorId(int idVenda){
        
        Venda venda = null;
    
        Connection conexao = Conexao.conectar();
    
        String sql = "SELECT * FROM venda WHERE id_venda=?";
        
        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setInt(1, idVenda);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                venda = new Venda();
                
                venda.setIdVenda(rs.getInt("id_venda"));
                venda.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
                venda.setIdCliente(rs.getInt("id_cliente"));
                venda.setIdFuncionario(rs.getInt("id_usuario"));
                
            }
        }catch(Exception e){
            System.out.println("Erro ao buscar venda: " + e.getMessage());
        
        }finally{
            Conexao.desconectar(conexao);
        }
        
        return venda;
    }
    
    public List<Venda> listarVendas(){
        List<Venda> lista = new ArrayList<>();
        
        Connection conexao = Conexao.conectar();
    
        String sql = "SELECT * FROM venda";
        
        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Venda venda = new Venda();
                
                venda.setIdVenda(rs.getInt("id_venda"));
                venda.setDataHora(rs.getTimestamp("data_horario").toLocalDateTime());
                venda.setIdCliente(rs.getInt("id_cliente"));
                venda.setIdFuncionario(rs.getInt("id_usuario"));
                venda.setValorTotal(rs.getDouble("valor_total"));
                
                lista.add(venda);
            }
        }catch(Exception e){
            System.out.println("Erro ao listar venda: " + e.getMessage());
        
        }finally{
            Conexao.desconectar(conexao);
        }
        
        return lista;
    }
}
