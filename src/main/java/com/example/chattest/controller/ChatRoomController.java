package com.example.chattest.controller;

import com.example.chattest.dto.ChatRoom;
import com.example.chattest.service.ChatService;
import jakarta.servlet.http.HttpSession;
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

    // 채팅 리스트 화면
    @GetMapping("/chats")
    public String rooms(Model model, HttpSession session) {
        if (session.getAttribute("userName") == null) {
            return "/kakao/index";
        }
        return "/kakao/chats";
    }

    @GetMapping("/chat")
    public String chat(Model model, HttpSession session
    ) {
        if (session.getAttribute("userName") == null) {
            return "/kakao/index";
        }
        return "/kakao/chat";
    }

    @GetMapping("/friends")
    public String friends(Model model, HttpSession session) {
        // 세션에서 user_seq 값을 가져와서 userName 변수에 저장합니다.
        Long userSeq = (Long) session.getAttribute("user_seq");

        // 세션에 user_seq 값이 없으면 로그인 페이지로 리다이렉트합니다.
        if (userSeq == null) {
            return "redirect:/kakao/index";
        }

        // 세션에 user_seq 값이 있으면 해당 값과 뷰를 반환합니다.
        model.addAttribute("userSeq", userSeq);

        return "kakao/friends";
    }

    @GetMapping("/find")
    public String find(Model model, HttpSession session) {
        if (session.getAttribute("userName") == null) {
            return "/kakao/index";
        }
        return "/kakao/find";
    }

    @GetMapping("/more")
    public String more(Model model, HttpSession session) {
        if (session.getAttribute("userName") == null) {
            return "/kakao/index";
        }
        return "/kakao/more";
    }

    @GetMapping("/settings")
    public String settings(Model model, HttpSession session) {
        if (session.getAttribute("userName") == null) {
            return "/kakao/index";
        }
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
    public String openChat(Model model, HttpSession session) {
        if (session.getAttribute("userName") == null) {
            return "/kakao/index";
        }
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