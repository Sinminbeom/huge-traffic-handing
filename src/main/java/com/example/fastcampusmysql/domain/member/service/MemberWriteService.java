package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;
import com.example.fastcampusmysql.domain.member.repository.MemberNicknameHistoryRepository;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberWriteService {
    final private MemberRepository memberRepository;
    private final MemberNicknameHistoryRepository memberNicknameHistoryRepository;
    public Member register(RegisterMemberCommand registerMemberCommand) {
        /*
            목표 - 회원정보(이메일, 닉네임, 생년월일)를 등록한다.
                - 닉네임은 10자를 넘길 수 없다.
            파라미터 - memberRegisterCommand

            Member member = Member.of(memberRegisterCommand)
            memberRepository.save(member)
         */
        Member member = Member.builder()
                .nickname(registerMemberCommand.nickname())
                .email(registerMemberCommand.email())
                .birthday(registerMemberCommand.birthdate())
                .build();
        Member saveMember = memberRepository.save(member);
        saveNicknameHistory(saveMember);
        return saveMember;
    }

    public void changeNickname(Long memberId, String nickname) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        member.changeNickname(nickname);
        memberRepository.save(member);

        // 닉네임 변경 히스토리 저장
        saveNicknameHistory(member);
    }

    private void saveNicknameHistory(Member member) {
        MemberNicknameHistory memberNicknameHistory = MemberNicknameHistory
                .builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .createdAt(member.getCreatedAt())
                .build();
        memberNicknameHistoryRepository.save(memberNicknameHistory);
    }
}
