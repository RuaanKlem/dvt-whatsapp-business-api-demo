import com.dvt.DVTWhatsAppBusinessAPIDemo;
import com.dvt.service.RabbitMQSender;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DVTWhatsAppBusinessAPIDemo.class)
@WebAppConfiguration
public abstract class DVTWhatsAppBusinessAPIDemoTest {
    protected RabbitMQSender rabbitMQSender;
    protected RabbitTemplate rabbitTemplate;
    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.rabbitTemplate = Mockito.mock(RabbitTemplate.class);
        this.rabbitMQSender = new RabbitMQSender(this.rabbitTemplate);
    }
}
