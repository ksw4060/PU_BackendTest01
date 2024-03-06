package example2.demo2.member.controller;

import example2.demo2.member.entity.Member;
import example2.demo2.member.model.MemberDto;
import example2.demo2.member.service.MemberServiceV1;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class MemberControllerV1 {

    private final MemberServiceV1 memberServiceV1;


    @GetMapping("/members")
    public String memberList(Model model) {
        List<Member> members = memberServiceV1.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    @GetMapping("/members/new")
    public String createForm(Model model, Member member) {
        model.addAttribute("memberForm", new MemberDto(member));
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String joinMember(@Valid MemberDto memberForm, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "입력값을 확인해주세요.");
            return "members/createMemberForm";
        }

        if (!memberForm.getPassword1().equals(memberForm.getPassword2())) {
            model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
            return "members/createMemberForm";
        }

        Member member = createMember(memberForm);

        memberServiceV1.join(member);
        return "redirect:/";
    }

    private static Member createMember(MemberDto memberForm) {
        Member member = new Member();
        member.setEmail(memberForm.getEmail());
        member.setUsername(memberForm.getUsername());
        member.setPassword1(memberForm.getPassword1());
        return member;
    }

}
