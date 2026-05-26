/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
    conn = new conectaDAO().connectDB();
    String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
    
    try {

        prep = conn.prepareStatement(sql);
        prep.setString(1, produto.getNome());
        prep.setInt(2, produto.getValor());
        prep.setString(3, produto.getStatus());
        prep.execute();
        
    } catch (SQLException erro) {
        System.out.println("Erro ao cadastrar produto no DAO: " + erro.getMessage());
        erro.printStackTrace();
    } finally {
        try {
            if (prep != null) prep.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Erro ao fechar conexões: " + e.getMessage());
        }
    }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
    listagem.clear();
    conn = new conectaDAO().connectDB();
    String sql = "SELECT * FROM produtos";
    
    try {
        prep = conn.prepareStatement(sql);
        resultSet = prep.executeQuery();
        while (resultSet.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultSet.getInt("id"));
            produto.setNome(resultSet.getString("nome"));
            produto.setValor(resultSet.getInt("valor"));
            produto.setStatus(resultSet.getString("status"));
            listagem.add(produto);
        }
        
    } catch (SQLException erro) {
        System.out.println("Erro ao listar produtos no DAO:");
        erro.printStackTrace();
    } finally {
        try {
            if (resultSet != null) resultSet.close();
            if (prep != null) prep.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Erro ao fechar conexões de listagem: " + e.getMessage());
        }
    }
    return listagem;

    }
    
    
    
        
}

