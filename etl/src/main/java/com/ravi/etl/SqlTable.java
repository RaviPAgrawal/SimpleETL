package com.ravi.etl;

public class SqlTable implements Source {
	
	private EtlSubject etlSubject;
	
	public SqlTable(){
		
	}
	
	public SqlTable(EtlSubject etlSubject){
		this.etlSubject = etlSubject;
		this.etlSubject.setSource(this);
	}

	public void capitalizeContent() {
		System.out.println("capitalizing content from sql table - Start");
		System.out.println("capitalizing content from sql table - Implementation goes here");
		System.out.println("capitalizing content from sql table - End");
	}

	public void countWords() {
		System.out.println("counting unique words from sql table - Start");
		System.out.println("counting unique words from sql table - Implementation goes here");
		System.out.println("counting unique words from sql table - End");
	}

}
