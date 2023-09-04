package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.MemberDTO;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberReadService {
    private final MemberRepository memberRepository;

    public MemberDTO getMember(Long id) {
        Member member = memberRepository.findByid(id).orElseThrow();
        return toDTO(member);
    }
    public MemberDTO toDTO(Member member) {
        return new MemberDTO(member.getId(), member.getEmail(), member.getNickname(), member.getBirthday());
    }
}
