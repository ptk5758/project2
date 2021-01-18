package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test.do")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TestServlet() {
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
		boolean email = false;
		String rice[] = request.getParameterValues("rice");
		for(String menu : rice) {
			System.out.println(menu);
			if(menu.equals("돼지국밥")) {
				email = true;
			}
			
		}
		System.out.println(rice.length);
		System.out.println(email);

		RequestDispatcher dis = request.getRequestDispatcher("test.jsp");
		dis.forward(request, response);
	}

}
