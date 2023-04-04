package nur.init;

import nur.models.Role;
import nur.models.User;
import nur.service.RoleServImp;
import nur.service.UserServImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Userinit {

    @Autowired
    private RoleServImp roleService;
    private UserServImpl userService;

    public Userinit(UserServImpl userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        Role role1 = new Role("ADMIN");
        Role role2 = new Role("USER");

        roleService.addRole(role1);
        roleService.addRole(role2);

        Set<Role> roleAdmin = new HashSet<>();
        Set<Role> roleUser = new HashSet<>();

        roleAdmin.add(role1);
        roleUser.add(role2);

        User user1 = new User("admin", "$2y$10$0W.SJalI2GNpGA3hVtVWPeypIJZgB0wDLWaypEI5dKptpQxvN6akm", roleAdmin,"admin@gmail.com");
        User user2 = new User("user", "$2y$10$oOwaD9k6nwTiu3FKq8.CqeZhW/JvAc7VDUBIDJqNdplBlcPsilTRK", roleUser,"user@gmail.com");

        userService.addUser(user1);
        userService.addUser(user2);
    }
}