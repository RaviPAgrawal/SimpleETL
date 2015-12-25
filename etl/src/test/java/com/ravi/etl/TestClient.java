package com.ravi.etl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TestClient {

	@Test
	public void capitalizeContent_TextFile_Test() throws IOException {

		//Check if parent directory exists, if yes clean it, if no create it
		File capitalizeTaskDir = new File(TextFile.capitalizeTaskDirPath);
		capitalizeTaskDir.mkdir();
		if(capitalizeTaskDir.isDirectory()){
			FileUtils.cleanDirectory(capitalizeTaskDir);	
		}

		//create input folder under parent directory
		File inputDir = new File(TextFile.capitalizeTaskDirPath + File.separator + TextFile.inputFolder);
		inputDir.mkdir();

		//Add file in the input folder
		File inputFile = new File(inputDir, TextFile.capitalizeFileName);
		inputFile.createNewFile();

		//Write Test data in input file
		String testData = "test data to capitalize";
		Utility.writeContentToFile(testData, inputFile);

		//create output folder under parent directory
		File outputDir = new File(TextFile.capitalizeTaskDirPath + File.separator + TextFile.outputFolder);
		outputDir.mkdir();

		//Add file in the output folder
		File outputFile = new File(outputDir, TextFile.capitalizeFileName);
		outputFile.createNewFile();

		//Capitalize content while copying from input file to output file
		EtlSubject etlSubject = new EtlSubject();
		new TextFile(etlSubject);
		etlSubject.capitalizeContent();

		//Read data from output file
		String outputData = Utility.getStringDataFromFile(new File(TextFile.capitalizeTaskOutputFilePath));

		//Check if operation is successful
		assertTrue(testData.toUpperCase().equals(outputData));
	}

	@Test
	public void countWords_TextFile_Test() throws IOException{
		
		//Check if parent directory exists, if yes clean it, if no create it
		File countWordsTaskDir = new File(TextFile.countWordsTaskDirPath);
		countWordsTaskDir.mkdir();
		if(countWordsTaskDir.isDirectory()){
			FileUtils.cleanDirectory(countWordsTaskDir);	
		}

		//create input folder under parent directory
		File inputDir = new File(TextFile.countWordsTaskDirPath + File.separator + TextFile.inputFolder);
		inputDir.mkdir();

		//Add file in the input folder
		File inputFile = new File(inputDir, TextFile.countContentFileName);
		inputFile.createNewFile();

		//Write Test data in input file
		String testData = "test data to count test test data";
		Utility.writeContentToFile(testData, inputFile);

		//create output folder under parent directory
		File outputDir = new File(TextFile.countWordsTaskDirPath + File.separator + TextFile.outputFolder);
		outputDir.mkdir();

		//Add file in the output folder
		File outputFile = new File(outputDir, TextFile.countContentFileName);
		outputFile.createNewFile();

		//Count content from input file and write result to output file
		EtlSubject etlSubject = new EtlSubject();
		new TextFile(etlSubject);
		etlSubject.countWords();

		//Read data from output file
		String outputData = Utility.getStringDataFromFile(new File(TextFile.countWordsTaskOutputFilePath));

		//Check if operation is successful
		assertTrue(outputData.equals("test-3 data-2 count-1 to-1 "));
	}
	
	@Test
	public void capitalizeContent_SqlTable_Test(){
		EtlSubject etlSubject = new EtlSubject();
		new SqlTable(etlSubject);
		etlSubject.capitalizeContent();
	}

	@Test
	public void countWords_SqlTable_Test(){
		EtlSubject etlSubject = new EtlSubject();
		new SqlTable(etlSubject);
		etlSubject.countWords();
	}
}
