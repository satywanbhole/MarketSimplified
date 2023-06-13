package com.springboot.model;

import java.util.ArrayList;
import java.util.List;

public class MutualFund {

    private Meta meta;
    private List<Data> data = new ArrayList<Data>();
	public Meta getMeta() {
		return meta;
	}
	public List<Data> getData() {
		return data;
	}
	public void setMeta(Meta meta) {
		this.meta = meta;
	}
	public void setData(List<Data> data) {
		this.data = data;
	}
	public MutualFund(Meta meta, List<Data> data) {
		super();
		this.meta = meta;
		this.data = data;
	}
	@Override
	public String toString() {
		return "MutualFund [meta=" + meta + ", data=" + data + "]";
	}
	public MutualFund() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
}
