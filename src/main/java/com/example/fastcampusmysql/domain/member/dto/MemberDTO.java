package com.example.fastcampusmysql.domain.member.dto;

import java.time.LocalDate;

public record MemberDTO(
        Long id,
        String email,
        String nickname,
        LocalDate birthday
) {
}
