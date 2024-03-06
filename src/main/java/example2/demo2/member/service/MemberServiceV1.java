package example2.demo2.member.service;


import example2.demo2.member.entity.Member;
import example2.demo2.member.repository.MemberRepositoryV1;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceV1 {

    private final MemberRepositoryV1 memberRepositoryV1;

    /**
     * 회원가입 - 이메일, 비밀번호 => return Id
     * email - 중복 회원 검증 : validateDuplicateMemberByEmail
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMemberByEmail(member); // 중복 회원 검증 , 이미 존재하는 이메일인가?
        validatePasswords(member.getPassword1(), member.getPassword2()); // 비밀번호 검증 - 비번1과 비번2가 같은지
        memberRepositoryV1.save(member);
        return member.getId(); // 저장된 회원의 ID 반환
    }

    /**
     * Id -> 특정 회원 조회 -> 그 회원의 Id 반환
     */
    public Member findOneMember(Long memberId) {
        return memberRepositoryV1.findById(memberId).get();
    }
    /**
     * 전체 회원 조회
     */
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

    /**
     * 이메일중복검사
     */
    private void validateDuplicateMemberByEmail(Member member) {
        Optional<Member> findMembers = memberRepositoryV1.findByEmail(member.getEmail());
        if (findMembers.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 존재하는 회원입니다. 다른 이메일을 이용해주세요.");
        }
    }

    /**
     * 비밀번호 검증
     */
    public void validatePasswords(String password1, String password2) {
        if (!password1.equals(password2)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "두 비밀번호가 일치하지 않습니다.");
        }
    }
}
