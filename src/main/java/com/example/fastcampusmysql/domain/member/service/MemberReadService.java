package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.MemberDTO;
import com.example.fastcampusmysql.domain.member.dto.MemberNicknameHistoryDTO;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;
import com.example.fastcampusmysql.domain.member.repository.MemberNicknameHistoryRepository;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberReadService {
    private final MemberRepository memberRepository;
    private final MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    public MemberDTO getMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow();
        return toDTO(member);
    }
    public MemberDTO toDTO(Member member) {
        return new MemberDTO(member.getId(), member.getEmail(), member.getNickname(), member.getBirthday());
    }
    public List<MemberDTO> getMembers(List<Long> ids) {
        List<Member> members = memberRepository.findAllByIdIn(ids);
        return members.stream().map(this::toDTO).toList();
    }
    public List<MemberNicknameHistoryDTO> getNicknameHistories(Long memberId) {
        return memberNicknameHistoryRepository.findAllByMemberId(memberId)
                .stream()
                .map(this::toDTO)
                .toList();
    }
    private MemberNicknameHistoryDTO toDTO(MemberNicknameHistory memberNicknameHistory) {
        return new MemberNicknameHistoryDTO(
                memberNicknameHistory.getId(),
                memberNicknameHistory.getMemberId(),
                memberNicknameHistory.getNickname(),
                memberNicknameHistory.getCreatedAt()
        );
    }
}
