package nur.service;

import nur.dao.RoleDao;
import nur.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleServImp implements RoleServ{
    private final RoleDao roleDao;
    @Autowired
    public RoleServImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Set<Role> getAllRoles() {
        return new HashSet<>(roleDao.findAll());
    }

    @Override
    public void addRole(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role getRoleById(long id) {
        return roleDao.getById(id);
    }

    @Override
    public Set<Role> getRolesById(List<Long> roles) {
        return new HashSet<>(roleDao.findAllById(roles));

    }

}
