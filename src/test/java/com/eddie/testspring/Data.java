package com.eddie.testspring;

import java.util.List;

public class Data{
	private List<DatalistItem> datalist;
	private String pageTotal;
	private String recordTotal;
	private String pageNo;
	private String pageSize;

	public List<DatalistItem> getDatalist(){
		return datalist;
	}

	public String getPageTotal(){
		return pageTotal;
	}

	public String getRecordTotal(){
		return recordTotal;
	}

	public String getPageNo(){
		return pageNo;
	}

	public String getPageSize(){
		return pageSize;
	}
}