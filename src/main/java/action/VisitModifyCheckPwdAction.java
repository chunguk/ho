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
 * Servlet implementation class VisitModifyCheckPwdAction
 */
@WebServlet("/visit/modify_pwd.do")
public class VisitModifyCheckPwdAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		//수신바인딩 설정 utf-8
		request.setCharacterEncoding("utf-8");
		
		//1. 파라메터받기
		int		idx		= Integer.parseInt(request.getParameter("idx"));
		String c_pwd	= request.getParameter("c_pwd");
		
		//2. idx해당되는 게시물 1건 가져오기 싱글톤받는 인스턴스쓰기
		VisitVo vo = VisitDao.getInstance().selectOne(idx);
		
		//3. 비밀번호 비교 원래비번이랑 새로쓴비번확인
		boolean bResult = vo.getPwd().equals(c_pwd);
		
		// JSON형식 {"result" : true}
		String json = String.format("{\"result\": %b}", bResult);
		
		//4. 응답처리 제이슨처리 한글셋처리
		response.setContentType("applicatuin/json; charset-utf-8;");
		response.getWriter().print(json);
		
	}

}

