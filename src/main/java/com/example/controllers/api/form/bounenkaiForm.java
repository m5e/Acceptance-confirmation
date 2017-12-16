package com.example.controllers.api.form;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class bounenkaiForm implements Serializable {

	private static final long serialVersionUID = 1330043957072942381L;

	private Long id;

	@Size(min = 1, max = 50)
	private String name;

	@Size(min = 1, max = 10)
	private String flag;

	@Size(min = 1, max = 50)
	private String name2;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}

}
