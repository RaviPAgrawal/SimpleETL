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

	public void capitalizeContent(Object input, Object output){
		source.capitalizeContent(input, output);
	}

	public void countWords(Object input, Object output){
		source.countWords(input, output);
	}

}
