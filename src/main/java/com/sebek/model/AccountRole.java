package com.sebek.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "account_role", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"acc_id", "accr_role" }) })
public class AccountRole extends AbstractModel {

	public final static String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";
	public final static String ROLE_USER = "ROLE_USER";
	public final static String ROLE_ADMIN = "ROLE_ADMIN";

	@Id
	@Column(name = "accr_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;

	@Column(name = "accr_role")
	@NotNull
	private String role;

	@ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "acc_id", nullable = false)
	private Account account;

	public AccountRole() {
		this.setRole(ROLE_ANONYMOUS);
	}

	public AccountRole(String role) {
		this.setRole(role);
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}