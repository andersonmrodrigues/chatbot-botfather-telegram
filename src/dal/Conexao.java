/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.*;

/**
 *
 * @author ivansuptitz
 */
public class Conexao {

    private final String driver = "org.postgresql.Driver";
    private final String url = "jdbc:postgresql://localhost:5432/trabalho";
    private final String usuario = "jean";
    private final String senha = "postgres";//aqui no lab, "" (VAZIO)
    private final Connection conexao;

    public Conexao() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        conexao = DriverManager.getConnection(url, usuario, senha);
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return conexao.prepareStatement(sql);
    }
}
