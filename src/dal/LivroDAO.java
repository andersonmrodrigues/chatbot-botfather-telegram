package dal;

import java.sql.*;
import java.util.ArrayList;
import model.EnumCat;
import model.Livro;

/**
 *
 * @author ivansuptitz
 */
public class LivroDAO {
    
    /**
     * Faz a consulta dos livros no BD e retorna um arraylist com todos
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public ArrayList<Livro> consultar() throws ClassNotFoundException, SQLException{
        ArrayList<Livro> lstLivros = new ArrayList<>();
        Conexao conn = new Conexao();
        PreparedStatement st = conn.prepareStatement("select * from livro");
        ResultSet rs = st.executeQuery();//executa a consulta
        
        while(rs.next()){//retorna true se ainda tiver registro e posiciona no próximo registro
            Livro l = new Livro();
            l.setId(rs.getInt("id"));
            l.setTitulo(rs.getString("titulo"));
            l.setAutor(rs.getString("autor"));
            l.setCategoria(EnumCat.valueOf(rs.getString("categoria")));
            l.setDataPublicacao(rs.getDate("data_lancamento"));
            
            lstLivros.add(l);            
        }
        st.close();
        rs.close();
        
        return lstLivros;
    }
    
    public void inserir(Livro l) throws ClassNotFoundException, SQLException{
        Conexao conn = new Conexao();
        String sql = "insert into livro (titulo, autor, categoria, data_lancamento)\n" +
"values (?, ?, ?, ?);";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, l.getTitulo());
        st.setString(2, l.getAutor());
        st.setString(3, l.getCategoria().toString());
        st.setDate(4, l.getDataPublicacao());
        
        st.executeUpdate();//executa o comando no BD
    }
}
