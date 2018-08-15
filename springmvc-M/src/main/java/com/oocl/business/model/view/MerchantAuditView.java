package com.oocl.business.model.view;

import java.io.Serializable;

public class MerchantAuditView implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;
	
	private String account;
	
	private String storeNumber;
	
	private String idCard;
	
	private String merchantName;
	
	private String idCardImg;
	
	private String storeName;
	
	private String storeLocation;

	
	private String licenseImg;
	
	private String applicationOpinion;
	
	private Integer status;

	private Integer isDeleted;
	
	private String createdAt;
	
	private String updatedAt;
	
	public MerchantAuditView() {
		
	}

	public MerchantAuditView(String id, String account, String storeNumber, String idCard, String merchantName, String idCardImg, String storeName, String storeLocation, String licenseImg, String applicationOpinion, Integer status, Integer isDeleted, String createdAt, String updatedAt) {
		this.id = id;
		this.account = account;
		this.storeNumber = storeNumber;
		this.idCard = idCard;
		this.merchantName = merchantName;
		this.idCardImg = idCardImg;
		this.storeName = storeName;
		this.storeLocation = storeLocation;
		this.licenseImg = licenseImg;
		this.applicationOpinion = applicationOpinion;
		this.status = status;
		this.isDeleted = isDeleted;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
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

	public String getStoreNumber() {
		return storeNumber;
	}

	public void setStoreNumber(String storeNumber) {
		this.storeNumber = storeNumber;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getIdCardImg() {
		return idCardImg;
	}

	public void setIdCardImg(String idCardImg) {
		this.idCardImg = idCardImg;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreLocation() {
		return storeLocation;
	}

	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}

	public String getLicenseImg() {
		return licenseImg;
	}

	public void setLicenseImg(String licenseImg) {
		this.licenseImg = licenseImg;
	}

	public String getApplicationOpinion() {
		return applicationOpinion;
	}

	public void setApplicationOpinion(String applicationOpinion) {
		this.applicationOpinion = applicationOpinion;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
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
}
