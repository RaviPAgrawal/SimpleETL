package com.ravi.etl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class TextFile implements Source {
	
	public static final String drivePath = "D:";
	
	public static final String capitalizeTaskDirPath = drivePath + File.separator + "EtlCapitalizeTaskDir";
	
	public static final String countWordsTaskDirPath = drivePath + File.separator + "EtlCountTaskDir";

	public static final String inputFolder = "input";
	
	public static final String outputFolder = "output";
	
	private EtlSubject etlSubject;
	
	public TextFile(){
		
	}
	
	public TextFile(EtlSubject etlSubject){
		this.etlSubject = etlSubject;
		this.etlSubject.setSource(this);
	}

	public void capitalizeContent(Object input, Object output) {
		System.out.println("\ncapitalizing content from Text file - Start");
		System.out.println("capitalizing content from Text file - Performing operation...");
		
		File inputDir = (File) input;
		File outputDir = (File) output;
		
		for (File inputFile : inputDir.listFiles()) {
			String fileContent = Utility.getStringDataFromFile(inputFile);
			try{
				File outputFile = new File(outputDir, inputFile.getName());
				PrintWriter outputWriter = new PrintWriter(outputFile);
				outputWriter.print(fileContent.toUpperCase());
				outputWriter.close();
			} catch(IOException e){
				e.printStackTrace();
			}
		}
		
		System.out.println("capitalizing content from Text file - End");
	}

	public void countWords(Object input, Object output) {
		System.out.println("\ncounting unique words from Text file - Start");
		System.out.println("counting unique words from Text file - Performing operation...");
		
		File inputDir = (File) input;
		File outputDir = (File) output;
		
		for (File inputFile : inputDir.listFiles()) {
			String fileContent = Utility.getStringDataFromFile(inputFile);
			String[] array = fileContent.split(" ");
			Map<String, Integer> contentCountMap = getContentCountMap(array);
			String outputData = getContentCountMapDataInString(contentCountMap);
			try{
				File outputFile = new File(outputDir, inputFile.getName());
				PrintWriter outputWriter = new PrintWriter(outputFile);
				outputWriter.print(outputData);
				outputWriter.close();
			} catch(IOException e){
				e.printStackTrace();
			}
		}
		
		System.out.println("counting unique words from Text file - End");
	}

	private String getContentCountMapDataInString(Map<String, Integer> contentCountMap) {
		String outputData = "";
		for (Map.Entry<String, Integer> entry : contentCountMap.entrySet()) {
			outputData = outputData + entry.getKey() + "-" + entry.getValue() + " ";
		}
		return outputData;
	}

	private Map<String, Integer> getContentCountMap(String[] array) {
		Map<String, Integer> contentCountMap = new HashMap<>();
		if(array != null && array.length > 0){
			for (String string : array) {
				string = string.trim();
				Integer count = contentCountMap.get(string);
				if(count != null){
					contentCountMap.put(string, ++count);
				}else{
					contentCountMap.put(string, 1);
				}
			}
		}
		return contentCountMap;
	}
	
}
