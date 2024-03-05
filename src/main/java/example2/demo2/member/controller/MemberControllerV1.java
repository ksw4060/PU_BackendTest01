package example2.demo2.member.controller;

import example2.demo2.member.entity.Member;
import example2.demo2.member.model.MemberForm;
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
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String joinMember(@Valid MemberForm memberForm, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        String password1 = memberForm.getPassword1();
        String password2 = memberForm.getPassword2();

        if (!password1.equals(password2)) {
            model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
            model.addAttribute("memberForm", memberForm);
            return "members/createMemberForm";
        }
        Member member = createMember(memberForm, password1);

        memberServiceV1.join(member);
        return "redirect:/";
    }

    private static Member createMember(MemberForm form, String password1) {
        Member member = new Member();
        member.setEmail(form.getEmail());
        member.setUsername(form.getUsername());
        member.setPassword(password1);
        return member;
    }


}
