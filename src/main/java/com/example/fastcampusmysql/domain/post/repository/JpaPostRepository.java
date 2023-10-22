package com.example.fastcampusmysql.domain.post.repository;

import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface JpaPostRepository extends JpaRepository<Post, Long>, PostRepository {
    @Query(value = """
    SELECT new com.example.fastcampusmysql.domain.post.dto.DailyPostCount(p.memberId, p.createdDate, count(p.id))
    FROM Post p
    WHERE p.memberId = :#{#test.memberId}
      AND p.createdDate BETWEEN :#{#test.firstDate} AND :#{#test.lastDate}
    GROUP BY p.memberId, p.createdDate
""")
    List<DailyPostCount> groupByCreatedDate(@Param("test") DailyPostCountRequest request);

    @Query("SELECT p FROM Post p WHERE p.id = :postId" +
            " AND (:requiredLock = true OR :requiredLock = false)")
    Optional<Post> findById(@Param("postId") Long postId, @Param("requiredLock") boolean requiredLock);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Post p WHERE p.id = :postId")
    Optional<Post> findByIdWithLock(@Param("postId") Long postId);

}
