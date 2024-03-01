package com.telusko.service;

public interface IPurchaseOrder 
{
	public String purchase(String items[],Double price[],String[]toEmail)throws Exception;
	
}
