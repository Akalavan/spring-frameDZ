package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.akalavan.controllers.RentController;
import ru.akalavan.objects.CarJpaRelease;
import ru.akalavan.objects.RentJpaRelease;
import service.mock.MockRentService;

import java.util.Date;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RentController.class, MockRentService.class})
public class RentControllerTest {

    @Autowired
    private RentController rentController;
    private MockMvc mockMvc;
    private final static String URL = "http://localhost:8080/rent/v1/rents";
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(rentController).build();
    }

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(get(URL))
                .andExpect(status().isOk());
    }

    @Test
    public void findById() throws Exception {
        mockMvc.perform(get(URL + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void create() throws Exception {
        RentJpaRelease rent = new RentJpaRelease(8, 2000, new Date(), new Date(), new CarJpaRelease());
        String requestJSON = mapper.writeValueAsString(rent);
        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJSON))
                    .andExpect(status().isCreated());
    }

    @Test
    public void update() throws Exception {
        RentJpaRelease rent = new RentJpaRelease(1, 9999, new Date(), new Date(), new CarJpaRelease());
        String requestJSON = mapper.writeValueAsString(rent);
        mockMvc.perform(put(URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(delete(URL + "/2"))
                .andExpect(status().isNoContent());
    }

}
