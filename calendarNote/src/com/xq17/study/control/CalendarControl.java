package com.xq17.study.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//import com.xq17.study.view.CalendarPanel.*;
//import com.xq17.study.view.CalendarPanel;
public class CalendarControl implements ActionListener, MouseListener {
	CalendarPanel cP;
	public CalendarControl(CalendarPanel calendarPanel) {
		// TODO Auto-generated constructor stub
		/*
		 *  设置View层的操作图像
		 */
		this.cP = calendarPanel;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==cP.month2){

            if(cP.month>=12){
                cP.month=1;
                cP.year=cP.year+1;
                cP.calendar.setMonth(cP.month);
                cP.calendar.setYear(cP.year);
                String day[]=cP.calendar.getCalendar();
                for(int i=0;i<42;i++){
                    cP.labelday[i].setText(day[i]);
                }
            }
            else{
                cP.month=cP.month+1;
                cP.calendar.setMonth(cP.month);
                cP.calendar.setYear(cP.year);
                String day[]=cP.calendar.getCalendar();
                for(int i=0;i<42;i++){
                    cP.labelday[i].setText(day[i]);
                }
            }
            cP.showMessage.setText(cP.calendar.getYear()+"年"+cP.calendar.getMonth()+"月"+cP.day+"日");
            cP.showMonth.setText(""+cP.calendar.getMonth());
			/*nn.setshowtime(year,month);*/


        }

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
}
