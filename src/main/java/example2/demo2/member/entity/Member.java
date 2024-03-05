package example2.demo2.member.entity;

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
    private String password;
//
//    @Enumerated(EnumType.STRING)
//    private MemberRole role;

    @Column(name = "joined_at", nullable = false)
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

    public void setPassword(String password) {
        this.password = password;
    }
}
