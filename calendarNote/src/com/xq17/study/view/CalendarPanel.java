package com.xq17.study.view;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Month;
import java.time.Year;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.xq17.study.control.*;

public class CalendarPanel extends JPanel implements ActionListener, MouseListener {
	// BorderLayout South
	JPanel header;
	JButton year1, year2, month1, month2;
	JTextField showYear = null;
	JTextField showMonth = null;
	// Calendar logic
	Calendardata calendar;
	CalendarControl CAL;
	// BorderLayout center
	JPanel leftPanel, rightPanel;
	public JLabel showMessage = new JLabel("", JLabel.CENTER);
	// concat leftPanel and rightPanel
	JSplitPane split;
	NotePad notePad;
	JTextArea text;
	JLabel title[];
	JLabel labelday[];
	int year, month, day;
	int currentDay;
	String week[] = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
	// BorderLayout North
	JPanel footer;
	JButton Save, Delete, Read;
	// Event Handle Control
	ActionListener leftAct, noteAct;
	Calendar Date;

	public CalendarPanel(int y, int m, int d) {
		/*
		 * key Date Calendar
		 */
		calendar = new Calendardata();
		/*
		 * North Init
		 */
		header = new JPanel();
		showYear = new JTextField(4);
		// 文本居中
		showYear.setHorizontalAlignment(JTextField.CENTER);
		showYear.setFont(new Font("TimesRoman", Font.BOLD, 14));
		year1 = new JButton("上年");
		year2 = new JButton("下年");
		header.setLayout(new FlowLayout());
		showMonth = new JTextField(4);
		showMonth.setHorizontalAlignment(JTextField.CENTER);
		showMonth.setFont(new Font("TimesRoman", Font.BOLD, 14));
		month1 = new JButton("上月");
		month2 = new JButton("下月");
		header.add(year1);
		header.add(showYear);
		header.add(year2);
		header.add(month1);
		header.add(showMonth);
		header.add(month2);
		this.setLayout(new BorderLayout());
		this.add(header, BorderLayout.NORTH);
		/*
		 * 
		 * 添加事件处理
		 * 
		 */
		// leftAct = new CalendarControl(this);
		leftAct = this;
		year1.addActionListener(leftAct);
		year2.addActionListener(leftAct);
		showYear.addActionListener(leftAct);
		month1.addActionListener(leftAct);
		month2.addActionListener(leftAct);
		showMonth.addActionListener(leftAct);
		/*
		 * Test JLabel tJLabel = new JLabel("header"); header.add(tJLabel);
		 * this.add(header, BorderLayout.NORTH);
		 */

		/*
		 * Center Init
		 */
		leftPanel = new JPanel();
		rightPanel = new JPanel();

		leftPanel.setLayout(new GridLayout(7, 7));
		this.year = y;
		this.month = m;
		this.day = d;
		title = new JLabel[7];
		labelday = new JLabel[42];
		for (int j = 0; j < 7; j++) {
			title[j] = new JLabel(week[j], JLabel.CENTER);
			leftPanel.add(title[j]);
			// title[j].setText(week[j]);
			title[j].setBorder(BorderFactory.createRaisedBevelBorder());
			title[j].setFont(new Font("TimesRoman", Font.BOLD, 14));
			title[j].setForeground(Color.blue);
			if (j == 0) {
				title[j].setForeground(Color.RED);/* 设置周六周日font为红色 */
			}
			if (j == 6) {
				title[j].setForeground(Color.RED);
			}
		}
		/*
		 * set date tag
		 */
		for (int i = 0; i < 42; i++) {
			labelday[i] = new JLabel("", JLabel.CENTER);
			labelday[i].setBorder(BorderFactory.createLineBorder(Color.blue));
			labelday[i].setForeground(Color.darkGray);
			leftPanel.add(labelday[i]);
			if (i % 7 == 6) {
				labelday[i].setForeground(Color.red);
			}
			if (i % 7 == 0) {
				labelday[i].setForeground(Color.red);
			}
            final int j=i;
            /*添加鼠标监听器内部类,适配器*/
            labelday[j].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    // TODO 自动生成的方法存根
                    /*处理鼠标释放*/
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    // TODO 自动生成的方法存根

                    /*处理鼠标按下*/
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // TODO 自动生成的方法存根
                    /*处理鼠标离开*/
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // TODO 自动生成的方法存根
                    /*处理鼠标移入*/

                }

                @Override
                public void mouseClicked(MouseEvent e){
                    // TODO 自动生成的方法存根
                    /*处理鼠标点击*/
                    /*获取日期   天*/

                    String Day=labelday[j].getText();
                    try {
                        if(!Day.equals("")){
                            /*设置选中日期标签fontcolor*/

                            /*初始化上次点击的标签*/
                            for(int i=0;i<42;i++){
                                if(i%7==6){
                                    labelday[i].setForeground(Color.red);
                                    labelday[i].setFont(new Font("TimesRoman", Font.BOLD, 13));
                                }
                                if(i%7==0){
                                    labelday[i].setForeground(Color.red);
                                    labelday[i].setFont(new Font("TimesRoman", Font.BOLD, 13));
                                }
                                if(!(i%7==6) && !(i%7==0)){
                                    labelday[i].setForeground(Color.darkGray);
                                    labelday[i].setFont(new Font("TimesRoman", Font.BOLD, 13));
                                }
                            }

                            labelday[j].setForeground(Color.green);
                            labelday[j].setFont(new Font("TimesRoman", Font.BOLD, 16));
                            showMessage.setText(calendar.getYear()+"年"+calendar.getMonth()+"月"+Day+"日");
                            showYear.setText(""+calendar.getYear());
                            showMonth.setText(""+calendar.getMonth());
                            File file=new File("./log"+File.separator+File.separator+showMessage.getText()+".txt");
                            if(file.exists()){
                                String h=showMessage.getText();
                                String m=h+"有日志记录，是否查看？";
                                int ok=JOptionPane.showConfirmDialog(getParent(),m,"询问",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                                if(ok==JOptionPane.YES_OPTION){
                                    try {
                                        FileReader in=new FileReader(file);
                                        BufferedReader bufr=new BufferedReader(in);
                                        char a[]=new char[1024];
                                        int len=bufr.read(a);
                                        text.setText(new String(a,0,len));

                                        /*  System.out.println(new String(a,0,len));*/

                                        bufr.close();
                                        in.close();

                                    } catch (IOException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                    }
                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(getParent(), showMessage.getText()+"没有日志记录，清编辑并保存");
                                text.setText("");
                            }
                        }
                    } catch (NullPointerException e2) {
                        // TODO: handle exception
                            /*JOptionPane.showMessageDialog(null, "此处为空日期");*/
                    }
                }
            });
		}
		showMessage.setText("日历:" + calendar.getYear() + "年" + calendar.getMonth() + "月" + calendar.getDay() + "日");
		showMessage.setForeground(Color.black);
		showMessage.setFont(new Font("TimesRoman", Font.BOLD, 18));
		text = new JTextArea();
		text.setFont(new Font("楷体", Font.PLAIN, 12));
		rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		rightPanel.add(showMessage, BorderLayout.NORTH);
		rightPanel.add(new JScrollPane(text), BorderLayout.CENTER);
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
		// set split percentage
		split.setDividerLocation(300);
		this.add(split, BorderLayout.CENTER);

		/*
		 * 添加事件处理
		 */

		/*
		 * Footer Init
		 */
		noteAct = new NotePad(this);
		Save = new JButton("保存日志");
		Delete = new JButton();
		Delete.setText("删除日志");
		Delete.setForeground(Color.red);
		Read = new JButton("读取日志");
		
		Read.setForeground(Color.gray);
		/*
		 * 这里需要后期修改
		 */
		Read.addActionListener(this);
		Save.addActionListener(this);
		Delete.addActionListener(this);
		footer = new JPanel();
		footer.setLayout(new FlowLayout());
		footer.add(Save);
		footer.add(Delete);
		footer.add(Read);
		this.add(footer, BorderLayout.SOUTH);

		/*
		 * 添加事件处理
		 */

		/*
		 * program Init
		 */
		year = calendar.getYear();
		month = calendar.getMonth();
		day = calendar.getDay();
		calendar.setYear(year);
		calendar.setMonth(month);
		String day[] = calendar.getCalendar();

		/* 设置日期标签名 */
		for (int i = 0; i < 42; i++) {
			labelday[i].setText(day[i]);
		}
		/* 高亮当前日期 */
		// currentDay = calendar.getDay() + calendar.getDis();
		// labelday[currentDay].setFont(new Font("TimesRoman", Font.BOLD, 18));
		// labelday[currentDay].setForeground(Color.YELLOW);
		showYear.setText("" + year);
		showMonth.setText("" + month);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == month2) {

			if (month >= 12) {
				month = 1;
				year = year + 1;
				calendar.setMonth(month);
				calendar.setYear(year);
				String day[] = calendar.getCalendar();
				for (int i = 0; i < 42; i++) {
					labelday[i].setText(day[i]);
				}
			} else {
				month = month + 1;
				calendar.setMonth(month);
				calendar.setYear(year);
				String day[] = calendar.getCalendar();
				for (int i = 0; i < 42; i++) {
					labelday[i].setText(day[i]);
				}
			}
			showMessage.setText(calendar.getYear() + "年" + calendar.getMonth() + "月" + calendar.getDay() + "日");
			showYear.setText("" + calendar.getYear());
			showMonth.setText("" + calendar.getMonth());
			/* nn.setshowtime(year,month); */
		} else if (e.getSource() == month1) {
			if (month <= 1) {
				month = 12;
				year = year - 1;
				calendar.setYear(year);
				calendar.setMonth(month);
				String day[] = calendar.getCalendar();
				for (int i = 0; i < 42; i++) {
					labelday[i].setText(day[i]);
				}
			} else {
				month = month - 1;
				calendar.setMonth(month);
				calendar.setYear(year);
				String day[] = calendar.getCalendar();
				for (int i = 0; i < 42; i++) {
					// labelday[i].setText(day[i]);
					System.out.println(day[i]);
				}
			}
			showMessage.setText(calendar.getYear() + "年" + calendar.getMonth() + "月" + calendar.getDay() + "日");
			/* nn.setshowtime(year,month); */
			showYear.setText("" + calendar.getYear());
			showMonth.setText("" + calendar.getMonth());
		}

		if (e.getSource() == year1) {
			year = year - 1;
			calendar.setMonth(month);
			calendar.setYear(year);
			String day[] = calendar.getCalendar();
			for (int i = 0; i < 42; i++) {
				labelday[i].setText(day[i]);
			}
			showMessage.setText(calendar.getYear() + "年" + calendar.getMonth() + "月" + calendar.getDay() + "日");
			showYear.setText("" + calendar.getYear());
			showMonth.setText("" + calendar.getMonth());
			/* nn.setshowtime(year,month); */
		} else if (e.getSource() == year2) {
			year = year + 1;
			calendar.setMonth(month);
			calendar.setYear(year);
			String day[] = calendar.getCalendar();
			for (int i = 0; i < 42; i++) {
				labelday[i].setText(day[i]);
			}
			showMessage.setText(calendar.getYear() + "年" + calendar.getMonth() + "月" + calendar.getDay() + "日");
			showYear.setText("" + calendar.getYear());
			showMonth.setText("" + calendar.getMonth());
		}
		// 解决修改延时的问题
		if(e.getSource()==Save){
            File file=new File("./log"+File.separator+File.separator+showMessage.getText()+".txt");
            System.out.println(text.getText());
            if(!text.getText().equals("")){
                try {
                    file.createNewFile();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    FileWriter out=new FileWriter(file);
                    BufferedWriter bw=new BufferedWriter(out);
                    bw.write(text.getText());
                    bw.flush();
                    bw.close();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(getParent(),"保存成功");
            }
            else{
                JOptionPane.showMessageDialog(getParent(),"当前并未编辑输入，不能保存空文件，保存失败");
            }

        }
        else if(e.getSource()==Delete){
            String h=showMessage.getText();
            String m="删除"+h+"的日志？";
            int ok=JOptionPane.showConfirmDialog(getParent(),m,"询问",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(ok==JOptionPane.YES_OPTION){
                File file=new File("./log"+File.separator+File.separator+showMessage.getText()+".txt");
                file.delete();
                text.setText("");
                JOptionPane.showMessageDialog(getParent(),"日志删除成功");
            }
        }else if(e.getSource()==Read) {
        	String h=showMessage.getText();
        	File file=new File("./log"+File.separator+File.separator+showMessage.getText()+".txt");
        	if(!file.exists())
        	{
        		JOptionPane.showMessageDialog(getParent(), showMessage.getText()+"没有记录");
        	}
            try {
                FileReader in=new FileReader(file);
                BufferedReader bufr=new BufferedReader(in);
                char a[]=new char[1024];
                int len=bufr.read(a);
                text.setText(new String(a,0,len));

                /*  System.out.println(new String(a,0,len));*/

                bufr.close();
                in.close();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
		this.updateUI();
	}
}
