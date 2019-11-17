package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.*;

/**
 *
 * @author ivansuptitz
 */
public class DAO<T> {

    Conexao conn;

    public DAO() throws ClassNotFoundException, SQLException {
        this.conn = new Conexao();
    }

    public ArrayList<T> findAll(Class classe) throws SQLException {
        ArrayList<T> lst = new ArrayList<>();
        String sql = "SELECT * FROM";
        PreparedStatement st;
        ResultSet rs;

        if (classe == Categoria.class) {
            sql += " categoria";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Categoria obj = new Categoria();
                obj.setIdCategoria(rs.getInt("id_categoria"));
                obj.setDsCategoria(rs.getString("ds_categoria"));
                lst.add((T) obj);
            }
        } else if (classe == Produto.class) {
            sql += " produto";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Produto obj = new Produto();
                obj.setIdProduto(rs.getInt("id_produto"));
                obj.setIdCategoria(rs.getInt("id_categoria"));
                obj.setDsProduto(rs.getString("ds_produto"));
                obj.setVlPreco(rs.getBigDecimal("vl_preco"));
                lst.add((T) obj);
            }
        }

        return lst;
    }

    public ArrayList<T> findById(Class classe, Integer id) throws SQLException {
        ArrayList<T> lst = new ArrayList<>();
        String sql = "SELECT * FROM";
        PreparedStatement st;
        ResultSet rs;

        if (classe == Categoria.class) {
            sql += " categoria WHERE id_categoria = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                Categoria obj = new Categoria();
                obj.setIdCategoria(rs.getInt("id_categoria"));
                obj.setDsCategoria(rs.getString("ds_categoria"));
                lst.add((T) obj);
            }
        } else if (classe == Produto.class) {
            sql += " produto WHERE id_produto = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                Produto obj = new Produto();
                obj.setIdProduto(rs.getInt("id_produto"));
                obj.setIdCategoria(rs.getInt("id_categoria"));
                obj.setDsProduto(rs.getString("ds_produto"));
                obj.setVlPreco(rs.getBigDecimal("vl_preco"));
                lst.add((T) obj);
            }
        }

        return lst;
    }

    public void inserir(T classe) throws SQLException {
        String sql = "INSERT INTO ";
        PreparedStatement st;
        if (classe instanceof Produto) {
            Produto obj = (Produto) classe;//fazendo CAST
            sql += " produto(id_categoria, ds_produto, vl_preco)"
                    + " VALUES (?, ?, ?)";
            st = conn.prepareStatement(sql);
            st.setInt(1, obj.getIdCategoria());
            st.setString(2, obj.getDsProduto());
            st.setBigDecimal(3, obj.getVlPreco());
        } else if (classe instanceof Categoria) {
            Categoria obj = (Categoria) classe;//fazendo CAST
            sql += " categoria(ds_categoria) VALUES (?)";
            st = conn.prepareStatement(sql);
            st.setString(1, obj.getDsCategoria());
        } else {
            throw new IllegalArgumentException("Método DAO não preparado para este objeto");
        }

        st.executeUpdate();
    }

    public void removeById(T classe, Integer id) throws SQLException {
        String sql = "DELETE FROM ";
        PreparedStatement st;
        if (classe instanceof Produto) {
            Produto obj = (Produto) classe;//fazendo CAST
            sql += " produto WHERE id_produto = ? ";
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
        } else if (classe instanceof Categoria) {
            Categoria obj = (Categoria) classe;//fazendo CAST
            sql += " categoria WHERE id_categoria = ? ";
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
        } else {
            throw new IllegalArgumentException("Método DAO não preparado para este objeto");
        }

        st.executeUpdate();
    }

    public void update(T classe, Integer id) throws SQLException {
        String sql = "UPDATE ";
        PreparedStatement st;
        if (classe instanceof Produto) {
            Produto obj = (Produto) classe;//fazendo CAST
            sql += " produto SET id_categoria = ?, ds_produto = ?, vl_preco = ? WHERE id_produto = " + id;
            st = conn.prepareStatement(sql);
            st.setInt(1, obj.getIdCategoria());
            st.setString(2, obj.getDsProduto());
            st.setBigDecimal(3, obj.getVlPreco());
        } else if (classe instanceof Categoria) {
            Categoria obj = (Categoria) classe;//fazendo CAST
            sql += " categoria SET ds_categoria = ? WHERE id_categoria = " + id;
            st = conn.prepareStatement(sql);
            st.setString(1, obj.getDsCategoria());
        } else {
            throw new IllegalArgumentException("Método DAO não preparado para este objeto");
        }

        st.executeUpdate();
    }
}
