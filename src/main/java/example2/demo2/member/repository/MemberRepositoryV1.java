package example2.demo2.member.repository;

import example2.demo2.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepositoryV1 extends JpaRepository<Member, Long> {

    // findByEmail 이라는 naming을 보고, "email"을 기준으로 jpql로 바꿔준다
    // select m from member m where m.email = :email
    List<Member> findByEmail(String email);
}
