package com.example.fastcampusmysql.domain.member.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String email;
    private LocalDate birthday;
    private LocalDateTime createdAt;

    final private static Long NAME_MAX_LENGTH = 10L;
    @Builder
    public Member(Long id, String nickname, String email, LocalDate birthday, LocalDateTime createdAt) {
        this.id = id;

        validateNickname(nickname);
        this.nickname = Objects.requireNonNull(nickname);

        this.email = Objects.requireNonNull(email);
        this.birthday = Objects.requireNonNull(birthday);
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }

    public void changeNickname(String to) {
        // 널이 아니여야 하고
        Objects.requireNonNull(to);
        // 최대 길이 10
        validateNickname(to);
        nickname = to;
    }
    private void validateNickname(String nickname) {
        Assert.isTrue(nickname.length() <= NAME_MAX_LENGTH, "최대 길이를 초과했습니다.");
    }
}
