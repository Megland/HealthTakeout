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
import service.FoodOperationImp;

/**
 * Servlet implementation class FoodInsertServlet
 */
@WebServlet("/FoodInsertServlet")
public class FoodInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    FoodOperationImp foodOper = new FoodOperationImp();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String foodno=request.getParameter("foodno");
		String category=request.getParameter("category");
		String foodname=request.getParameter("foodname");
		String Sprice=request.getParameter("price");
		float price=Float.parseFloat(Sprice);
		String Scalories=request.getParameter("calories");
		float calories=Float.parseFloat(Scalories);
		String supplier=request.getParameter("supplier");
		String information=request.getParameter("information");
		
		int i=0 ;
		if(StringUtils.isNullOrEmpty(foodno)) {
			Food s=new Food(0,category,foodname,price,calories,supplier,information);
			i=foodOper.insertFood(s);
		}else {
			Food s=new Food(Integer.parseInt(foodno),category,foodname,price,calories,supplier,information);
			i=foodOper.updateFood(s);
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
