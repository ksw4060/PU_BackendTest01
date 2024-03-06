package example2.demo2.member.controller;

import example2.demo2.member.entity.Member;
import example2.demo2.member.exception.MemberAlreadyExistsException;
import example2.demo2.member.model.MemberDto;
import example2.demo2.member.repository.MemberRepositoryV1;
import example2.demo2.member.service.MemberServiceV1;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MemberApiControllerV1 {

    private final MemberServiceV1 memberServiceV1;

    @GetMapping("/members")
    public ResponseEntity<List<MemberDto>> memberList() {
        // Entity 조회
        List<Member> memberList = memberServiceV1.findMembers();
        // Entity -> DTO
        List<MemberDto> collect = memberList.stream()
                .map(m -> new MemberDto(m))
                .collect(Collectors.toList());
        return new ResponseEntity<>(collect, HttpStatus.OK);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<MemberDto> getMemberById(@PathVariable Long id) {
        Member oneMember = memberServiceV1.findOneMember(id);
        MemberDto memberDto = new MemberDto(oneMember);
        return new ResponseEntity<>(memberDto, HttpStatus.OK);

    }

    // 수정 해야 할 부분. 2024.03.06 Tue
    @GetMapping("/members/new")
    public MemberDto createForm() {
        return new MemberDto(new Member());
    }

    @PostMapping("/members/new")
    public ResponseEntity<?> joinMember(@Valid @RequestBody CreateMemberRequest request) {
        Member member = createMemberFromRequest(request);

        Long joinMemberId = memberServiceV1.join(member); // validate - email, password1,2

        CreateMemberResponse response = new CreateMemberResponse(joinMemberId, member.getEmail());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private Member createMemberFromRequest(CreateMemberRequest request) {
        Member member = new Member();
        member.setEmail(request.getEmail());
        member.setUsername(request.getUsername());
        member.setNickname(request.getNickname());
        member.setPassword1(request.getPassword1());
        member.setPassword2(request.getPassword2());
        return member;
    }

    private static Long setMemberInformation(CreateMemberRequest request, Member member) {
        member.setEmail(request.email);
        member.setUsername(request.username);
        member.setNickname(request.nickname);
        member.setPassword1(request.password1);
        member.setPassword2(request.password2);

        return member.getId();
    }

    @Data
    static class CreateMemberRequest {
        private String email;
        private String username;
        private String nickname;
        private String password1;
        private String password2;
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
