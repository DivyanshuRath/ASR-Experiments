package dropwizard.springboot.dropwizardToSpringboot.service;

import dropwizard.springboot.dropwizardToSpringboot.dto.UserDto;
import dropwizard.springboot.dropwizardToSpringboot.entity.UserEntity;
import dropwizard.springboot.dropwizardToSpringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class UserCrudServiceImpl implements UserCrudService{

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

    @Override
    public String getUsersFromDownstreamApi() {
        final String uri = "https://jsonplaceholder.typicode.com/users";

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);
    }


}
