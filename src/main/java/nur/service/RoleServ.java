package nur.service;

import nur.models.Role;

import java.util.List;
import java.util.Set;

public interface RoleServ {
    void addRole(Role role);
    Role getRoleById(long id);
    Set<Role> getRolesById(List<Long> roles);
    Set<Role> getAllRoles();
}
