package com.example.fastcampusmysql.domain.follow.repository;

import com.example.fastcampusmysql.domain.follow.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFollowRepository extends JpaRepository<Follow, Long>, FollowRepository {
}
