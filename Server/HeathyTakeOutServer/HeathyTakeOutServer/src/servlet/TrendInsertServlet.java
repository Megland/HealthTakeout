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

import entity.Trend;
import service.TrendOperationImp;

/**
 * Servlet implementation class TrendInsertServlet
 */
@WebServlet("/TrendInsertServlet")
public class TrendInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrendInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    TrendOperationImp trendOper = new TrendOperationImp();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String trendno=request.getParameter("trendno");
		String Suserno=request.getParameter("userno");
		int userno=Integer.parseInt(Suserno);
		String username=request.getParameter("username");
		String trendtitle=request.getParameter("trendtitle");
		String trendcontent=request.getParameter("trendcontent");
		String releasetime=request.getParameter("releasetime");
			
		int i=0 ;
		if(StringUtils.isNullOrEmpty(trendno)) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			releasetime=df.format(new Date()); // new Date()为获取当前系统时间
			Trend s=new Trend(0,userno,username,trendtitle,trendcontent,releasetime);
			i=trendOper.insertTrend(s);
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
