package nur.service;

import nur.dao.RoleDao;
import nur.dao.UserDao;
import nur.models.Role;
import nur.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServImpl implements UserServ{
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServImpl (UserDao userDao, RoleDao roleDao,PasswordEncoder  passwordEncoder) {
        this.userDao=userDao;
        this.roleDao=roleDao;
        this.passwordEncoder=passwordEncoder;
    }
    @Override
    public List<User> getAllUsers() {
        return  userDao.findAll();
    }

    @Override
    public User findById(long id) {

        return userDao.getById(id);
    }
    @Override
    public User passwordCoder(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }
    @Override
    @Transactional
    public void addUser(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional
    public void removeUser(long id) {
        userDao.deleteById(id);
    }

    @Override
    @Transactional
    public void updateUser(User user,long id) {
        user.setUserId(id);
        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    private Collection<? extends GrantedAuthority> ath(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());
    }
}
