package hello.servlet.domain.member;

import hello.servlet.web.domain.member.Member;
import hello.servlet.web.domain.member.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest { //Junit 5부터는 Test클래스 접근제한자 생략 가능

    MemberRepository memberRepository = MemberRepository.getInstance(); //Spring을 사용하면 자동으로 Singleton을 보장해준다.

    /**
     * 테스트 종료 후 초기화 한다. <br/>
     * 전체 테스트시 테스트메소드 호출의 순서가 보장되지 않으므로 <br/>
     * 테스트 종료 후 초기화 하지 않으면 오류 발생을 야기한다. <br/>
     */
    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    /**
     * 저장 테스트
     * @throws Exception
     */
    @Test
    void save() throws Exception {
        //given
        Member member = new Member("hello", 20);

        //when
        Member savedMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
    }

    /**
     * 전체 조회
     * @throws Exception
     */
    @Test
    void findAll() throws Exception {
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result = memberRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2); // result객체 사이즈 비교
        assertThat(result).contains(member1, member2); // member1과 member2가 result 리스트 객체에 포함되어있는가

    }
}
