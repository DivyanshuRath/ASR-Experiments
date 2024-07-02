package dropwizard.springboot.dropwizardToSpringboot.service;

import dropwizard.springboot.dropwizardToSpringboot.dto.UserDto;
import dropwizard.springboot.dropwizardToSpringboot.entity.UserEntity;

import java.util.List;

public interface UserCrudService {
    public UserEntity saveUser(UserDto userDto);
    public UserEntity updateUser(Integer userId, UserDto userDto);
    public UserEntity getUser(Integer userId);
    public List<UserEntity> getAllUsers();
    public UserEntity deleteUser(Integer userId);
}
