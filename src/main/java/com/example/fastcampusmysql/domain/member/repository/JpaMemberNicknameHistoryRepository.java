package com.example.fastcampusmysql.domain.member.repository;

import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberNicknameHistoryRepository extends JpaRepository<MemberNicknameHistory, Long>, MemberNicknameHistoryRepository {
}
