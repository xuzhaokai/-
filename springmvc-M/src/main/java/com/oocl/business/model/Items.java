package com.oocl.business.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "t_items")
public class Items {
	
	@Id
	@Column(name = "u_id")
	@GenericGenerator(name = "ug", strategy = "uuid")
	@GeneratedValue(generator = "ug")
	private String id;

	@Column(name = "menu_name", length = 50)
	private String menuName;

	@Column(name = "total_price")
	private double totalPrice;
	
	@Column(name = "count")
	private Integer count;
	
	@Column(name = "unit_price")
	private double unitPrice;


	@Column(name = "created_at", length = 50)
	private String createdAt;

	@Column(name = "updated_at", length = 50)
	private String updatedAt;
	
	@ManyToOne(targetEntity = Orders.class)
	@JoinColumn(name = "orders_id")
	@JsonIgnoreProperties("items")
	private Orders orders;
	
	@OneToOne(cascade=CascadeType.ALL,optional=true)
	@JoinColumn(name="menu_id",referencedColumnName="u_id")
	private Menu menu;

	public Items() {
	}

	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}



	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}


	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
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

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	
	
}
