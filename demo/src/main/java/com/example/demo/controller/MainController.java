package com.example.demo.controller;

// RestController는 일반 Controller와 달리 JSON 형식으로 데이터를 내보냄.
// 컨트롤러는 브라우저에서 들어오는 신호를 가장 먼저 맞이한다.
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class MainController {

    @GetMapping("/api/hello")
    public String hello() {
        return "hello from spring";
    }
}
 