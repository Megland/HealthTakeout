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

import entity.Address;
import service.AddressOperationImp;

/**
 * Servlet implementation class AddressInsertServlet
 */
@WebServlet("/AddressInsertServlet")
public class AddressInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddressInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    AddressOperationImp addressOper = new AddressOperationImp();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String addressno=request.getParameter("addressno");
		String contactperson=request.getParameter("contactperson");
		String tempgender=request.getParameter("gender");
		String gender="0";
		if(tempgender.equals("Ůʿ")) {
			gender="1";
		}
		String phone=request.getParameter("phone");
		String addressdetail=request.getParameter("addressdetail");
		String userno=request.getParameter("userno");
		
		int i=0 ;
		if(StringUtils.isNullOrEmpty(addressno)) {
			Address s=new Address(0,contactperson,gender,phone,addressdetail,Integer.parseInt(userno));
			i=addressOper.insertAddress(s);
		}else {
			Address s=new Address(Integer.parseInt(addressno),contactperson,gender,phone,addressdetail,Integer.parseInt(userno));
			i=addressOper.updateAddress(s);
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
