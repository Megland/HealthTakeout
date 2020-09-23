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

import entity.Message;
import service.MessageOperationImp;

/**
 * Servlet implementation class MessageInsertServlet
 */
@WebServlet("/MessageInsertServlet")
public class MessageInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    MessageOperationImp messageOper = new MessageOperationImp();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String messageid=request.getParameter("messageid");
		String senderusernostr=request.getParameter("senderuserno");
		int senderuserno=Integer.parseInt(senderusernostr);
		
		String sendername=request.getParameter("sendername");
		
		String receiverusernostr=request.getParameter("receiveruserno");
		int receiveruserno=Integer.parseInt(receiverusernostr);
	
		String messagecontent=request.getParameter("messagecontent");
		String sendtime=request.getParameter("sendtime");
		
		int i=0 ;
		if(StringUtils.isNullOrEmpty(messageid)) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			sendtime=df.format(new Date()); // new Date()为获取当前系统时间
			Message s=new Message(0,senderuserno,sendername,receiveruserno,messagecontent,sendtime);
			i=messageOper.insertMessage(s);
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
