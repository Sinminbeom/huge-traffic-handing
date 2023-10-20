package com.example.fastcampusmysql.domain.post;

import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.repository.JpaPostRepository;
import com.example.fastcampusmysql.domain.post.repository.PostRepository;
import com.example.fastcampusmysql.util.PostFixtureFactory;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class PostBulkInsertTest {
    @Autowired
    private JpaPostRepository postRepository;

    @Test
    public void bulkInserts() {
        EasyRandom easyRandom = PostFixtureFactory.get(
                2L,
                LocalDate.of(1970, 1, 1),
                LocalDate.of(2022, 2, 1)
        );
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int count = 20000 * 100;
//        int count = 5;
        List<Post> posts = IntStream.range(0, count)
                .parallel()
                .mapToObj(i -> easyRandom.nextObject(Post.class))
                .toList();
        stopWatch.stop();
//        System.out.println("객체 생성 시간 = " + stopWatch.getTotalTimeSeconds());

        StopWatch queryStopWatch = new StopWatch();
        queryStopWatch.start();

        postRepository.saveAll(posts);

        queryStopWatch.stop();
        System.out.println("DB Insert 시간 = " + queryStopWatch.getTotalTimeSeconds());
    }
}
