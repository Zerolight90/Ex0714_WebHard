package mybatis.dao;

import mybatis.service.FactoryService;
import mybatis.vo.MemoVO;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoDAO {
    //memoList.jsp에서 표현할 메모 목록
    public static List<MemoVO> getAll(){
        //팩토리를 통해 sqlSession을 얻어야 한다.
        SqlSession ss = FactoryService.getFactory().openSession();
        List<MemoVO> list = ss.selectList("memo.all");
        ss.close();
        return list;
    }

    //메모를 저장하는 기능
    public static int addMemo(String writer, String content, String ip){
        //인자로 받은 writet, contet, ip 를 add라는 Mapper로 보내기 위해
        // 해당 Mapper의 인자(parameeter Type)인 Map 구조를 준비해야 한다.
        Map<String, String> map = new HashMap<>();
        //인자로 받은값을 생성한 map에 저장
        map.put("writer", writer);
        map.put("content", content);
        map.put("ip", ip);

        //이제 add라는 Mapper를 호출하기 위해 sqlsseion이 필요함
        SqlSession ss = FactoryService.getFactory().openSession(); //openSession(true); 를 한다면 자동컷이 되지만, 별로 좋은 코드는 아니다

        int cnt = ss.insert("memo.add", map);
        //cnt는 저장된 레코드의 수를 저장 한다.

        if (cnt > 0){
            ss.commit();
        }else{
            ss.rollback();
        }
        ss.close();
        return cnt;

    }
}
