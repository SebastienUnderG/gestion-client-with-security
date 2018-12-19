package formation.fitec.gestionclient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.java.formation.GestionClientApplication;
import com.java.formation.client.entity.Client;
import com.java.formation.client.service.ClientService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = GestionClientApplication.class)
@AutoConfigureMockMvc
public class ClientControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClientService clientService;
    
    public static final String GLOBAL_EXPECTED="{\"lastName\":\"DUPONT\",\"firstName\":\"Loic\",\"category\":\"parent\",\"birthDay\":\"12-18-2017\",\"email-address\":\"toto@gmail.com\"}";

    @Test
    public void getClientByIdTest() throws Exception {

        Mockito.when(clientService.find(1l)).thenReturn(getClient());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/client/1").accept(MediaType.APPLICATION_JSON).header("Authorization", "Basic YWRtaW46YWRtaW4=");

        MvcResult result = mvc.perform(requestBuilder).andReturn();
        
        System.out.println("STEP 000" + result.getResponse().getContentAsString());

        JSONAssert.assertEquals(GLOBAL_EXPECTED, result.getResponse().getContentAsString(), false);
    }
    
    
    
    
    
    
    
    

    public static Client getClient() throws ParseException {
        Client client = new Client();
        client.setCategorie("parent");
        client.setEmail("toto@gmail.com");
        client.setNom("DUPONT");
        client.setPrenom("Loic");
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        client.setDateNaissance(format.parse("12-18-2017"));

        return client;
    }

}
