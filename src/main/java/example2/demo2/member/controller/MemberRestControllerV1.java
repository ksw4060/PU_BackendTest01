package example2.demo2.member.controller;

import example2.demo2.member.entity.Member;
import example2.demo2.member.model.MemberForm;
import example2.demo2.member.service.MemberServiceV1;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberRestControllerV1 {

    private final MemberServiceV1 memberServiceV1;

    @GetMapping("/api/v1/members")
    public List<Member> memberList() {
        return memberServiceV1.findMembers();
    }

    @GetMapping("/api/v1/members/new")
    public MemberForm createForm() {
        return new MemberForm();
    }

    @PostMapping("/api/v1/members/new")
    public CreateMemberResponse joinMember(@Valid @RequestBody MemberForm memberForm) {
        String email = memberForm.getEmail();
        String username = memberForm.getUsername();
        String nickname = memberForm.getNickname();
        String password1 = memberForm.getPassword1();
        String password2 = memberForm.getPassword2();

        if (!password1.equals(password2)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        Member member = createMember(email, username, nickname, password1);
        Long id = memberServiceV1.join(member);

        String getEmail = member.getEmail();
        return new CreateMemberResponse(id, getEmail);
    }

    private static Member createMember(String email, String username, String nickname, String password1) {
        Member member = new Member();
        member.setEmail(email);
        member.setUsername(username);
        member.setNickname(nickname);
        member.setPassword(password1);
        return member;
    }

    @Data
    private class CreateMemberResponse {
        private Long id;
        private String email;

        public CreateMemberResponse(Long id, String email) {
            this.id = id;
            this.email = email;
        }
    }
}
