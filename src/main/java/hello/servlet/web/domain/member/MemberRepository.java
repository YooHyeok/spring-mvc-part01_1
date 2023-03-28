package hello.servlet.web.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    /**
     * 동시성 문제가 고려되지 않기 떄문에 실무에서는 ConcurrentHashMap, AtomicLong 사용을 고려한다.
     */
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // id 증가값 sequence

    /**
     * Singleton 작업
     * 스프링을 쓰지 않으므로 singleton 객체생성
     */
    private static final MemberRepository instance = new MemberRepository(); //
    public static MemberRepository getInstance() {
        return instance;
    }
    private MemberRepository() {

    }

    /**
     * 회원 저장 메소드
     * @param member
     * @return 저장 이후 member 객체 반환 (유효성을 검증하거나 저장된 내역을 확인한다)
     */
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    /**
     * Id를 기준으로 단건 조회
     * @param id
     * @return store map객체로부터 id key를 기준으로 검색후 1:1 매칭되는 value 반환
     */
    public Member findById(Long id) {
        return store.get(id);
    }

    /**
     * 전체 조회
     * @return store 리스트 목록
     */
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store에있는 모든 값들을 꺼내어 새로운 ArrayList에 담아준다.
        // new ArrayList의 값을 넣거나 조작해도 store에 있는 value List를 건들고싶지 않아서(store 자체를 보호하기 위해서)
    }

    /**
     * store 초기화 메소드
     */
    public void clearStore() {
        store.clear();
    }


}
