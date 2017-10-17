package demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@TransactionConfiguration(defaultRollback = true)
@WebAppConfiguration
public class GreetingRestControllerTest {


    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }


    @Test
    public void testGetGreeting() throws Exception {

        mockMvc.perform(get("/hi")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(mvcResult -> {
                    String responseJson = mvcResult.getResponse().getContentAsString();
                    Greeting greeting = new ObjectMapper().reader(Greeting.class)
                            .readValue(responseJson);
                    assertEquals(greeting.getGreeting(), "hi!");
                });

        mockMvc.perform(get("/hi")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.greeting", is("hi!")));
    }

    @Test
    public void testGetGreetingWithName() throws Exception {

        mockMvc.perform(get("/hi").param("name", "Jakub")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.greeting", containsString("hi Jakub!")));
    }

    @Test
    public void testGetListOfCharacters() throws Exception {
        String text = "some text";
        mockMvc.perform(get("/chars").param("text", text)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(text.length())))
                .andExpect(result -> {
                            String[] res = new ObjectMapper().reader(String[].class)
                                    .readValue(result.getResponse().getContentAsString());
                            assertArrayEquals(res, text.split(""));
                        }
                );
    }


    @After
    public void tearDown() throws Exception {

    }
}