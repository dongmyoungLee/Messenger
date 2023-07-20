package com.example.chattest.controller;

import com.example.chattest.dto.ChatRoom;
import com.example.chattest.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatService chatService;
    @GetMapping("/chat1")
    public String rooms2(Model model) {
        return "/chat/room";
    }
    @GetMapping("/chat2")
    public String rooms3(Model model) {
        return "/room/roomdetail";
    }

    // 채팅 리스트 화면
    @GetMapping("/chats")
    public String rooms(Model model) {
        return "/kakao/chats";
    }

    @GetMapping("/chat")
    public String chat(Model model) {
        return "/kakao/chat";
    }

    @GetMapping("/friends")
    public String friends(Model model) {

        return "/kakao/friends";
    }

    @GetMapping("/find")
    public String find(Model model) {
        return "/kakao/find";
    }

    @GetMapping("/more")
    public String more(Model model) {
        return "/kakao/more";
    }

    @GetMapping("/settings")
    public String settings(Model model) {
        return "/kakao/settings";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "/kakao/index";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        return "/kakao/signup";
    }
    @GetMapping("/openChat")
    public String openChat(Model model) {
        return "/kakao/openChat";
    }

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatService.findAllRoom();
    }

    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String name) {
        return chatService.createRoom(name);
    }
    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail1(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "/kakao/openChat";
    }
    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatService.findById(roomId);
    }

}