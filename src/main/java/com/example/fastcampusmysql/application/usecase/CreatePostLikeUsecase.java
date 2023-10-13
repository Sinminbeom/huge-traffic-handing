package com.example.fastcampusmysql.application.usecase;

import com.example.fastcampusmysql.domain.member.dto.MemberDTO;
import com.example.fastcampusmysql.domain.member.service.MemberReadService;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.service.PostLikeWriteService;
import com.example.fastcampusmysql.domain.post.service.PostReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreatePostLikeUsecase {
    private final PostReadService postReadService;
    private final MemberReadService memberReadService;
    private final PostLikeWriteService postLikeWriteService;

    public void execute(Long postId, Long memberId) {
        Post post = postReadService.getPost(postId);
        MemberDTO memberDTO = memberReadService.getMember(memberId);
        postLikeWriteService.create(post, memberDTO);
    }
}
