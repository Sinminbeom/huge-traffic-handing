package com.example.fastcampusmysql.domain.post.service;

import com.example.fastcampusmysql.domain.post.dto.PostCommand;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.repository.JpaPostRepository;
import com.example.fastcampusmysql.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostWriteService {
    private final JpaPostRepository postRepository;
    public Long create(PostCommand command) {
        Post post = Post
                .builder()
                .memberId(command.memberId())
                .contents(command.contents())
                .build();
        return postRepository.save(post).getId();
    }

    // lock을 쓰기 위해선 트랜잭션을 붙여줘야한다
    @Transactional
    public void likePost(Long postId) {
        // Post post = postRepository.findById(postId, true).orElseThrow();
        Post post = postRepository.findByIdWithLock(postId).orElseThrow();
        post.incrementLikeCount();
        postRepository.save(post);
    }

    public void likePostByOptimisticLock(Long postId) {
         Post post = postRepository.findById(postId, false).orElseThrow();
        post.incrementLikeCount();
        postRepository.save(post);
    }
}
