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

import entity.Food;
import entity.Order;
import service.OrderOperationImp;

import java.util.Date;
import java.text.SimpleDateFormat;


/**
 * Servlet implementation class OrderInsertServlet
 */
@WebServlet("/OrderInsertServlet")
public class OrderInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    OrderOperationImp orderOper = new OrderOperationImp();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String orderid=request.getParameter("orderid");
		String Suserno=request.getParameter("userno");
		int userno=Integer.parseInt(Suserno);
		
		String Srestaurantid=request.getParameter("restaurantid");
		int restaurantid=Integer.parseInt(Srestaurantid);
		
		String username=request.getParameter("username");
		String restaurantname=request.getParameter("restaurantname");
		String orderdetails=request.getParameter("orderdetails");
		
		String Stotalprice=request.getParameter("totalprice");
		float totalprice=Float.parseFloat(Stotalprice);
		
		String starttime=request.getParameter("starttime");
		
		String orderstatus=request.getParameter("orderstatus");
		
		String Saddressno=request.getParameter("addressno");
		int addressno=Integer.parseInt(Saddressno);
		
		String orderaddress=request.getParameter("orderaddress");
		

		
		int i=0 ;
		if(StringUtils.isNullOrEmpty(orderid)) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			starttime=df.format(new Date()); // new Date()为获取当前系统时间
			Order s=new Order(0,userno,restaurantid,username,restaurantname,orderdetails,totalprice,starttime,orderstatus,addressno,orderaddress);
			i=orderOper.insertOrder(s);
		}else {
			Order s=new Order(Integer.parseInt(orderid),userno,restaurantid,username,restaurantname,orderdetails,totalprice,starttime,orderstatus,addressno,orderaddress);
			i=orderOper.updateOrders(s);
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
