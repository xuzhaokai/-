package com.oocl.business.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.oocl.business.model.view.OrdersRequest;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "t_orders")
public class Orders {
	@Id
	@Column(name = "u_id")
	@GenericGenerator(name = "ug", strategy = "uuid")
	@GeneratedValue(generator = "ug")
	private String id;

	@ManyToOne(targetEntity = Store.class)
	@JoinColumn(name = "store_id")
	@JsonIgnoreProperties("orders")
	private Store store;

	@Column(name = "store_name",  length = 50)
	private String storeName;

	@ManyToOne(targetEntity = Customer.class)
	@JoinColumn(name = "customer_id")
	@JsonIgnoreProperties("orders")
	private Customer customer;
	
	@Column(name = "total_price")
	private double totalPrice;


	@Column(name = "store_img", length = 200)
	private String storeImg;

	@Column(name = "comments",length = 200)
	private String  comments;


	@Column(name = "grade")
	private double grade;
	
	@Column(name = "address",nullable = false, length = 100)
	private String  address;

	@Column(name = "consignee",nullable = false, length = 100)
	private String  consignee;
	
	@Column(name = "mobile",nullable = false, length = 20)
	private String  mobile;
	
	@Column(name = "remark",length = 200)
	private String  remark;
	
	private Integer status;

	@Column(name = "created_at", length = 50)
	private String createdAt;

	@Column(name = "updated_at", length = 50)
	private String updatedAt;
	
	
	@OneToMany(mappedBy = "orders",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JsonIgnoreProperties("orders")
	private List<Items> items;
	

	public Orders() {
	}

	
	
	public List<Items> getItems() {
		return items;
	}



	public void setItems(List<Items> items) {
		this.items = items;
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

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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


	public void setUpdateMsd(OrdersRequest orders){

		this.consignee = orders.getConsignee();
		this.mobile = orders.getMobile();
		this.address = orders.getAddress();
		if(orders.getStatus()!=null){
			this.status = orders.getStatus();
		}

	}

	public String getStoreImg() {
		return storeImg;
	}

	public void setStoreImg(String storeImg) {
		this.storeImg = storeImg;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
