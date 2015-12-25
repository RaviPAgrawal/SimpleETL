package com.ravi.etl;

public class EtlSubject {
	
	private Source source;
	
	public EtlSubject(){
		
	}
	
	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public void capitalizeContent(){
		source.capitalizeContent();
	}

	public void countWords(){
		source.countWords();
	}

}
