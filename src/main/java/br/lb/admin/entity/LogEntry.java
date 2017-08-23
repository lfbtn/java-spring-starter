package br.lb.admin.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SYS_LOG")
public class LogEntry {

	@Id
	@GeneratedValue
	private Integer id;

	private Integer usuario;
	private String tipo; // inclusao, delete, update
	private String resumo;
	private Date inclusao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUsuario() {
		return usuario;
	}

	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public Date getInclusao() {
		return inclusao;
	}

	public void setInclusao(Date inclusao) {
		this.inclusao = inclusao;
	}

}
