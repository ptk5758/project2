package EventController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EventDAO;
import model.AttendBean;

@WebServlet("/attend.view")
public class attend_view_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public attend_view_Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int newYear = 2020;
		int newMonth = 1;
		int newFirst = 1;
		int newLast = 1;
		int newFirstDay = 0;
		int week = 5;
		int j = 0;
		String attendDay[] = new String[42]; //그날짜를 담는 배열
		int attendCount[] = new int[42];  //그날짜에 출석한 인원 담는 배열 
		int setday = 01;
		int lastday = 7;
		Calendar cal = Calendar.getInstance();
		newFirst = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DATE, newFirst);
		newLast = cal.getActualMaximum(Calendar.DAY_OF_MONTH);//312930 그달 일수
		newMonth = cal.get(Calendar.MONTH)+1;//달
		newFirstDay = cal.get(Calendar.DAY_OF_WEEK);//1일의 요일
		newYear = cal.get(Calendar.YEAR);
		
		ArrayList<AttendBean> v = new ArrayList<AttendBean>();
		ArrayList<AttendBean> today_v = new ArrayList<AttendBean>();
		EventDAO dao = new EventDAO();
		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		today_v = dao.todayAttend(sdf.format(today));
		//달력구문시작
		for(int i=0; i<=week; i++){
			while(newFirstDay != 1){
				attendDay[j] = newMonth-1+"월";
				attendCount[j] = 0;
				AttendBean bean = new AttendBean();
				bean.setSigndate_option("backMonth");
				v.add(bean);
				newFirstDay--;
				lastday--;
				j++;
			}
			while(lastday != 0){
				if(setday > newLast){
					attendDay[j] =  newMonth+1+"월";
					attendCount[j] = 0;
					AttendBean bean = new AttendBean();
					bean.setSigndate_option("nextMonth");
					v.add(bean);
					setday++;
					lastday--;
					j++;
				}else{
					attendDay[j] = String.format("%02d", setday);
					String calendarDay = newYear+"-"+String.format("%02d", newMonth)+"-"+String.format("%02d", setday);
					attendCount[j] = dao.showAttend(calendarDay);
					AttendBean bean = new AttendBean();
					bean.setSigndate_option("normal");
					v.add(bean);
					setday++;
					lastday--;
					j++;
				}
			}
			lastday = 7;
		}
		
		request.setAttribute("today_v", today_v);
		request.setAttribute("attendCount", attendCount);
		request.setAttribute("v", v);
		request.setAttribute("attendDay", attendDay);
		request.setAttribute("newMonth", newMonth);
		request.setAttribute("newYear", newYear);
		
		RequestDispatcher dis = request.getRequestDispatcher("/event/attend2.jsp");
		dis.forward(request, response);
	}

}
