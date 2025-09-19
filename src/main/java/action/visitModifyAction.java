package action;

import java.io.IOException;

import dao.VisitDao;
import db.vo.VisitVo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class visitModifyAction
 */
@WebServlet("/visit/modify.do")
public class visitModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// /visit/modify.do?idx=30&name=황춘국1&content=안녕%0D%0A하이&pwd=1234
		
		//1. 수신 인코딩 설정
		request.setCharacterEncoding("utf-8");
		
		//2. parameter 받기
		int 	idx 	= Integer.parseInt(request.getParameter("idx"));
		String name 	= request.getParameter("name");
		String content 	= request.getParameter("content").replaceAll("\n", "<br>");
		String pwd		= request.getParameter("pwd");
		
		//3.   ip 받기
		String ip 		= request.getRemoteAddr();
		
		//4. VisitVo 포장
		VisitVo vo 		= new VisitVo(idx, name, content, pwd, ip);
		
		//5. DB update
		int res = VisitDao.getInstance().update(vo);
		
		//6. 메인화면[목록보기]로 돌아가라
		response.sendRedirect("list.do");
		
		
	}

}

