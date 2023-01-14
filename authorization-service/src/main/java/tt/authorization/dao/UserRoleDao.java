package tt.authorization.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tt.authorization.entity.UserRole;

import java.util.Optional;

@Repository
public interface UserRoleDao extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByName(String userRoleName);
}

