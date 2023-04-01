package mochegov.transact.repositories;

import mochegov.transact.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByUserName(String userName);
}
