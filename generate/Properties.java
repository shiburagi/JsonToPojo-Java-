package com.example;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import java.io.Serializable;

public class Properties extends RealmObject implements Serializable{

	@SerializedName("ApiKey")
	@Expose
	private Bar bar;
	@SerializedName("ApiKey")
	@Expose
	private Foo foo;
	@SerializedName("ApiKey")
	@Expose
	private Baz baz;

	public Bar getBar(){
		return bar;
	}

	public void setBar(Bar bar){ 
		this.bar=bar;
	}

	public Foo getFoo(){
		return foo;
	}

	public void setFoo(Foo foo){ 
		this.foo=foo;
	}

	public Baz getBaz(){
		return baz;
	}

	public void setBaz(Baz baz){ 
		this.baz=baz;
	}

}
