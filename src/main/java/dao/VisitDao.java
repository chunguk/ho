package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import db.vo.VisitVo;
import service.MyBatisConnector;

// VisitDAO(Date Access Object)
// DAO : DRUD처리하는 객체

public class VisitDao {
	
	SqlSessionFactory factory ;
	
//싱글톤(Singleton) 은 프로그램 실행 동안 객체를 하나만 만들고 공유하는 패턴kkkk
//VisitDao = 클래스이름 환경변수lllll
static VisitDao single = null;

	public static VisitDao getInstance() {
	
		if (single == null)
			single = new VisitDao();
	
		return single;
	
	}
	
	private VisitDao() {
			factory = MyBatisConnector.getInstance().getSqlSessionFactory();		
	}

	
	//조회
	public List<VisitVo> selectList() {

		List<VisitVo> list =null;

		// 1.작업수행하는 객체(마이바티스 SqlSession)얻어온다
		SqlSession sqlSession = factory.openSession();
		
		//2. 조회 작업 수행					namespace.mapper_id 맵퍼.xml들여다보기
		list = sqlSession.selectList("visit.visit_list");
		
		//3. 작업은항상닫기
		sqlSession.close();
		
		//-------위에 123규칙은 지켜라
		
		return list;

	}
	//같은 셀렉트끼리 오버로드관계 인자가있냐없냐차이
	public List<VisitVo> selectList(Map<String, Object> map) {

		List<VisitVo> list =null;

		// 1.작업수행하는 객체(마이바티스 SqlSession)얻어온다
		SqlSession sqlSession = factory.openSession();
		
		//2. 조회 작업 수행					namespace.mapper_id  , map=파라미터
		list = sqlSession.selectList("visit.visit_list_condition", map);
		
		//3. 작업은항상닫기
		sqlSession.close();
		
		//-------위에 123규칙은 지켜라
		
		return list;
		
	}
	
	//추가		비지트액션이호출
	public int insert(VisitVo vo) {

		int res = 0;
		//SqlSession sqlSession = factory.openSession();
		//			true하면 알아서 오토커밋				true : auto commit
		SqlSession sqlSession = factory.openSession(true);
		
		//2.작업 인서트 수행
		res = sqlSession.insert("visit.visit_insert" , vo);
				
		//2-1 sqlDB에 저장 
		//sqlSession.commit();	//DB적용
		
		//sqlSession.rollback();		//취소
		
		
		
		//3.닫기
		sqlSession.close();
	
		return res;
	}

	//삭제
	public int delete(int idx) {

		int res = 0;
		
		//1.			true하면 알아서 오토커밋				true : auto commit
		SqlSession sqlSession = factory.openSession(true);
		
		//2.작업 인서트 수행			mapper_id	   ,  parameter
		res = sqlSession.delete("visit.visit_delete" , idx);
				
		//3.닫기
		sqlSession.close();
		
		return res;
	}

	//[수정] idx에 해당하는 1건 데이터를 조회해서 수정
	public VisitVo selectOne(int idx) {

		VisitVo vo = null;

		//1.작업객체얻어오기
		SqlSession sqlSession = factory.openSession(true);
		
		//2.작업객체 수행
		vo = sqlSession.selectOne("visit.visit_one" , idx);
		
		//3. 
		sqlSession.close();

		return vo;
	}

		
	//수정 템플릿
	public int update(VisitVo vo) {

		int res = 0;
		
		//1.작업객체 가져오기
		SqlSession sqlSession = factory.openSession(true);
		
		//2. 작업객체수행				mapper_id		,	parameter
		res = sqlSession.update("visit.visit_update", vo);
		
		//3.
		sqlSession.close();
	
		return res;
	}



	
	

}//end DAO
