package example2.demo2.member.model;

import example2.demo2.member.controller.MemberApiControllerV1;
import example2.demo2.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "memberId")
@AllArgsConstructor
public class MemberDto {

    private Long memberId;
    private String email;
    private String username;
    private String nickname;

    private String password1;
    private String password2;

    public MemberDto(Member member) {
        this(member.getId(),
                member.getEmail(),
                member.getUsername(),
                member.getNickname(),
                member.getPassword1(),
                member.getPassword2()
        );
    }

    public String getPassword1() {
        return password1;
    }

    public String getPassword2() {
        return password2;
    }


}
