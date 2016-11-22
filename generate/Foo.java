package com.example;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import java.io.Serializable;

public class Foo extends RealmObject implements Serializable{

	@SerializedName("ApiKey")
	@Expose
	private Foo_ foo;
	@SerializedName("ApiKey")
	@Expose
	private String type;

	public Foo_ getFoo(){
		return foo;
	}

	public void setFoo(Foo_ foo){ 
		this.foo=foo;
	}

	public String getType(){
		return type;
	}

	public void setType(String type){ 
		this.type=type;
	}

}
