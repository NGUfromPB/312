package nur.Controller;

import nur.models.User;
import nur.service.UserServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;


@Controller
public class UserController {
    private final UserServ userServ;

    @Autowired()
    public UserController(UserServ userServ) {
        this.userServ = userServ;
    }
    @GetMapping("/user")
    public String getUser (Principal principal, Model model) {
        User user = userServ.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "userPage";
    }
    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }
}
