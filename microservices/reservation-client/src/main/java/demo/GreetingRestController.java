package demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class GreetingRestController {

    @RequestMapping(value = "/hi", produces = MediaType.APPLICATION_JSON_VALUE)
    public Greeting getHiGreeting(@RequestParam Optional<String> name) {
        return new Greeting("hi"+name.map(x -> " " + x + "!").orElse("!"));
    }

    @RequestMapping("/chars")
    public String[] getListOfCharacters(@RequestParam String text){
        return text.split("");
    }
}
