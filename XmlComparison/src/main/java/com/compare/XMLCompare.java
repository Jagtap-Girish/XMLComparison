package com.compare;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.xml.sax.SAXException;

public class XMLCompare {

	public static void main(String[] args) throws SAXException, IOException {
		FileInputStream fileInputStream= new FileInputStream("/Users/gJagtap/OneDrive - Prolifics Corporation Ltd.,/Desktop/part.xml");
		FileInputStream fileInputStream1= new FileInputStream("/Users/gJagtap/OneDrive - Prolifics Corporation Ltd.,/Desktop/customer.xml");
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(fileInputStream));
		BufferedReader bufferedReader1 =new BufferedReader(new InputStreamReader(fileInputStream1));
		XMLUnit.setIgnoreWhitespace(true);
		List<?> differences=comapare(bufferedReader,bufferedReader1);
		printDifference(differences);
	}

	

	private static List<?> comapare(BufferedReader bufferedReader, BufferedReader bufferedReader1) throws SAXException, IOException {
		Diff diferences = new Diff(bufferedReader, bufferedReader1);
		DetailedDiff detailedDiff=new DetailedDiff(diferences);
		return detailedDiff.getAllDifferences();
	}
	private static void printDifference(List<?> differences) {
	int TotalDifferences=differences.size();
	System.out.println("=================");
	System.out.println("Number of differences : "+TotalDifferences);
		System.out.println("=================");
		for (Object difference : differences) {
			System.out.println(difference);
		}
	}

}
