package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/Test5Servlet")
public class Test5Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Test5Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		JSONObject jsonObj = new JSONObject();
		JSONArray json_Arr = new JSONArray();
		
		jsonObj.put("name", name);
		
		JSONObject info = new JSONObject();
		info.put("id", "ptk5758");
		info.put("password", "ptk0941");
		info.put("age", "22");
		info.put("tel", tel);
		json_Arr.add(info);
		
		jsonObj.put("userinfo", json_Arr);
		jsonObj.put("check", true);
		
		/*
		 * System.out.println("jsonobj :"+jsonObj.toString());
		 * System.out.println("name :"+jsonObj.get("name").toString());
		 * System.out.println("userinfo :"+jsonObj.get("userinfo").toString());
		 */
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonObj.toString());
		
	}

}
