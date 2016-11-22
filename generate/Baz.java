package com.example;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import java.io.Serializable;

public class Baz extends RealmObject implements Serializable{

	@SerializedName("ApiKey")
	@Expose
	private String type;

	public String getType(){
		return type;
	}

	public void setType(String type){ 
		this.type=type;
	}

}
