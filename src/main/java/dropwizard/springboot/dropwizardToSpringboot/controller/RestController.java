package dropwizard.springboot.dropwizardToSpringboot.controller;

import dropwizard.springboot.dropwizardToSpringboot.dto.UserDto;
import dropwizard.springboot.dropwizardToSpringboot.entity.UserEntity;
import dropwizard.springboot.dropwizardToSpringboot.service.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private UserCrudService userCrudService;

    /**
     * Endpoint to greet user
     * @param name, i.e. name of the user. If empty then default value of Stranger will be returned
     * @return greeting message
     */
    @GetMapping("/hello-world")
    public String displayName(@RequestParam(name="name", required = false, defaultValue = "Stranger") String name){
        return "Hello! "+name;
    }

    /**
     * Endpoint to create/save a user
     * @param userDto, takes userDto fields required to create and save user
     * @return returns the entity of saved user
     */
    @PostMapping("/saveUser")
    public UserEntity saveUser(@RequestBody UserDto userDto){
        return userCrudService.saveUser(userDto);
    }

    /**
     * Endpoint to update an existing user from its userId field
     * @param userId, required field to identify the user which needs to be updated
     * @param userDto, the fields which need to be updated for an id
     * @return returns the updated userEntity with updated fields
     */
    @PutMapping("/updateUser")
    public UserEntity updateUser(@RequestParam(name="userId", required = true)Integer userId,
                                 @RequestBody UserDto userDto){
        return userCrudService.updateUser(userId, userDto);
    }

    /**
     * Endpoint to get a particular user from userId
     * @param userId, required field to identify the user which needs to be fetched
     * @return returns the fetched userEntity
     */
    @GetMapping("/getUser")
    public UserEntity getUser(@RequestParam(name="userId", required = true)Integer userId){
        return userCrudService.getUser(userId);
    }

    /**
     * Endpoint to get all existing users
     * @return list of userEntity
     */
    @GetMapping("/getAllUsers")
    public List<UserEntity> getAllUsers(){
        return userCrudService.getAllUsers();
    }

    /**
     * Endpoint to delete user
     * @param userId, required field to identify the user which needs to be deleted
     * @return returns deleted userEntity
     */
    @DeleteMapping("/deleteUser")
    public UserEntity deleteUser(@RequestParam(name = "userId", required = true)Integer userId){
        return userCrudService.deleteUser(userId);
    }

    /**
     * Endpoint to demonstrate downstream api i.e. making call to an external api
     * @return return users listed on the provided uri
     */
    @GetMapping("/downstreamGetUsers")
    public String getEmployees(){
        return userCrudService.getUsersFromDownstreamApi();
    }
}
