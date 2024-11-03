package rw.reservation.hotelbooking.repository;

import rw.reservation.hotelbooking.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}