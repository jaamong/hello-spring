package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // 127.0.0.1:8080 첫 화면
    public String home(){
        return "home"; //home.html
    }
}
