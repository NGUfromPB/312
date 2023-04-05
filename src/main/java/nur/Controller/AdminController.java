package nur.Controller;

import nur.models.User;
import nur.service.RoleServ;
import nur.service.UserServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServ userServ;
    private final RoleServ roleServ;

    @Autowired
    public AdminController(UserServ userServ, RoleServ roleServ) {
        this.roleServ=roleServ;
        this.userServ = userServ;
    }

    @GetMapping("/")
    public String users(Model model) {
        model.addAttribute("users", userServ.getAllUsers());
        return "users";
    }

    @GetMapping("/{id}")
    public String getUser (@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userServ.findById(id));
        return "user";
    }

    @GetMapping("/new")
    public String addUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("listRoles",roleServ.getAllRoles());
        return "new";
    }

    @PostMapping("/new")
    public String add(@RequestParam("role") ArrayList<Long> roles,
                      @ModelAttribute("user") User user) {
        user.setRoles(roleServ.getRolesById(roles));
        userServ.addUser(user);
        return "redirect:/admin/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userServ.removeUser(id);
        return "redirect:/admin/";
    }

    @GetMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") long id, Model model) {
        model.addAttribute(userServ.findById(id));
        model.addAttribute("listRoles",roleServ.getAllRoles());
        return "edit";
    }

    @PatchMapping("/edit/{id}")
    public String update(@RequestParam("role")ArrayList<Long> roles,
                         User user,
                         @PathVariable("id") long id) {
        user.setRoles(roleServ.getRolesById(roles));
        user.setUserId(id);
        userServ.addUser(user);
        return "redirect:/admin/";
    }
}
