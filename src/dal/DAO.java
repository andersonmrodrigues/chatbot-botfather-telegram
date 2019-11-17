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
    public ArrayList<T> findById(Class classe, String id) throws SQLException {
        ArrayList<T> lst = new ArrayList<>();
        String sql = "SELECT * FROM";
        PreparedStatement st;
        ResultSet rs;

        if (classe == Categoria.class) {
            sql += " categoria WHERE id_categoria = ?";
            st = conn.prepareStatement(sql);
            st.setString(1, id);
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
            st.setString(1, id);
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

    public void inserir(T obj) throws SQLException {
        String sql = "INSERT INTO ";
        PreparedStatement st;

        if (obj instanceof Cidade) {
            Cidade c = (Cidade) obj;//fazendo CAST
            sql += " cidade (uf, descricao, saude, educacao, renda"
                    + ", ifdm, ranking_estadual, ranking_nacional)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            st = conn.prepareStatement(sql);
            st.setString(1, c.getUf());
            st.setString(2, c.getDescricao());
            st.setDouble(3, c.getSaude());
            st.setDouble(4, c.getEducacao());
            st.setDouble(5, c.getRenda());
            st.setDouble(6, c.getIfdm());
            st.setInt(7, c.getRankingEstadual());
            st.setInt(8, c.getRankingNacional());
        } else if (obj instanceof UnidadeFederacao) {
            UnidadeFederacao c = (UnidadeFederacao) obj;//fazendo CAST
            sql += " unidade_federacao (uf, descricao, media_saude, media_educacao"
                    + ", media_renda, media_ifdm)"
                    + " VALUES (?, ?, ?, ?, ?, ?)";

            st = conn.prepareStatement(sql);
            st.setString(1, c.getUf());
            st.setString(2, c.getDescricao());
            st.setDouble(3, c.getMediaSaude());
            st.setDouble(4, c.getMediaEducacao());
            st.setDouble(5, c.getMediaRenda());
            st.setDouble(6, c.getMediaIfdm());
        } else {
            throw new IllegalArgumentException("Método DAO não preparado para este objeto");
        }

        st.executeUpdate();
    }
}
