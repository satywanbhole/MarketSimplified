package com.springboot.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="meta")
public class Meta{ 

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer schemeId;
	@JsonProperty(value = "fund_house",access = Access.AUTO)
	private String fundHouse;
	@JsonProperty(value = "scheme_Type",access = Access.AUTO)
	private String schemType;
	@JsonProperty(value = "scheme_category",access = Access.AUTO)
	private String schemeCategory;
	@JsonProperty(value = "scheme_code",access = Access.AUTO)
	private Integer schemeCode;
	@JsonProperty(value = "scheme_name",access = Access.AUTO)
	

//	@OneToMany(targetEntity = Data.class, cascade = CascadeType.ALL)
//	@JoinColumn(name="mp_fk", referencedColumnName = "schemeId")
//	private List<Data> data;
	
	private String schemeName;
	public Integer getSchemeId() {
		return schemeId;
	}
	public String getFundHouse() {
		return fundHouse;
	}
	public String getSchemType() {
		return schemType;
	}
	public String getSchemeCategory() {
		return schemeCategory;
	}
	public Integer getSchemeCode() {
		return schemeCode;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeId(Integer schemeId) {
		this.schemeId = schemeId;
	}
	public void setFundHouse(String fundHouse) {
		this.fundHouse = fundHouse;
	}
	public void setSchemType(String schemType) {
		this.schemType = schemType;
	}
	public void setSchemeCategory(String schemeCategory) {
		this.schemeCategory = schemeCategory;
	}
	public void setSchemeCode(Integer schemeCode) {
		this.schemeCode = schemeCode;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public Meta(Integer schemeId, String fundHouse, String schemType, String schemeCategory, Integer schemeCode,
			String schemeName) {
		super();
		this.schemeId = schemeId;
		this.fundHouse = fundHouse;
		this.schemType = schemType;
		this.schemeCategory = schemeCategory;
		this.schemeCode = schemeCode;
		this.schemeName = schemeName;
	}
	public Meta() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Meta [schemeId=" + schemeId + ", fundHouse=" + fundHouse + ", schemType=" + schemType
				+ ", schemeCategory=" + schemeCategory + ", schemeCode=" + schemeCode + ", schemeName=" + schemeName
				+ "]";
	}
	



	
	
	
	
	
	
}
