package com.example.fastcampusmysql.util;

import com.example.fastcampusmysql.domain.post.entity.Post;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import static org.jeasy.random.FieldPredicates.named;
import static org.jeasy.random.FieldPredicates.ofType;
import static org.jeasy.random.FieldPredicates.inClass;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.function.Predicate;

public class PostFixtureFactory {
    public static EasyRandom get(Long memberId, LocalDate firstDate, LocalDate lastDate) {
        Predicate<Field> memberIdPredicate = named("memberId").and(ofType(Long.class)).and(inClass(Post.class));
        Predicate<Field> idPredicate = named("id").and(ofType(Long.class)).and(inClass(Post.class));
        EasyRandomParameters params = new EasyRandomParameters()
                .dateRange(firstDate, lastDate)
                .randomize(memberIdPredicate, () -> memberId)
                .excludeField(idPredicate);
        return new EasyRandom(params);
    }
}
