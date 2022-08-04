package com.compare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.xml.sax.SAXException;

public class XMLCompare {
	static ArrayList<String> output=new ArrayList<String>();
	public static void main(String[] args) throws SAXException, IOException {
		File dir1 = new File("/Users/gJagtap/OneDrive - Prolifics Corporation Ltd.,/Desktop/New Folder");
		File dir2 = new File("/Users/gJagtap/OneDrive - Prolifics Corporation Ltd.,/Desktop/New Folder 1");
		File[] files=dir1.listFiles();
		File[] files1=dir2.listFiles();
		ArrayList<File> f=new ArrayList<File>();
		ArrayList<File> f1=new ArrayList<File>();
		for (int i = 0; i < files.length; i++) {
			f.add(files[i]);
		}
		for (int i = 0; i < files1.length; i++) {
			f1.add(files1[i]);
		}
		boolean flag=true;
		for (Iterator<File> iterator = f1.iterator(); iterator.hasNext();) {
			File file = iterator.next();
			for (File file1 : f) {
				if (file1.getName().equals(file.getName()) && file1.length()==file.length())
				{
					flag=false;
					output.add(file.getName());
					String out=output.toString();
					printOutput("commom file "+out);
					System.out.println("These files are same in folders both ====> "+file.getName());
				}
				if (FilenameUtils.getExtension(file.getName()).equals("xml") && FilenameUtils.getExtension(file1.getName()).equals("xml"))
				{
					flag =false;
					FileInputStream fileInputStream= new
							FileInputStream(file);
					FileInputStream fileInputStream1= new
							FileInputStream(file1);
					BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(fileInputStream));
					BufferedReader bufferedReader1 =new BufferedReader(new InputStreamReader(fileInputStream1));
					XMLUnit.setIgnoreWhitespace(true);
					List<?> differences=comapare(bufferedReader,bufferedReader1);
					printDifference(differences);
				}
			}
		}
		if(flag) {
			System.out.println("All files are different");
		}
	}
	private static List<?> comapare(BufferedReader bufferedReader, BufferedReader bufferedReader1) throws SAXException, IOException {
		Diff diferences = new Diff(bufferedReader, bufferedReader1);
		DetailedDiff detailedDiff=new DetailedDiff(diferences);
		return detailedDiff.getAllDifferences();
	}
	private static void printDifference(List<?> differences) throws IOException {
		int TotalDifferences=differences.size();
		System.out.println("=================");
		System.out.println("Number of differences : "+TotalDifferences);
		System.out.println("=================");
		for (Object difference : differences) {
			System.out.println(difference);
			String out=difference.toString();
			printOutput(out);
		}
	}
	private static void printOutput(String difference) throws IOException {
		FileOutputStream output=new FileOutputStream("/Users/gJagtap/OneDrive - Prolifics Corporation Ltd.,/Desktop/Output.txt");
		byte[] getBytes=difference.getBytes();
		output.write(getBytes);
	}
}
