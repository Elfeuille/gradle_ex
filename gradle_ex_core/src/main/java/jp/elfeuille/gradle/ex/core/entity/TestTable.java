package jp.elfeuille.gradle.ex.core.entity;

import java.io.Serializable;

public class TestTable implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column test_table.id
	 * @mbggenerated
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column test_table.value
	 * @mbggenerated
	 */
	private String value;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table test_table
	 * @mbggenerated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column test_table.id
	 * @return  the value of test_table.id
	 * @mbggenerated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column test_table.id
	 * @param id  the value for test_table.id
	 * @mbggenerated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column test_table.value
	 * @return  the value of test_table.value
	 * @mbggenerated
	 */
	public String getValue() {
		return value;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column test_table.value
	 * @param value  the value for test_table.value
	 * @mbggenerated
	 */
	public void setValue(String value) {
		this.value = value;
	}
}