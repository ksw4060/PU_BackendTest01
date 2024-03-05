package example2.demo2.member.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class MemberForm {
    @NotEmpty(message = "email을 입력해주세요. ")
    private String email;
    @NotEmpty(message = "username을 입력해주세요. ")
    private String username;
    @NotEmpty(message = "nickname을 입력해주세요. ")
    private String nickname;

    @NotEmpty(message = "password를 입력해주세요. ")
    private String password1;
    @NotEmpty(message = "password를 다시한번 입력해주세요. ")
    private String password2;
}
