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

import entity.Restaurant;
import service.RestaurantOperationImp;

/**
 * Servlet implementation class RestaurantInsertServlet
 */
@WebServlet("/RestaurantInsertServlet")
public class RestaurantInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestaurantInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    RestaurantOperationImp restaurantOper = new RestaurantOperationImp();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String restaurantid=request.getParameter("restaurantid");
		String restaurantname=request.getParameter("restaurantname");
		String introduction=request.getParameter("introduction");
		String restaurantaddress=request.getParameter("restaurantaddress");
		
		int i=0 ;
		if(StringUtils.isNullOrEmpty(restaurantid)) {
			Restaurant s=new Restaurant(0,restaurantname,introduction,restaurantaddress);
			i=restaurantOper.insertRestaurant(s);
		}else {
			Restaurant s=new Restaurant(Integer.parseInt(restaurantid),restaurantname,introduction,restaurantaddress);
			i=restaurantOper.updateRestaurant(s);
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
