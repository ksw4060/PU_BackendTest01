package example2.demo2.member.service;


import example2.demo2.member.entity.Member;
import example2.demo2.member.repository.MemberRepositoryV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceV1 {

    private final MemberRepositoryV1 memberRepositoryV1;

    /**
     * 회원가입 - 이메일, 비밀번호 => return Id
     * 중복 회원 검증 : validateDuplicateMember
     */
    public Long join(Member member) {

        validateDuplicateMember(member); //중복 회원 검증
        memberRepositoryV1.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {

        List<Member> findMembers = memberRepositoryV1.findByEmail(member.getEmail());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다. 다른 이메일을 이용해주세요. ");
        }
    }

    /**
     * Id -> 특정 회원 조회 -> 그 회원의 Id 반환
     */
    public Member findOneMember(Long memberId) {
        return memberRepositoryV1.findById(memberId).get();
    }

    public List<Member> findMembers() {
        return memberRepositoryV1.findAll();
    }

    /**
     * 회원 정보 수정하기
     */
    @Transactional
    public void updateMember(Long id, String nickname) {
        Optional<Member> member = memberRepositoryV1.findById(id);

    }
}
