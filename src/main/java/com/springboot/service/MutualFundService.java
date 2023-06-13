package com.springboot.service;

import java.util.Map;

import com.springboot.model.Meta;

public interface MutualFundService {

	// For save the URL Data into Database
	public String getSchemeDataByCode(String schemeCode);

	// For get all the schemeName data from database
	public Meta getAllSchemeData(String schemeName);

	// Search API for Scheme with Filter
	Map<String, String> getMutualFund(String schemeCode, String filter);
}
