package nur.dao;

import nur.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Long> {
    @Query("Select u from User u left join fetch u.roles where u.username=:username")
    User findByUsername(@Param("username")String username);
}
