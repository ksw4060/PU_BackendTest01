package example2.demo2.member.entity;

import example2.demo2.member.entity.enums.MemberRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "members")
public class Member {

    @Id
    @SequenceGenerator(
            name = "member_sequence",
            sequenceName = "member_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "member_sequence"
    )
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(nullable = false)
    private String password1;

    @Column(nullable = false)
    private String password2;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Column(name = "joined_at", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate joinedAt;

    private Boolean activated = true;

    private Boolean phoneCertified = false;


    public void setUsername(String username) {
        this.username = username;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public void setRole(MemberRole role) {
        this.role = role;
    }


    public Member() {
        this.joinedAt = LocalDate.now();
    }

}
