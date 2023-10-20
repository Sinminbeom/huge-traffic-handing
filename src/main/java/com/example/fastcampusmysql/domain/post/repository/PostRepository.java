package com.example.fastcampusmysql.domain.post.repository;

import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepository {
//    List<DailyPostCount> groupByCreatedDate(DailyPostCountRequest request);
    List<Post> findAllByIdIn(List<Long> Ids);
    Page<Post> findAllByMemberId(Long memberId, Pageable pageable);
    Long countByMemberId(Long memberId);
    List<Post> findAllByMemberIdOrderByIdDesc(Long memberId, Pageable pageable);
    List<Post> findAllByMemberIdInOrderByIdDesc(List<Long> memberIds, Pageable pageable);
    List<Post> findAllByIdLessThanAndMemberIdOrderByIdDesc(Long id, Long memberId, Pageable pageable);
    List<Post> findAllByIdLessThanAndMemberIdInOrderByIdDesc(Long id, List<Long> memberIds, Pageable pageable);
    Post save(Post post);
//    List<Post> saveAll(List<Post> posts);

}
