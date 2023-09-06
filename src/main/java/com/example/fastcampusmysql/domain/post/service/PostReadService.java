package com.example.fastcampusmysql.domain.post.service;

import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostReadService {
    private final PostRepository postRepository;

    public List<DailyPostCount> getDailyPostCount(DailyPostCountRequest request) {
        /*
            시작 일자와 종료일자 사이에 memberId가 작성한 게시물을 가져오려고 한다.
            반환 값은 일자별 memberId의 게시물 갯수
            select * from where POST where memberId = :memberId and between firstDate and lastDate
            group by createdDate, memberId
         */
        return postRepository.groupByCreatedDate(request);

    }
}
