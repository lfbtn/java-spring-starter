package br.lb.admin.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADM_NEWS")
public class News {

    @Id
    @GeneratedValue
    private Integer id;

    private String title;
    private String text;
    private Date inclusao;
    private Date publicacao;
    private Date remocao;
    private boolean visivel;
    private String autor;
    private String fotografo;
    private String imagem;
    private String categoria1;
    private String categoria2;
    private String categoria3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public Date getInclusao() {
	return inclusao;
    }

    public void setInclusao(Date inclusao) {
	this.inclusao = inclusao;
    }

    public Date getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Date publicacao) {
        this.publicacao = publicacao;
    }

    public boolean isVisivel() {
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getRemocao() {
        return remocao;
    }

    public void setRemocao(Date remocao) {
        this.remocao = remocao;
    }

	public String getFotografo() {
		return fotografo;
	}

	public void setFotografo(String fotografo) {
		this.fotografo = fotografo;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getCategoria1() {
		return categoria1;
	}

	public void setCategoria1(String categoria1) {
		this.categoria1 = categoria1;
	}

	public String getCategoria2() {
		return categoria2;
	}

	public void setCategoria2(String categoria2) {
		this.categoria2 = categoria2;
	}

	public String getCategoria3() {
		return categoria3;
	}

	public void setCategoria3(String categoria3) {
		this.categoria3 = categoria3;
	}


	
}
