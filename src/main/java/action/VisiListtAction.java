package action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VisitDao;
import db.vo.VisitVo;

/**
 * Servlet implementation class VisiListtAction
 */
//	webapp경로 역할:방명록(visit) 목록 조회 후 JSP로 전달
@WebServlet("/visit/list.do")
public class VisiListtAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// visit/list.do?search=content&search_text=길동
		
		//0수신인코딩
		request.setCharacterEncoding("utf-8");
		
		//1.parameter받기
		String search 			= request.getParameter("search");
		if(search==null) search="all";
		String search_text 	= request.getParameter("search_text");
		
		//검색정보를 담을 Map을 선언
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		if(search.equals("name_content")) {
			map.put("name", search_text);
			map.put("content", search_text);
			
		}else if(search.equals("name")) {
			map.put("name", search_text);
		}else if(search.equals("content")) {
			map.put("content", search_text);
		}
		
		
		//방명록 목록 가져오기
		//반환 타입: List<VisitVo> (방명록 1건당 VisitVo 객체)/ DB에서 방명록 전체 데이터를 가져옴
		List<VisitVo> list = VisitDao.getInstance().selectList(map); 
		
		//request binding
		//JSP로 전달하기 위해 request 객체에 데이터 바인딩
		//JSP에서 ${list}로 접근 가능
		request.setAttribute("list", list);
		

		//dispatcher(forward)jsp로 보낼 jsp주소 써야됨	
		String forwar_page					 = "visit_list.jsp";	//전달될 jsp주소 변수에 입력
		RequestDispatcher disp 			 = request.getRequestDispatcher(forwar_page); //위의 변수로 jsp페이지 지정
		disp.forward(request, response); 		//서버 내부에서 request와 response 객체를 그대로
		//JSP로 전달 브라우저 주소는 바뀌지 않고, JSP에서 출력
	}

}

						//🔹 전체 흐름 요약
						
						//브라우저에서 /visit/list.do 요청
						
						//서블릿 실행 → DB에서 방명록 리스트 조회
						
						//조회한 리스트를 request에 list로 저장
						
						//visit_list.jsp로 포워딩
						
						//JSP에서 ${list}를 이용해 화면에 방명록 출력
