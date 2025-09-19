package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VisitDao;

/**
 * Servlet implementation class VisitDeleteAction
 */
@WebServlet("/visit/delete.do")
public class VisitDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// /visit/delete.do?idx=14
		
		//파마미터는 스트링으로만받음
		//String str_idx = request.getParameter("idx");
		//그래서 강제로 인트형으로 형변환 시킴
		//int	idx   = Integer.parseInt(str_idx);
		
		//1. 삭제할 게시물 idx받기
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		//2. DB에서삭제 DAO에서 다 권할받음 
		int res = VisitDao.getInstance().delete(idx);
		
		//3. 메인으로 이동(목록보기)
		response.sendRedirect("list.do");
		
		
		
		
	}

}
