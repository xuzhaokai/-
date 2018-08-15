package com.oocl.business.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "t_business")
public class Business {
	@Id
	@Column(name = "u_id")
	@GenericGenerator(name = "ug", strategy = "uuid")
	@GeneratedValue(generator = "ug")
	private String id;

	@Column(name = "account", nullable = false, length = 50, unique = true)
	private String account;

	@Column(name = "password", nullable = false, length = 50)
	private String password;

	private Integer type;

	private Integer status;

	@Column(name = "last_login_at", length = 50)
	private String lastLoginAt;

	@Column(name = "created_at", length = 50)
	private String createdAt;

	@Column(name = "updated_at", length = 50)
	private String updatedAt;

	@OneToMany(mappedBy = "business",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	@JsonIgnoreProperties("business")
	private List<Store> stores;
	// 使用JPA的时候，如果AB两个实体间是一对多，多对一的关系，如果不在@OneToMany里加入
	// mappedBy属性(相当于inverse=”true”)会导致自动生成一个多余的中间表

	
	public Business() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLastLoginAt() {
		return lastLoginAt;
	}

	public void setLastLoginAt(String lastLoginAt) {
		this.lastLoginAt = lastLoginAt;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Store> getStores() {
		return stores;
	}

	public void setStores(List<Store> stores) {
		this.stores = stores;
	}
	

}
