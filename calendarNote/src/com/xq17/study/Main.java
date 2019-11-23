package com.xq17.study;


import java.util.Calendar;
import javax.swing.*;
import java.awt.*;
import java.io.File;

import com.xq17.study.control.*;
import com.xq17.study.view.*;

public class Main extends JFrame {
	// view layer
	CalendarPanel viewPanel;
	Calendar calendar;
	public Main() {
		this.setTitle("Calendar NotePad");
		this.setSize(600, 400);
		this.setLocation(200, 300);
		Container con=getContentPane();
		/*
		 * View Layer Init
		 */
		calendar = Calendar.getInstance();
		int y = calendar.get(Calendar.YEAR);
		int m=calendar.get(Calendar.MONTH)+1;
		int d=calendar.get(Calendar.DAY_OF_MONTH);
		viewPanel = new CalendarPanel(y, m, d);
		con.add(viewPanel);
		JOptionPane.showMessageDialog(null, "Author:xq17");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// mkdir log directory
		File folder = new File("./log");
		if(!folder.exists()) {
			folder.mkdir();
		}
	}
	public static void main(String[] args) {
		new Main().setVisible(true);
	}
}
