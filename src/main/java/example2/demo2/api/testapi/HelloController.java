package example2.demo2.api.testapi;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HelloController {

    @RequestMapping("hello")
    public String home(Model model) {
        model.addAttribute("data", "hello PU");
        return "hello";
    }
}
