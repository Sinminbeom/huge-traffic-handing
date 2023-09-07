package com.example.fastcampusmysql.application.usecase;

import com.example.fastcampusmysql.domain.follow.entity.Follow;
import com.example.fastcampusmysql.domain.follow.service.FollowReadService;
import com.example.fastcampusmysql.domain.member.dto.MemberDTO;
import com.example.fastcampusmysql.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetFollowMemberUsecase {
    private final MemberReadService memberReadService;
    private final FollowReadService followReadService;
    public List<MemberDTO> execute(Long memberId) {
        /*
            fromMemberId = memberId -> Follow list
         */
        List<Follow> followings = followReadService.getFollowings(memberId);
        List<Long> ids = followings.stream().map(f -> f.getToMemberId()).toList();
        return memberReadService.getMembers(ids);
    }
}
