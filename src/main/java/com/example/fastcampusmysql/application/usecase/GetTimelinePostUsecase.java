package com.example.fastcampusmysql.application.usecase;

import com.example.fastcampusmysql.domain.follow.entity.Follow;
import com.example.fastcampusmysql.domain.follow.service.FollowReadService;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.service.PostReadService;
import com.example.fastcampusmysql.util.CursorRequest;
import com.example.fastcampusmysql.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class GetTimelinePostUsecase {
    private final FollowReadService followREadService;
    private final PostReadService postReadService;
    public PageCursor<Post> execute(Long memberId, CursorRequest cursorRequest) {
        /*
            1. memberId -> follow 조회
            2. 1번 결과로 게시물 조회
         */
        List<Follow> followings = followREadService.getFollowings(memberId);
        List<Long> followingMemberIds = followings.stream().map(f -> f.getToMemberId()).toList();
        return postReadService.getPosts(followingMemberIds, cursorRequest);
    }
    public PageCursor<Post> executeByTimeline(Long memberId, CursorRequest cursorRequest) {
        /*
            1. Timeline 조회
            2. 1번에 해당하는 게시물을 조회한다.
         */
        List<Follow> followings = followREadService.getFollowings(memberId);
        List<Long> followingMemberIds = followings.stream().map(f -> f.getToMemberId()).toList();
        return postReadService.getPosts(followingMemberIds, cursorRequest);
    }
}
