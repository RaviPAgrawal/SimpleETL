package com.ravi.etl;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class TestClient {

	@Test
	public void capitalizeContent_TextFile_Test() throws IOException {

		cleanDirectory(TextFile.capitalizeTaskDirPath);

		//create input folder under parent directory
		File inputDir = new File(TextFile.capitalizeTaskDirPath + File.separator + TextFile.inputFolder);
		inputDir.mkdir();

		createInputFiles(inputDir);

		//create output folder under parent directory
		File outputDir = new File(TextFile.capitalizeTaskDirPath + File.separator + TextFile.outputFolder);
		outputDir.mkdir();

		//Capitalize content while copying from input file to output file
		EtlSubject etlSubject = new EtlSubject();
		new TextFile(etlSubject);
		etlSubject.capitalizeContent(inputDir, outputDir);
		
		assertCapitalizeContent(inputDir, outputDir);
	}

	@Test
	public void countWords_TextFile_Test() throws IOException{

		cleanDirectory(TextFile.countWordsTaskDirPath);

		//create input folder under parent directory
		File inputDir = new File(TextFile.countWordsTaskDirPath + File.separator + TextFile.inputFolder);
		inputDir.mkdir();

		createInputFiles(inputDir);

		//create output folder under parent directory
		File outputDir = new File(TextFile.countWordsTaskDirPath + File.separator + TextFile.outputFolder);
		outputDir.mkdir();

		//Count content from input file and write result to output file
		EtlSubject etlSubject = new EtlSubject();
		new TextFile(etlSubject);
		etlSubject.countWords(inputDir, outputDir);

		assertCountWords(outputDir);
	}

	@Test
	public void capitalizeContent_SqlTable_Test(){
		EtlSubject etlSubject = new EtlSubject();
		new SqlTable(etlSubject);
		etlSubject.capitalizeContent(null, null);
	}

	@Test
	public void countWords_SqlTable_Test(){
		EtlSubject etlSubject = new EtlSubject();
		new SqlTable(etlSubject);
		etlSubject.countWords(null, null);
	}
	
	private void cleanDirectory(String dirPath) throws IOException {
		//Check if parent directory exists, if yes clean it, if no create it
		File taskDir = new File(dirPath);
		taskDir.mkdir();
		if(taskDir.isDirectory()){
			FileUtils.cleanDirectory(taskDir);	
		}
	}
	
	private void createInputFiles(File inputDir) throws IOException {
		//Add files in the input folder
		File inputFile1 = new File(inputDir, "file1.txt");
		inputFile1.createNewFile();

		File inputFile2 = new File(inputDir, "file2.txt");
		inputFile2.createNewFile();

		File inputFile3 = new File(inputDir, "file3.txt");
		inputFile3.createNewFile();
		
		//Write Test data in input file
		String testData1 = "test data test file1 data";
		Utility.writeContentToFile(testData1, inputFile1);

		String testData2 = "test data test file2 data";
		Utility.writeContentToFile(testData2, inputFile2);

		String testData3 = "test data test file3 data";
		Utility.writeContentToFile(testData3, inputFile3);
	}

	private void assertCapitalizeContent(File inputDir, File outputDir) {
		//Creating map of input file name and input file content
		Map<String, String> inputFileNameToFileContentMap = new HashMap<>(); 
		for (File inputFile : inputDir.listFiles()) {
			String fileContent = Utility.getStringDataFromFile(inputFile);
			inputFileNameToFileContentMap.put(inputFile.getName(), fileContent);
		}

		//Creating map of output file name and output file content
		Map<String, String> outputFileNameToFileContentMap = new HashMap<>(); 
		for (File outputFile : outputDir.listFiles()) {
			String fileContent = Utility.getStringDataFromFile(outputFile);
			outputFileNameToFileContentMap.put(outputFile.getName(), fileContent);
		}
		
		//iterate over input file map and output file map to check if operation is successful
		for (Map.Entry<String, String> entry : inputFileNameToFileContentMap.entrySet()) {
			String inputFileName = entry.getKey();
			String inputFileContent = entry.getValue();
			assertTrue(outputFileNameToFileContentMap.get(inputFileName).equals(inputFileContent.toUpperCase()));
		}
	}

	private void assertCountWords(File outputDir) {
		//Creating map of output file name and output file content
		Map<String, String> outputFileNameToFileContentMap = new HashMap<>(); 
		for (File outputFile : outputDir.listFiles()) {
			String fileContent = Utility.getStringDataFromFile(outputFile);
			outputFileNameToFileContentMap.put(outputFile.getName(), fileContent);
		}

		//Check if count operation is successful
		assertTrue(outputFileNameToFileContentMap.get("file1.txt").equals("test-2 data-2 file1-1 "));
		assertTrue(outputFileNameToFileContentMap.get("file2.txt").equals("test-2 data-2 file2-1 "));
		assertTrue(outputFileNameToFileContentMap.get("file3.txt").equals("test-2 data-2 file3-1 "));
	}

}
