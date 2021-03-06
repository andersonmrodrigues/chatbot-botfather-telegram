/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.*;

/**
 *
 * @author Anderson e Jean
 */
public class Conexao {

    private final String driver = "org.postgresql.Driver";
    private final String url = "jdbc:postgresql://localhost:5432/trab";
    private final String usuario = "postgres";
    private final String senha = "postgres";
    private final Connection conexao;

    /**
     * Criando conexão com o banco de dados
     */
    public Conexao() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        conexao = DriverManager.getConnection(url, usuario, senha);
    }

    /**
     * Executa SQL
     */
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return conexao.prepareStatement(sql);
    }
}
