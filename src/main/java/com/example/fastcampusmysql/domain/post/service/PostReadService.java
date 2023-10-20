package com.example.fastcampusmysql.domain.post.service;

import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.repository.JpaPostRepository;
import com.example.fastcampusmysql.domain.post.repository.PostRepository;
import com.example.fastcampusmysql.util.CursorRequest;
import com.example.fastcampusmysql.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostReadService {
//    private final PostRepository postRepository;
    private final JpaPostRepository postRepository;

    public List<DailyPostCount> getDailyPostCount(DailyPostCountRequest request) {
        /*
            시작 일자와 종료일자 사이에 memberId가 작성한 게시물을 가져오려고 한다.
            반환 값은 일자별 memberId의 게시물 갯수
            select * from where POST where memberId = :memberId and between firstDate and lastDate
            group by createdDate, memberId
         */
        return postRepository.groupByCreatedDate(request);
    }
    public Page<Post> getPosts(Long memberId, Pageable pageable) {
        return postRepository.findAllByMemberId(memberId, pageable);
    }
    public PageCursor<Post> getPosts(Long memberId, CursorRequest cursorRequest) {
        List<Post> posts = findAllBy(memberId, cursorRequest);
        Long nextKey = getNextKey(posts);
        return new PageCursor<>(cursorRequest.next(nextKey), posts);
    }

    public PageCursor<Post> getPosts(List<Long> memberIds, CursorRequest cursorRequest) {
        List<Post> posts = findAllBy(memberIds, cursorRequest);
        Long nextKey = getNextKey(posts);
        return new PageCursor<>(cursorRequest.next(nextKey), posts);
    }
    public List<Post> getPosts(List<Long> Ids) {
        return postRepository.findAllByIdIn(Ids);
    }

    private List<Post> findAllBy(Long memberId, CursorRequest cursorRequest) {
        Pageable pageable = PageRequest.of(0, cursorRequest.size());
        if (cursorRequest.hasKey()) {
//            return postRepository.findAllByIdLessThanAndMemberIdOrderByIdDesc(cursorRequest.key(), memberId, cursorRequest.size());
            return postRepository.findAllByIdLessThanAndMemberIdOrderByIdDesc(cursorRequest.key(), memberId, pageable);
        } else {
//            return postRepository.findAllByMemberIdOrderByIdDesc(memberId, cursorRequest.size());
            return postRepository.findAllByMemberIdOrderByIdDesc(memberId, pageable);
        }
    }
    private List<Post> findAllBy(List<Long> memberIds, CursorRequest cursorRequest) {
        Pageable pageable = PageRequest.of(0, cursorRequest.size());
        if (cursorRequest.hasKey()) {
//            return postRepository.findAllByIdLessThanAndMemberIdInOrderByIdDesc(cursorRequest.key(), memberIds, cursorRequest.size());
            return postRepository.findAllByIdLessThanAndMemberIdInOrderByIdDesc(cursorRequest.key(), memberIds, pageable);
        } else {
//            return postRepository.findAllByMemberIdInOrderByIdDesc(memberIds, cursorRequest.size());
            return postRepository.findAllByMemberIdInOrderByIdDesc(memberIds, pageable);
        }
    }

    private Long getNextKey(List<Post> posts) {
        return posts.stream().mapToLong(Post::getId).min().orElse(CursorRequest.NONE_Key);
    }
}
