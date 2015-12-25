package com.ravi.etl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Utility {

	public static String getStringDataFromFile(File file){
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
			String inputData;
			StringBuilder outputBuilder = new StringBuilder();
			while((inputData = bufferedReader.readLine()) != null){
				outputBuilder.append(inputData);
			}
			return outputBuilder.toString();
		} catch(IOException e){
			e.printStackTrace();
			return "Error while reading from file";
		}
	}
	
	public static void writeContentToFile(String inputData, File file){
		try{
			PrintWriter outputWriter = new PrintWriter(file);
			outputWriter.print(inputData);
			outputWriter.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
}
