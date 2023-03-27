package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemberRepositoryTest { //Junit 5부터는 Test클래스 접근제한자 생략 가능

    MemberRepository memberRepository = MemberRepository.getInstance(); //Spring을 사용하면 자동으로 Singleton을 보장해준다.

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
        Assertions.assertThat(findMember).isEqualTo(savedMember);
    }
}
