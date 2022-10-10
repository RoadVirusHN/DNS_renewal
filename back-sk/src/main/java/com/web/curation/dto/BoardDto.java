package com.web.curation.dto;


import java.sql.Blob;
import java.util.Arrays;

import javax.sql.rowset.serial.SerialBlob;

public class BoardDto{

	private int b_index;
	private int u_index;
	private String context;
	private String multi_file;
	private String reg_date;
	private String[] fileLoc;
	private byte[][] data_file;
	private SerialBlob[] b;
	
	public BoardDto() {
		super();
	}

	public BoardDto(int b_index, int u_index, String context, String multi_file, String reg_date) {
		super();
		this.b_index = b_index;
		this.u_index = u_index;
		this.context = context;
		this.multi_file = multi_file;
		this.reg_date = reg_date;
	}
	
	public BoardDto(int b_index, int u_index, String context, String multi_file) {
		super();
		this.b_index = b_index;
		this.u_index = u_index;
		this.context = context;
		this.multi_file = multi_file;
	}

	public BoardDto(int u_index, String context, String multi_file) {
		super();
		this.u_index = u_index;
		this.context = context;
		this.multi_file = multi_file;
	}

	public int getB_index() {
		return b_index;
	}

	public void setB_index(int b_index) {
		this.b_index = b_index;
	}

	public int getU_index() {
		return u_index;
	}

	public void setU_index(int u_index) {
		this.u_index = u_index;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getMulti_file() {
		return multi_file;
	}

	public void setMulti_file(String multi_file) {
		this.multi_file = multi_file;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String[] getFileLoc() {
		return fileLoc;
	}

	public void setFileLoc(String[] fileLoc) {
		this.fileLoc = fileLoc;
	}

	public byte[][] getData_file() {
		return data_file;
	}

	public void setData_file(byte[][] data_file) {
		this.data_file = data_file;
	}

	public SerialBlob[] getB() {
		return b;
	}

	public void setB(SerialBlob[] b) {
		this.b = b;
	}

	@Override
	public String toString() {
		return "BoardDto [b_index=" + b_index + ", u_index=" + u_index + ", context=" + context + ", multi_file="
				+ multi_file + ", reg_date=" + reg_date + ", fileLoc=" + Arrays.toString(fileLoc) + ", data_file="
				+ Arrays.toString(data_file) + ", b=" + Arrays.toString(b) + "]";
	}

}
