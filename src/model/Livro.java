package model;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author ivansuptitz
 */
public class Livro implements Serializable { 
    private int id;
    private String titulo;
    private EnumCat categoria;
    private String autor;
    private Date dataPublicacao;

    public Livro(int id, String titulo, EnumCat categoria, String autor, Date dataPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.autor = autor;
        this.dataPublicacao = dataPublicacao;
    }

    public Livro() {
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public EnumCat getCategoria() {
        return categoria;
    }

    public void setCategoria(EnumCat categoria) {
        this.categoria = categoria;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
    
    @Override
    public Livro clone(){
        Livro novo = new Livro(id, titulo, categoria, autor, dataPublicacao);
        
        return novo;
    }
}
