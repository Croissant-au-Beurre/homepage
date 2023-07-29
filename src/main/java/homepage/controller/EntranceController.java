package homepage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EntranceController {

	@RequestMapping("/entrance")
    public String hello() {
        return "/entrance";
    }
}
