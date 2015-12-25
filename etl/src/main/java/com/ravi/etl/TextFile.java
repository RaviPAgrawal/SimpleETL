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
	
	public static final String capitalizeFileName = "capitalizeMyContent.txt";
	
	public static final String countContentFileName = "countMyContent.txt";
	
	public static final String capitalizeTaskInputFilePath = capitalizeTaskDirPath + File.separator + inputFolder + File.separator + capitalizeFileName;

	public static final String capitalizeTaskOutputFilePath = capitalizeTaskDirPath + File.separator + outputFolder + File.separator + capitalizeFileName;
	
	public static final String countWordsTaskInputFilePath = countWordsTaskDirPath + File.separator + inputFolder + File.separator + countContentFileName;

	public static final String countWordsTaskOutputFilePath = countWordsTaskDirPath + File.separator + outputFolder + File.separator + countContentFileName;

	private EtlSubject etlSubject;
	
	public TextFile(){
		
	}
	
	public TextFile(EtlSubject etlSubject){
		this.etlSubject = etlSubject;
		this.etlSubject.setSource(this);
	}

	public void capitalizeContent() {
		System.out.println("capitalizing content from Text file - Start");

		String fileContent = Utility.getStringDataFromFile(new File(capitalizeTaskInputFilePath));
		try{
			PrintWriter outputWriter = new PrintWriter(new File(capitalizeTaskOutputFilePath));
			outputWriter.print(fileContent.toUpperCase());
			outputWriter.close();
		} catch(IOException e){
			e.printStackTrace();
		}
		
		System.out.println("capitalizing content from Text file - End");
	}

	public void countWords() {
		System.out.println("counting unique words from Text file - Start");
		
		String fileContent = Utility.getStringDataFromFile(new File(countWordsTaskInputFilePath));
		String[] array = fileContent.split(" ");
		Map<String, Integer> contentCountMap = getContentCountMap(array);
		String outputData = getContentCountMapDataInString(contentCountMap);
		try{
			PrintWriter outputWriter = new PrintWriter(new File(countWordsTaskOutputFilePath));
			outputWriter.print(outputData);
			outputWriter.close();
		} catch(IOException e){
			e.printStackTrace();
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
