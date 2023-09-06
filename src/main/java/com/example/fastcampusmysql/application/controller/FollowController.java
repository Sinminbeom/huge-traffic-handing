package com.example.fastcampusmysql.application.controller;

import com.example.fastcampusmysql.application.usecase.CreateFollowMemberUsecase;
import com.example.fastcampusmysql.application.usecase.GetFollowMemberUsecase;
import com.example.fastcampusmysql.domain.member.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {
    private final CreateFollowMemberUsecase createFollowMemberUsecase;
    private final GetFollowMemberUsecase getFollowMemberUsecase;
    @PostMapping("/{fromId}/{toId}")
    public void register(@PathVariable Long fromId, @PathVariable Long toId) {
        createFollowMemberUsecase.execute(fromId, toId);
    }
    @GetMapping("/members/{fromId}")
    public List<MemberDTO> register(@PathVariable Long fromId) {
        return getFollowMemberUsecase.execute(fromId);
    }
}
