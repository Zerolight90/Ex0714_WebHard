package mybatis.dao;

import mybatis.service.FactoryService;
import mybatis.vo.MemVO;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Map;

public class MemberDAO {
    //login.jsp에서 호출하는 로그인 함수
    public static MemVO login(String id, String pw){
        Map<String, String> map = new HashMap<>();
        map.put("m_id", id);
        map.put("m_pw", pw);
        //sql문을 호출하기 위해 필요한 sqlSession얻기
        SqlSession ss = FactoryService.getFactory().openSession();
        MemVO mvo = ss.selectOne("member.login", map);
        ss.close();
        return mvo;
    }

    public static int registry(MemVO mvo){

        SqlSession ss = FactoryService.getFactory().openSession(); //openSession(true); 를 한다면 자동컷이 되지만, 별로 좋은 코드는 아니다

        int cnt = ss.insert("member.add", mvo);
        //cnt는 저장된 레코드의 수를 저장 한다.

        if (cnt > 0){
            ss.commit();
        }else{
            ss.rollback();
        }
        ss.close();
        return cnt;

    }

    //아이디 중복 체크
    public static MemVO chk(String id){
        Map<String, String> map = new HashMap<>();
        map.put("m_id", id);

        //sql문을 호출하기 위해 필요한 sqlSession얻기
        SqlSession ss = FactoryService.getFactory().openSession();
        MemVO mvo = ss.selectOne("chk.search", id);
        ss.close();
        return mvo;
    }


}


