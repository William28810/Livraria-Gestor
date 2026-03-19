/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.time.LocalDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author sidne
 */
public class RelatorioDao {
    public String relatorioVendasPorPeriodo(LocalDate inicio, LocalDate fim){
        
        StringBuilder relatorio = new StringBuilder();
        
        String sql = """
                     SELECT v.id_venda, v.data_horario, c.nome_cliente, SUM(i.preco_unitario) AS v.valor_total
                     FROM venda v
                     JOIN cliente c ON v.id_cliente = c.id_cliente
                     JOIN item_venda i ON v.id_venda = i.id_venda
                     WHERE DATE(v.data_horario) BETWEEN ? AND ?
                     GROUP BY v.id_venda, v.data_horario, c.nome_cliente
                     ORDER BY v.data_horario
                     """;
        try{
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setDate(1, java.sql.Date.valueOf(inicio));
            stmt.setDate(2, java.sql.Date.valueOf(fim));
            
            ResultSet rs = stmt.executeQuery();
            
            relatorio.append("RELATÓRIO DE VENDAS\n\n");
            
            while(rs.next()){
                relatorio.append("Venda: ")
                         .append(rs.getInt("id_venda"))
                         .append(" | Cliente: ")
                         .append(rs.getString("nome_cliente"))
                         .append(" | valor: R$ ")
                         .append(rs.getDouble("valor_total"))
                         .append(" | Data: ")
                         .append(rs.getTimestamp("data_horario"))
                         .append("\n");
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
        }catch(SQLException e){
            relatorio.append("Erro ao gerar relatório: ").append(e.getMessage());
        }
        
        return relatorio.toString();
    }

    public String relatorioLivrosMaisVendidos(LocalDate inicio, LocalDate fim){
        
        StringBuilder relatorio = new StringBuilder();
        
        String sql = """
                     SELECT l.titulo, SUM(i.quantidade) AS total_vendido
                     FROM item_venda i
                     JOIN livro l ON i.id_livro = l.id_livro
                     JOIN venda v ON i.id_venda = v.id_venda
                     WHERE DATE(v.data_horario) BETWEEN ? AND ?
                     GROUP BY l.titulo
                     ORDER BY total_vendido DESC
                     """;
        try{
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setDate(1, java.sql.Date.valueOf(inicio));
            stmt.setDate(2, java.sql.Date.valueOf(fim));
            
            ResultSet rs = stmt.executeQuery();
            
            relatorio.append("LIVROS MAIS VENDIDOS\n\n");
            
            while(rs.next()){
                relatorio.append("Livro: ")
                         .append(rs.getString("titulo"))
                         .append(" | Quantidade vendida: ")
                         .append(rs.getInt("total_vendido"))
                         .append("\n");
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
        }catch(SQLException e){
            relatorio.append("Erro ao gerar relatório: ").append(e.getMessage());
        }
        
        return relatorio.toString();
    }

    public String relatorioDesempenhoVendas(LocalDate inicio, LocalDate fim){
        
        StringBuilder relatorio = new StringBuilder();
        
        String sql = """
                     SELECT u.nome_funcionario, SUM(i.preco_unitario) AS total_vendas
                     FROM venda v
                     JOIN usuario u ON v.id_usuario = u.id_usuario
                     JOIN item_venda i ON v.id_venda = i.id_venda
                     WHERE DATE(v.data_horario) BETWEEN ? AND ?
                     GROUP BY u.nome_funcionario
                     ORDER BY total_vendas DESC
                     """;
        try{
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setDate(1, java.sql.Date.valueOf(inicio));
            stmt.setDate(2, java.sql.Date.valueOf(fim));
            
            ResultSet rs = stmt.executeQuery();
            
            relatorio.append("DESEMPENHO DE VENDAS\n\n");
            
            while(rs.next()){
                relatorio.append("Funcionario: ")
                         .append(rs.getString("nome_funcionario"))
                         .append(" | Total vendido: R$ ")
                         .append(rs.getDouble("total_vendas"))
                         .append("\n");
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
        }catch(SQLException e){
            relatorio.append("Erro ao gerar relatório: ").append(e.getMessage());
        }
        
        return relatorio.toString();
    }
}

