package demo;


import org.fluentlenium.adapter.FluentTest;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;

import java.util.concurrent.TimeUnit;


public class ListPageTest extends FluentTest {

    public WebDriver webDriver = new ChromeDriver();

    @Before
    public void before() throws Exception {
        SpringApplication.run(Application.class);
    }

    @Override
    public WebDriver getDefaultDriver() {
        return this.webDriver;
    }

    @Test
    public void listPageShouldShowNamesInDatabase() {

        goTo("http://localhost:8080/index.html");
        await().atMost(5, TimeUnit.SECONDS).until(".hello-message").containsText("hi Bob!");

        /*fill("#sb_form_q").with("FluentLenium");
        submit("#sb_form_go");
        assertThat(title(), containsString("FluentLenium"));*/

    }
}

/*

class AjaxPage extends FluentPage{

}*/
