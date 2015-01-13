package com.mmx.util;

import java.math.BigDecimal;

public class Category {
	private BigDecimal catCode;
	private String catShtDesc;
	private String catDescription;
	
	public Category(){
		super();
	}

	public BigDecimal getCatCode() {
		return catCode;
	}

	public void setCatCode(BigDecimal catCode) {
		this.catCode = catCode;
	}

	public String getCatShtDesc() {
		return catShtDesc;
	}

	public void setCatShtDesc(String catShtDesc) {
		this.catShtDesc = catShtDesc;
	}

	public String getCatDescription() {
		return catDescription;
	}

	public void setCatDescription(String catDescription) {
		this.catDescription = catDescription;
	}
	
}
