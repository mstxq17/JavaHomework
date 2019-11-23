package com.xq17.study.view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.text.JTextComponent;

//import com.xq17.study.view.CalendarPanel;

public class NotePad extends JPanel implements ActionListener{
	JTextArea text;
	JButton Save,Delete,Read;
	CalendarPanel calendarPanel;
	public NotePad(CalendarPanel calendar) {
		// TODO Auto-generated constructor stub
		text = calendar.text;
		Save = calendar.Save;
		Delete = calendar.Delete;
		Read = calendar.Read;
		this.calendarPanel = calendar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		System.out.print(e.getSource());
		if(e.getSource()==Save){
            File file=new File("./log"+File.separator+File.separator+calendarPanel.showMessage.getText()+".txt");
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
            String h=calendarPanel.showMessage.getText();
            String m="删除"+h+"的日志？";
            int ok=JOptionPane.showConfirmDialog(getParent(),m,"询问",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(ok==JOptionPane.YES_OPTION){
                File file=new File("./log"+File.separator+File.separator+calendarPanel.showMessage.getText()+".txt");
                file.delete();
                text.setText("");
                JOptionPane.showMessageDialog(getParent(),"日志删除成功");
            }
        }
	}

}
