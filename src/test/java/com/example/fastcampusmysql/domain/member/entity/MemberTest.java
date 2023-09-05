package com.example.fastcampusmysql.domain.member.entity;

import com.example.fastcampusmysql.util.MemberFixtureFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.LongStream;

class MemberTest {
    @Test
    @DisplayName("회원은 닉네임을 변경할 수 있다.")
    public void testChangeName() {
        Member member = MemberFixtureFactory.create();
        String expected = "minbeom";
        member.changeNickname(expected);

        Assertions.assertEquals(expected, member.getNickname());
    }
    @Test
    @DisplayName("회원은 닉네임은 최대 10자리를 넘을 수 없다.")
    public void testNicknameMaxLength() {
        Member member = MemberFixtureFactory.create();
        String overMaxLength = "minbeomminbeommin";

        Assertions.assertThrows(IllegalArgumentException.class, () -> member.changeNickname(overMaxLength));
    }
    @Test
    @DisplayName("이지랜덤 시드를 각각 설정하지 않으면 기본값 하나로 다 똑같은 값으로 나온다")
    public void testEasyRandom() {
        LongStream.range(0, 10)
                .mapToObj(MemberFixtureFactory::create)
                .forEach(member -> {
                    System.out.println(member.getNickname());
                });
    }
}