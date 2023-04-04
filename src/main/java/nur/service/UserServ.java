package nur.service;

import nur.models.User;

import java.util.List;

public interface UserServ {

    List<User> getAllUsers ();
    User findById(long id);
    void addUser(User user);
    void removeUser(long id);
    void updateUser(User user,long id);
    User findByUsername(String username);
    User passwordCoder(User user);
}