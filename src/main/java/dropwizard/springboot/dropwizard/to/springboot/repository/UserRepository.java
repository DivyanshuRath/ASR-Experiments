package dropwizard.springboot.dropwizard.to.springboot.repository;

import dropwizard.springboot.dropwizard.to.springboot.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
