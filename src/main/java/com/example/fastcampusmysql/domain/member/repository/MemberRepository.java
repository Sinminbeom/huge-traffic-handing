package com.example.fastcampusmysql.domain.member.repository;

import com.example.fastcampusmysql.domain.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Optional<Member> findById(Long id);
    List<Member> findAllByIdIn(List<Long> ids);
    Member save(Member member);
}
