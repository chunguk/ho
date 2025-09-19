package action;

import java.io.IOException;

import dao.VisitDao;
import db.vo.VisitVo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VisitModifyFormAction
 */
@WebServlet("/visit/modify_form.do")
public class VisitModifyFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		//visit/modify_form.do?idx=29
		
		//1. idx 받기
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		//2. idx에 해당되는 1건의 데이터를 얻어온다
		VisitVo vo = VisitDao.getInstance().selectOne(idx);
		
		//<textarea>에 수정데이터 원본을 넣기 위해서
		//<textarea> : \n (Window : \r\n Unix 계열 : \n)
		//HTML		 : <br>
		String content = vo.getContent().replaceAll("<br>", "\n"); //<br> 다 \n으로 바꿔야함
		vo.setContent(content);
		
		
		// request 바인딩
		request.setAttribute("vo", vo);
		
		//dispatcher(forward)
		String forwar_page = "visit_modify_form.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forwar_page);
		disp.forward(request, response);
		
		//안녕
		//ㅏㅏㅏㅏㅏㅏㅏㅏ

	}

}

