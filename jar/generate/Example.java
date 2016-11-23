package com.example;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import java.io.Serializable;

public class Example extends RealmObject implements Serializable{

	@SerializedName("ApiKey")
	@Expose
	private String name;

	public String getName(){
		return name;
	}

	public void setName(String name){ 
		this.name=name;
	}

}
