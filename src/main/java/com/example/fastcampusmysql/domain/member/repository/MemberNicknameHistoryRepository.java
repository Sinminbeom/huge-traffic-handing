package com.example.fastcampusmysql.domain.member.repository;

import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;

import java.util.List;

public interface MemberNicknameHistoryRepository {
    List<MemberNicknameHistory> findAllByMemberId(Long memberId);
    MemberNicknameHistory save(MemberNicknameHistory memberNicknameHistory);
}
