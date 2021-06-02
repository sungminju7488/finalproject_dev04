package com.dev4.sunbbang.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class PageVO {

	private int pagaNo = 0;
	private int pageSize = 10;
	private String condition = "";
	private String keyword = "";
}
