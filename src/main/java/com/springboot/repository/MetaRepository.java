package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.model.Data;
import com.springboot.model.Meta;

public interface MetaRepository extends JpaRepository<Meta, Integer>{

	public Meta findBySchemeName(String schemeName);

	// public void save(Data dataObject);
}
