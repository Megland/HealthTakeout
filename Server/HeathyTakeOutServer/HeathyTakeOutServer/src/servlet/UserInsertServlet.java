package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.StringUtils;

import entity.User;
import service.UserOperationImp;

/**
 * Servlet implementation class UserInsertServlet
 */
@WebServlet("/UserInsertServlet")
public class UserInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    UserOperationImp userOper = new UserOperationImp();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String userno=request.getParameter("userno");
		String username=request.getParameter("username");
		String password=request.getParameter("password");;
		String tempgender=request.getParameter("gender");
		String gender="0";
		if(tempgender.equals("Ů")) {
			gender="1";
		}
		String Sage=request.getParameter("age");
		int age=Integer.parseInt(Sage);
		String Sheight=request.getParameter("height");
		float height=Float.parseFloat(Sheight);
		String Sweight=request.getParameter("weight");
		float weight=Float.parseFloat(Sweight);
		String Stargetweight=request.getParameter("targetweight");
		float targetweight=Float.parseFloat(Stargetweight);
		String preference=request.getParameter("preference");
		
		int i=0;
		if(StringUtils.isNullOrEmpty(userno)) {
			User s=new User(0,username,password,gender,age,height,weight,preference,targetweight);
			i=userOper.insertUser(s);
		}else {
			User s=new User(Integer.parseInt(userno),username,password,gender,age,height,weight,preference,targetweight);
			i=userOper.updateUser(s);
		}
		
		JSONObject json = new JSONObject();
		json.put("code", i);
		PrintWriter pw=response.getWriter();
		pw.print(json);
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
