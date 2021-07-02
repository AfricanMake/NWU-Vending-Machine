package com.nwu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

	//instance Variables
	String loggerPath;
	File loggerFile;
	PrintWriter loggerWriter;
	SimpleDateFormat dateFormat;
	String format;
 
	//initialized non-parametrized constructor
	public Logger() {
		this.loggerPath = "Log.txt";
		this.loggerFile = new File(loggerPath);
		this.format = "MM/dd/yyyy hh:mm:ss a";
		this.dateFormat = new SimpleDateFormat(format);
		try {
			this.loggerWriter = new PrintWriter(new FileOutputStream(loggerFile),true);
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found!");
		}
	}

	//method
	public void log(String activity, String currentAmount, String remainingAmount) {
		String dateTime = dateFormat.format(new Date());
		loggerWriter.println(dateTime + " " + activity + " R" + currentAmount + " R" + remainingAmount);
	}
}