package com.qa.cql.test;

import org.testng.annotations.Test;

import com.qa.cql.base.CQLBase;

public class CQLTest {
	CQLBase cb=new CQLBase();
@Test	
public void init() throws Exception {
		
		
		CQLBase.getCQLConnection();
		CQLBase.closeCQLConnection();
		
	}
	

}
