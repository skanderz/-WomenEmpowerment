package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.enume.ERole;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    Optional<Role> findByName(ERole name);

    @Query("SELECT r FROM Role r WHERE r.name='ROLE_ADMIN' ")
    public Role getRoleAdmin();
    @Query("SELECT r FROM Role r WHERE r.name='ROLE_USER' ")
    public Role getRoleClient();
}
