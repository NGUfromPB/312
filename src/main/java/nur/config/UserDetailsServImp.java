package nur.config;

import nur.dao.UserDao;
import nur.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServImp implements UserDetailsService {
    UserDao userDao;
    @Autowired
    public UserDetailsServImp(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findByUsername(s);
        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }
        UserDetails user1 = new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),user.getRoles());
        return user1;
    }
}
