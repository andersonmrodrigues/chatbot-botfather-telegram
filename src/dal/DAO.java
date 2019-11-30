package dal;

import java.sql.Date;
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

    public ArrayList<T> findAll(Class classe, Integer id) throws SQLException {
        ArrayList<T> lst = new ArrayList<>();
        String sql = "SELECT * FROM";
        PreparedStatement st;
        ResultSet rs;

        if (classe == Categoria.class) {
            sql += " categoria ORDER BY ds_categoria";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Categoria obj = new Categoria();
                obj.setIdCategoria(rs.getInt("id_categoria"));
                obj.setDsCategoria(rs.getString("ds_categoria"));
                lst.add((T) obj);
            }
        } else if (classe == Produto.class) {
            sql += " produto ORDER BY ds_produto ";
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
        } else if (classe == Pedido.class) {
            sql += " pedido WHERE fg_entregue IS NOT true AND fg_finalizado IS true ORDER BY dt_pedido DESC ";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Pedido obj = new Pedido();
                obj.setIdPedido(rs.getInt("id_pedido"));
                obj.setIdCliente(rs.getInt("id_cliente"));
                obj.setDtPedido(rs.getDate("dt_pedido"));
                obj.setFgFinalizado(rs.getBoolean("fg_finalizado"));
                obj.setFgEntregue(rs.getBoolean("fg_entregue"));
                lst.add((T) obj);
            }
        } else if (classe == PedidoItem.class) {
            sql += " pedido_item ";
            if (id != null) {
                sql += " WHERE id_pedido = ?";
            }
            sql += " ORDER BY id_pedido_item";
            st = conn.prepareStatement(sql);
            if (id != null) {
                st.setInt(1, id);
            }
            rs = st.executeQuery();
            while (rs.next()) {
                PedidoItem obj = new PedidoItem();
                obj.setIdPedidoItem(rs.getInt("id_pedido_item"));
                obj.setIdPedido(rs.getInt("id_pedido"));
                obj.setIdProduto(rs.getInt("id_produto"));
                obj.setQtPedido(rs.getInt("qt_pedido"));
                obj.setVlPreco(rs.getBigDecimal("vl_preco"));
                obj.setDsObservacao(rs.getString("ds_observacao"));
                lst.add((T) obj);
            }
        }

        return lst;
    }

    public T findPedidoByIdCliente(Class classe, Integer id) throws SQLException {
        ArrayList<T> lst = new ArrayList<>();
        String sql = "SELECT * FROM";
        PreparedStatement st;
        ResultSet rs;

        if (classe == Pedido.class) {
            sql += " pedido WHERE id_cliente = ? AND fg_entregue IS NOT true AND fg_finalizado IS NOT true ";
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                Pedido obj = new Pedido();
                obj.setIdPedido(rs.getInt("id_pedido"));
                obj.setIdCliente(rs.getInt("id_cliente"));
                obj.setDtPedido(rs.getDate("dt_pedido"));
                obj.setFgEntregue(rs.getBoolean("fg_entregue"));
                obj.setFgFinalizado(rs.getBoolean("fg_finalizado"));
                lst.add((T) obj);
            }
        }
        try {
            return lst.get(0);
        } catch (Exception e) {
            return null;
        }
    }

    public T findById(Class classe, Integer id) throws SQLException {
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
        } else if (classe == Cliente.class) {
            sql += " cliente WHERE id_cliente = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                Cliente obj = new Cliente();
                obj.setIdCliente(rs.getInt("id_cliente"));
                obj.setDsCliente(rs.getString("nm_cliente"));
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
                lst.add((T) obj);
            }
        } else if (classe == Pedido.class) {
            sql += " pedido WHERE id_pedido = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                Pedido obj = new Pedido();
                obj.setIdPedido(rs.getInt("id_pedido"));
                obj.setIdCliente(rs.getInt("id_cliente"));
                obj.setDtPedido(rs.getDate("dt_pedido"));
                obj.setFgEntregue(rs.getBoolean("fg_entregue"));
                obj.setFgFinalizado(rs.getBoolean("fg_finalizado"));
                lst.add((T) obj);
            }
        }
        try {
            return lst.get(0);
        } catch (Exception e) {
            return null;
        }
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
        } else if (classe instanceof Cliente) {
            Cliente obj = (Cliente) classe;//fazendo CAST
            sql += " cliente(id_cliente, nm_cliente) VALUES (?, ?);";
            st = conn.prepareStatement(sql);
            st.setInt(1, obj.getIdCliente());
            st.setString(2, obj.getDsCliente());
        } else if (classe instanceof Pedido) {
            Pedido obj = (Pedido) classe;//fazendo CAST
            sql += " pedido(id_cliente, dt_pedido, fg_finalizado, fg_entregue) VALUES (?, ?, ?, ?);";
            st = conn.prepareStatement(sql);
            st.setInt(1, obj.getIdCliente());
            st.setDate(2, obj.getDtPedido());
            st.setBoolean(3, obj.getFgFinalizado());
            st.setBoolean(4, obj.getFgEntregue());
        } else if (classe instanceof PedidoItem) {
            PedidoItem obj = (PedidoItem) classe;//fazendo CAST
            sql += " pedido_item(id_pedido, id_produto, qt_pedido, vl_preco, ds_observacao) VALUES (?, ?, ?, ?, ?);";
            st = conn.prepareStatement(sql);
            st.setInt(1, obj.getIdPedido());
            st.setInt(2, obj.getIdProduto());
            st.setInt(3, obj.getQtPedido());
            st.setBigDecimal(4, obj.getVlPreco());
            st.setString(5, obj.getDsObservacao());
        } else {
            throw new IllegalArgumentException("Método DAO não preparado para este objeto");
        }

        st.executeUpdate();
    }

    public void removeById(T classe) throws SQLException {
        String sql = "DELETE FROM ";
        PreparedStatement st;
        if (classe instanceof Produto) {
            Produto obj = (Produto) classe;//fazendo CAST
            sql += " produto WHERE id_produto = ? ";
            st = conn.prepareStatement(sql);
            st.setInt(1, obj.getIdProduto());
        } else if (classe instanceof Categoria) {
            Categoria obj = (Categoria) classe;//fazendo CAST
            sql += " categoria WHERE id_categoria = ? ";
            st = conn.prepareStatement(sql);
            st.setInt(1, obj.getIdCategoria());
        } else {
            throw new IllegalArgumentException("Método DAO não preparado para este objeto");
        }
        st.executeUpdate();
    }

    public void update(T classe) throws SQLException {
        try {
            String sql = "UPDATE ";
            PreparedStatement st;
            if (classe instanceof Produto) {
                Produto obj = (Produto) classe;//fazendo CAST
                sql += " produto SET id_categoria = ?, ds_produto = ?, vl_preco = ? WHERE id_produto = " + obj.getIdProduto();
                st = conn.prepareStatement(sql);
                st.setInt(1, obj.getIdCategoria());
                st.setString(2, obj.getDsProduto());
                st.setBigDecimal(3, obj.getVlPreco());
            } else if (classe instanceof Categoria) {
                Categoria obj = (Categoria) classe;//fazendo CAST
                sql += " categoria SET ds_categoria = ? WHERE id_categoria = " + obj.getIdCategoria();
                st = conn.prepareStatement(sql);
                st.setString(1, obj.getDsCategoria());
            } else if (classe instanceof Pedido) {
                Pedido obj = (Pedido) classe;//fazendo CAST
                sql += " pedido SET id_cliente=?, dt_pedido=?, fg_finalizado=?, fg_entregue=? WHERE id_pedido = " + obj.getIdPedido();
                st = conn.prepareStatement(sql);
                st.setInt(1, obj.getIdCliente());
                st.setDate(2, obj.getDtPedido());
                st.setBoolean(3, obj.getFgFinalizado());
                st.setBoolean(4, obj.getFgEntregue());
            } else {
                throw new IllegalArgumentException("Método DAO não preparado para este objeto");
            }

            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
