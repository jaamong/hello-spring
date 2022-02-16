package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // ========================= MVC & Template Engine =============================
    // Controller에서 리턴 값으로 String을 반환하면 뷰 리졸버(viewResolver)가 화면을 찾아서 처리

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }


    // ============================= API =================================
    // @ResponseBody를 사용하면 뷰 리졸버(viewResolver)를 사용하지 않음
    // 대신 HTTP의 BODY에 문자 내용을 직접 변환(HTML BODY TAG를 말하는 것이 아님)
    // 뷰 리졸버(viewResolver)대신 HttpMessageConvert가 동작
        // 기본 문자처리 : StringHttpMessageConverter
        // 기본 객체처리 : MappingJackson2HttpMessageConverter
        // byte 처리 등등..

    // @ResponseBody 문자 반환(StringHttpMessageConverter)
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name + " :)";
    }

    // @ResponseBody 객체 반환 : 객체 반환 시 객체가 JSON으로 변환(MappingJackson2HttpMessageConverter)
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // {"name" : name}
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
