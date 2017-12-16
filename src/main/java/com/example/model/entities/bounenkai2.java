package com.example.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bounenkai2")

public class bounenkai2 {

	// プライマリキーカラムにユニークな値を自動生成
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// NAMEカラムを定義
	@Column(name = "name", nullable = false)
	private String name;

	// 出欠フラグを定義
	@Column(name = "flag", nullable = false)
	private String flag;

	// NAME2カラムを定義
	@Column(name = "name2")
	private String name2;


	// getterとsetter
	public Long getId() {
		return id;
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
		this.name = name2;

	}

	public static bounenkai2 create(String name, String flag, String name2) {
		bounenkai2 bounenkai2 = new bounenkai2();
		bounenkai2.name = name;
		bounenkai2.flag = flag;
		bounenkai2.name2 = name2;
		return bounenkai2;
	}

	public bounenkai2 update(String flag) {
		this.flag = flag;
		return this;
	}

}