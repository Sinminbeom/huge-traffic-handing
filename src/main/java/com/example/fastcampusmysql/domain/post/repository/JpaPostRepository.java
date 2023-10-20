package com.example.fastcampusmysql.domain.post.repository;

import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaPostRepository extends JpaRepository<Post, Long>, PostRepository {
    //TODO : JPQL로 구현
//    String sql = String.format("""
//                SELECT createdDate, memberId, count(id) as count
//                FROM %s
//                WHERE memberId = :memberId
//                AND createdDate BETWEEN :firstDate and :lastDate
//                GROUP BY createdDate, memberId
//                """, TABLE);
    List<DailyPostCount> groupByCreatedDate(DailyPostCountRequest request);
}
