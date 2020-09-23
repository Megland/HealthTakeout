package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.StringUtils;

import entity.Shopcomment;
import service.ShopCommentOperationImp;

/**
 * Servlet implementation class ShopCommentInsertServlet
 */
@WebServlet("/ShopCommentInsertServlet")
public class ShopCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopCommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    ShopCommentOperationImp ShopCommentOper = new ShopCommentOperationImp();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String shopcommentno=request.getParameter("shopcommentno");
		String Srestaurantid=request.getParameter("restaurantid");
		int restaurantid=Integer.parseInt(Srestaurantid);
		
		String content=request.getParameter("content");
		String commenttime=request.getParameter("commenttime");
		
		String Suserno=request.getParameter("userno");
		int userno=Integer.parseInt(Suserno);
		
		String username=request.getParameter("username");
		
		int i=0 ;
		if(StringUtils.isNullOrEmpty(shopcommentno)) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			commenttime=df.format(new Date()); // new Date()为获取当前系统时间
			Shopcomment s=new Shopcomment(0,restaurantid,content,commenttime,userno,username);
			i=ShopCommentOper.insertShopcomment(s);
		}else {
			Shopcomment s=new Shopcomment(Integer.parseInt(shopcommentno),restaurantid,content,commenttime,userno,username);
			i=ShopCommentOper.updateShopcomment(s);
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
