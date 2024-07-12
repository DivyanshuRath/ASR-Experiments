package dropwizard.springboot.dropwizard.to.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    /**
     * Endpoint to greet user
     * @param name, i.e. name of the user. If empty then default value of Stranger will be returned
     * @return greeting message
     */
    @GetMapping("/hello-world")
    public String displayName(@RequestParam(name="name", required = false, defaultValue = "Stranger") String name){
        return "Hello! "+name;
    }
}
