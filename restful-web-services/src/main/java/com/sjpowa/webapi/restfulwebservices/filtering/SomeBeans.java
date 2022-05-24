package com.sjpowa.webapi.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// We can use @JsonIgnoreProperties Annotation or
// @JsonIgnore, but I have to say that putting the annotation on the field
// could be better because if u have to change the name of the field
// then u have to change also in @JsonIgnoreProperties
@JsonIgnoreProperties(value = {"field2", "field3"})
public class SomeBeans {

	private String field1;
	
	// With JsonIgnore annotation we will not show in the response the data for this field
	//@JsonIgnore
	private String field2;
	private String field3;
	
	// CTOR
	public SomeBeans(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}
	
	// GETTERS and SETTERS
	public String getField1() {
		return field1;
	}
	
	public void setField1(String field1) {
		this.field1 = field1;
	}
	public String getField2() {
		return field2;
	}
	public void setField2(String field2) {
		this.field2 = field2;
	}
	public String getField3() {
		return field3;
	}
	public void setField3(String field3) {
		this.field3 = field3;
	}
	
}
