package dropwizard.springboot.dropwizard.to.springboot.service;

import dropwizard.springboot.dropwizard.to.springboot.dto.UserDto;
import dropwizard.springboot.dropwizard.to.springboot.entity.UserEntity;
import dropwizard.springboot.dropwizard.to.springboot.repository.UserRepository;
import dropwizard.springboot.dropwizard.to.springboot.service.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCrudServiceImpl implements UserCrudService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserEntity saveUser(UserDto userDto) {
        UserEntity user = new UserEntity();
        user.setName(userDto.getName());
        user.setEmail(userDto.getMailId());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return user;
    }

    @Override
    public UserEntity updateUser(Integer userId, UserDto userDto) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
        if(!optionalUserEntity.isPresent())
            throw new RuntimeException("User does not exist");
        UserEntity userEntity = optionalUserEntity.get();
        if(userDto.getName() != null){
            userEntity.setName(userDto.getName());
        }
        if(userDto.getMailId() != null){
            userEntity.setEmail(userDto.getMailId());
        }
        if(userDto.getPassword() != null){
            userEntity.setPassword(userDto.getPassword());
        }
        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity getUser(Integer userId) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
        if(!optionalUserEntity.isPresent()){
            throw new RuntimeException("User not found");
        }
        return optionalUserEntity.get();
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity deleteUser(Integer userId) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
        if(!optionalUserEntity.isPresent())
            throw new RuntimeException("User not found");

        userRepository.delete(optionalUserEntity.get());
        return optionalUserEntity.get();
    }
}
