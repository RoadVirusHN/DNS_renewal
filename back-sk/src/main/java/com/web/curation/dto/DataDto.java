package com.web.curation.dto;

import java.util.Arrays;

public class DataDto {
	private int d_index;
	private byte[] data_file;
	private int b_index;
	private int idx;
	
	public DataDto() {
	}

	public DataDto(int d_index, byte[] data_file, int b_index, int idx) {
		super();
		this.d_index = d_index;
		this.data_file = data_file;
		this.b_index = b_index;
		this.idx = idx;
	}
	
	public DataDto(byte[] data_file, int b_index, int idx) {
		super();
		this.data_file = data_file;
		this.b_index = b_index;
		this.idx = idx;
	}

	public int getD_index() {
		return d_index;
	}

	public void setD_index(int d_index) {
		this.d_index = d_index;
	}

	public byte[] getdata_file() {
		return data_file;
	}

	public void setdata_file(byte[] data_file) {
		this.data_file = data_file;
	}

	public int getB_index() {
		return b_index;
	}

	public void setB_index(int b_index) {
		this.b_index = b_index;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	@Override
	public String toString() {
		return "DataDto [d_index=" + d_index + ", data_file=" + Arrays.toString(data_file) + ", b_index=" + b_index + ", idx="
				+ idx + "]";
	}


}
