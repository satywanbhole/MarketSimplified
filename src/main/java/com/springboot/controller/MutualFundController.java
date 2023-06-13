package com.springboot.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Meta;

@RestController
@RequestMapping("/mf")
public class MutualFundController {
	
	@Autowired
	public com.springboot.service.MutualFundServiceImp mutualFundServiceImp;

	    // API for particular scheme code data stored into database
		@GetMapping("API-1/{schemeCode}")
		public ResponseEntity<String> getMutualFundData(@PathVariable String schemeCode) {
			
			String data= mutualFundServiceImp.getSchemeDataByCode(schemeCode);
			
			return new ResponseEntity<String>(data, HttpStatus.OK);
		}
		
		// Search API for Scheme with SchemeName
		@GetMapping("API-2/{schemeName}")
		public ResponseEntity<Meta> getAllSchemeData(@PathVariable String schemeName){
			
			Meta meta= mutualFundServiceImp.getAllSchemeData(schemeName);
			
			return new ResponseEntity<Meta>(meta, HttpStatus.OK);
		}
		
		// Search API for Scheme with SchemeCode and Filter 	
		@GetMapping("API-3/{schemeCode}/{filter}")
	    public ResponseEntity<Map<String,String>> getMutualFundData(@PathVariable String schemeCode,@PathVariable String filter){
			
			Map<String, String> map= mutualFundServiceImp.getMutualFund(schemeCode, filter);
			
			return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
		}
}
