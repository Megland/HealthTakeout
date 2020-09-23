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

import entity.Feedback;
import service.FeedbackOperationImp;

/**
 * Servlet implementation class FeedbackInsertServlet
 */
@WebServlet("/FeedbackInsertServlet")
public class FeedbackInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedbackInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    FeedbackOperationImp feedbackOper = new FeedbackOperationImp();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String feedbackno=request.getParameter("feedbackno");
		String feedbackdetail=request.getParameter("feedbackdetail");
		String userno=request.getParameter("userno");

		String username=request.getParameter("username");
		String contactdetail=request.getParameter("contactdetail");

		
		int i=0 ;
		if(StringUtils.isNullOrEmpty(feedbackno)) {
			Feedback s=new Feedback(0,feedbackdetail,Integer.parseInt(userno),username,contactdetail);
			i=feedbackOper.insertFeedback(s);
		}else {
			Feedback s=new Feedback(Integer.parseInt(feedbackno),feedbackdetail,Integer.parseInt(userno),username,contactdetail);
			i=feedbackOper.updateFeedback(s);
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
