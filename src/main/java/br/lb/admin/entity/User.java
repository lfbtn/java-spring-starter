package br.lb.admin.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import br.lb.financeiro.entity.CentrodeCustoContabil;

@Entity
@Table(name = "ADM_USERS")
public class User {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	@Column(unique = true)
	private String cpf;

	private String email;

	private String password;

	private BigDecimal custo;

	/*
	 * @ManyToOne(cascade = CascadeType.ALL) private CentrodeCustoContabil
	 * centrodeCustoContabil;
	 */

	private String profissao;

	private Date anoInclusao;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable
	private List<Role> roles;

	private Boolean enabled;

	private String token;

	private Boolean firstTimeLogin;

	private String pdfToken;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getCusto() {
		return custo;
	}

	public void setCusto(BigDecimal custo) {
		this.custo = custo;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public Date getAnoInclusao() {
		return anoInclusao;
	}

	public void setAnoInclusao(Date anoInclusao) {
		this.anoInclusao = anoInclusao;
	}

	/*
	 * public CentrodeCustoContabil getCentrodeCustoContabil() { return
	 * centrodeCustoContabil; }
	 * 
	 * public void setCentrodeCustoContabil(CentrodeCustoContabil
	 * centrodeCustoContabil) { this.centrodeCustoContabil =
	 * centrodeCustoContabil; }
	 */

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getFirstTimeLogin() {
		return firstTimeLogin;
	}

	public void setFirstTimeLogin(Boolean firstTimeLogin) {
		this.firstTimeLogin = firstTimeLogin;
	}

	public String getPdfToken() {
		return pdfToken;
	}

	public void setPdfToken(String pdfToken) {
		this.pdfToken = pdfToken;
	}

}
