package com.example.fastcampusmysql.domain.member.dto;

import java.time.LocalDate;

// get,set 자동으로 만들어지고 프로퍼티를 사용할 수 있다.
public record RegisterMemberCommand(
        String email,
        String nickname,
        LocalDate birthdate
) {
}
