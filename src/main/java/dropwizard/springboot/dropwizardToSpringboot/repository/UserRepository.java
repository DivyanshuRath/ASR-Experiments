package dropwizard.springboot.dropwizardToSpringboot.repository;

import dropwizard.springboot.dropwizardToSpringboot.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
