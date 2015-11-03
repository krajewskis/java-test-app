package com.sebek.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "account", uniqueConstraints = { @UniqueConstraint(columnNames = "acc_name") })
public class Account extends AbstractModel {

	@Id
	@Column(name = "acc_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;

	@NotNull
	@Column(name = "acc_name", unique = true)
	@Size(min = 4, max = 20)
	private String name;

	@NotNull
	@Column(name = "acc_email", unique = true)
	@Size(min = 5, max = 100)
	private String email;

	@Transient
	@Size(min = 5, max = 20)
	private String password;

	@Transient
	@Size(min = 5, max = 20)
	private String confirmPassword;

	@NotNull
	@Column(name = "acc_password", length = 64)
	String passwordSha;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "account")
	private Collection<AccountRole> accountRole = new ArrayList<AccountRole>();

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getPasswordSha() {
		return passwordSha;
	}

	public void setPasswordSha(String passwordSha) {
		this.passwordSha = passwordSha;
	}

	public Collection<AccountRole> getAccountRole() {
		return accountRole;
	}

	public void setAccountRole(Collection<AccountRole> accountRole) {
		this.accountRole = accountRole;
	}

}