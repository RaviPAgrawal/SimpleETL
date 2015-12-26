package com.ravi.etl;

public interface Source {
	
	void capitalizeContent(Object input, Object output);
	
	void countWords(Object input, Object output);
	
}
