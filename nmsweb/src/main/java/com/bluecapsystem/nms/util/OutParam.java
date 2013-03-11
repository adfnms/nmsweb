package com.bluecapsystem.nms.util;

public class OutParam<T> 
{
	
	private T param;
	
	
	public void set(T param)
	{
		this.param = param;
	}
	
	public T get()
	{
		return this.param;
	}
	
	@Override
	public String toString() 
	{
		return this.param.toString();
	}

}
