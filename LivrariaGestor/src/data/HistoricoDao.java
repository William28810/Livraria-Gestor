/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Funcionario;
import model.Historico;

/**
 *
 * @author sidne
 */
public class HistoricoDao {
    public void cadastrarHistorico(Historico historico){
        String sql = "INSERT INTO historico (tipo_acao, data_horario, id_usuario, tabela_movimentada, id_registro_movimento) VALUES(?,?,?,?,?)";
        
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1,historico.getTipoAcao());
            stmt.setTimestamp(2,java.sql.Timestamp.valueOf(historico.getDataHora()));
            stmt.setInt(3, historico.getIdFuncionario());
            stmt.setString(4,historico.getTabelaMovimentada());
            stmt.setInt(5,historico.getIdRegistroMovimento());
            
            stmt.executeUpdate();
            
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            
            System.out.println("Erro ao registrar histórico: " + e.getMessage());
            
        }
    }
    
    public List<Historico> listarHistorico(){
        List<Historico> lista = new ArrayList<>();
        
        Connection conn = Conexao.conectar();
    
        String sql = """
                     SELECT
                     h.id_historico,
                     h.tipo_acao,
                     h.tabela_movimentada,
                     h.id_registro_movimento,
                     h.data_horario,
                     u.id_usuario,
                     u.nome_funcionario
                     FROM historico h
                     JOIN usuario u
                     ON h.id_usuario = u.id_usuario
                     ORDER BY h.data_horario DESC
                     """;
        
        try{
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Historico historico = new Historico();
                
                historico.setIdHistorico(rs.getInt("id_historico"));
                historico.setTipoAcao(rs.getString("tipo_acao"));
                historico.setTabelaMovimentada(rs.getString("tabela_movimentada"));
                historico.setIdRegistroMovimento(rs.getInt("id_registro_movimento"));
                historico.setDataHora(rs.getTimestamp("data_horario").toLocalDateTime());
                historico.setIdFuncionario(rs.getInt("id_usuario"));
                
                Funcionario funcionario = new Funcionario();
                
                funcionario.setIdFuncionario(rs.getInt("id_usuario"));
                funcionario.setNome(rs.getString("nome_funcionario"));
                
                historico.setFuncionario(funcionario);
                
                lista.add(historico);
            }
            rs.close();
            stmt.close();
            conn.close();
            
        }catch(Exception e){
            System.out.println("Erro ao listar histórico: " + e.getMessage());
        
        }finally{
            Conexao.desconectar(conn);
        }
        
        return lista;
    }
}
