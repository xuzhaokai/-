package com.oocl.business.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "t_store")
public class Store {

	@Id
	@Column(name = "u_id")
	@GenericGenerator(name = "ug", strategy = "uuid")
	@GeneratedValue(generator = "ug")
	private String id;

	@Column(name = "merchant_store_number", nullable = false, length = 50, unique = true)
	private String merchantStoreNumber;// 店铺编号 外键,参照merchant表

	@ManyToOne(targetEntity = Business.class,fetch = FetchType.EAGER)
	@JoinColumn(name = "business_id")
	@JsonIgnoreProperties("stores")
	private Business business;

	@Column(name = "name", length = 50)
	private String name;// 店名

	@Column(name = "location", length = 50)
	private String location;

	@Column(name = "service_time", length = 50)
	private String serviceTime;

	@Column(name = "delivery_area", length = 50)
	private String deliveryArea;

	@Column(name = "img_first", length = 200)
	private String imgFirst;

	@Column(name = "img_second", length = 200)
	private String imgSecond;

	@Column(name = "store_img", length = 200)
	private String storeImg;

	private Double grade;
	
	private Integer status;

	@Column(name = "publicity", length = 200)
	private String publicity;

	@Column(name = "created_at", length = 200)
	@CreatedDate
	private String createdAt;

	@Column(name = "updated_at", length = 200)
	@LastModifiedDate
	private String updatedAt;
	
	@OneToMany(mappedBy="store",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JsonIgnoreProperties("store")
	@JsonIgnore
	private List<Menu> menus;
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name = "store_id")
	@JsonIgnoreProperties("store")
	@JsonIgnore
	private List<Orders> orders;

	public Store() {
	}


	public Store(String merchantStoreNumber, Business business, String name, String location, String serviceTime, String deliveryArea, String imgFirst, String imgSecond, String storeImg, Double grade, Integer status, String publicity, String createdAt, String updatedAt, List<Menu> menus, List<Orders> orders) {
		this.merchantStoreNumber = merchantStoreNumber;
		this.business = business;
		this.name = name;
		this.location = location;
		this.serviceTime = serviceTime;
		this.deliveryArea = deliveryArea;
		this.imgFirst = imgFirst;
		this.imgSecond = imgSecond;
		this.storeImg = storeImg;
		this.grade = grade;
		this.status = status;
		this.publicity = publicity;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.menus = menus;
		this.orders = orders;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMerchantStoreNumber() {
		return merchantStoreNumber;
	}

	public void setMerchantStoreNumber(String merchantStoreNumber) {
		this.merchantStoreNumber = merchantStoreNumber;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

	public String getDeliveryArea() {
		return deliveryArea;
	}

	public void setDeliveryArea(String deliveryArea) {
		this.deliveryArea = deliveryArea;
	}

	public String getImgFirst() {
		return imgFirst;
	}

	public void setImgFirst(String imgFirst) {
		this.imgFirst = imgFirst;
	}

	public String getImgSecond() {
		return imgSecond;
	}

	public void setImgSecond(String imgSecond) {
		this.imgSecond = imgSecond;
	}

	public String getStoreImg() {
		return storeImg;
	}

	public void setStoreImg(String storeImg) {
		this.storeImg = storeImg;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPublicity() {
		return publicity;
	}

	public void setPublicity(String publicity) {
		this.publicity = publicity;
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

	public void setStore(Store store){

		this.name = store.getName();
		this.location = store.getLocation();
		this.serviceTime = store.getServiceTime();
		this.deliveryArea = store.getDeliveryArea();
		this.imgFirst = store.getImgFirst();
		this.imgSecond = store.getImgSecond();
		this.storeImg = store.getStoreImg();
		this.publicity = store.getPublicity();
		this.updatedAt = store.getUpdatedAt();
	}

}
