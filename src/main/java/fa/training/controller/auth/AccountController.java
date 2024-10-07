package fa.training.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {
    @GetMapping("login")
    public String logIn(){
        return "auth/sign-in";
    }

    @GetMapping("/error/access-denied")
    public String accessDenied() {
        return "error/access-denied";
    }

//    @GetMapping("/error/not-found")
//    public String notFound() {
//        return "error/not-found";
//    }
}
