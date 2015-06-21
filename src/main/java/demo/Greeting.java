package demo;

/**
 * Created by jlong on 6/21/15.
 */
public class Greeting {
    private String greeting;

    public Greeting(String s) {
        this.greeting=s;
    }

    public Greeting() {
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
