package com.oocl.business.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

@Entity
@Table(name = "t_menu")
public class Menu {

	@Id
	@Column(name = "u_id")
	@GenericGenerator(name = "ug", strategy = "uuid")
	@GeneratedValue(generator = "ug")
	private String id;

	@ManyToOne(targetEntity = Store.class,fetch = FetchType.EAGER)
	@JoinColumn(name = "store_id")
	@JsonIgnoreProperties("menus")
	private Store store;

	@Column(name = "name", length = 50)
	private String name;

	@Column(name = "introduced", length = 200)
	private String introduced;

	@Column(name = "price")
	private Double price;

	@Column(name = "priority", length = 50)
	private String priority;

	@Column(name = "img", length = 200)
	private String img;

	@Column(name = "status",columnDefinition="INT default 0", length = 200)
	private Integer status;// 0-下架(默认) 1-上架

	@Column(name = "created_at", length = 200)
	@CreatedDate
	private String createdAt;

	@Column(name = "updated_at", length = 200)
	@LastModifiedBy
	private String updatedAt;

	public Menu() {
		// TODO Auto-generated constructor stub
	}

	public Menu(String id, Store store, String name, String introduced, double price, String priority, String img,
			Integer status, String createdAt, String updatedAt) {
		super();
		this.id = id;
		this.store = store;
		this.name = name;
		this.introduced = introduced;
		this.price = price;
		this.priority = priority;
		this.img = img;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
	
	public void setMenu(Menu menu) {
		this.name = menu.getName();
		this.introduced = menu.getIntroduced();
		this.price = menu.getPrice();
		this.priority = menu.getPriority();
		this.img = menu.getImg();
		this.status = menu.getStatus();
	}
}
