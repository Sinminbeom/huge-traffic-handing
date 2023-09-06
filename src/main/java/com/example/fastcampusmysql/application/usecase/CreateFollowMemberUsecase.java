package com.example.fastcampusmysql.application.usecase;

import com.example.fastcampusmysql.domain.member.dto.MemberDTO;
import com.example.fastcampusmysql.domain.follow.service.FollowWriteService;
import com.example.fastcampusmysql.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateFollowMemberUsecase {
    private final MemberReadService memberReadService;
    private final FollowWriteService followWriteService;
    public void execute(Long fromMemberId, Long toMemberId) {
        MemberDTO fromMember = memberReadService.getMember(fromMemberId);
        MemberDTO toMember = memberReadService.getMember(toMemberId);
        followWriteService.create(fromMember, toMember);
    }
}
