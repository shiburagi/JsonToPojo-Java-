package com.example;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import java.io.Serializable;

public class Example extends RealmObject implements Serializable{

	@SerializedName("ApiKey")
	@Expose
	private String type;
	@SerializedName("ApiKey")
	@Expose
	private Properties properties;

	public String getType(){
		return type;
	}

	public void setType(String type){ 
		this.type=type;
	}

	public Properties getProperties(){
		return properties;
	}

	public void setProperties(Properties properties){ 
		this.properties=properties;
	}

}
