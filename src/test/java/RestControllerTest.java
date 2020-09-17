import com.dvt.model.Message;
import com.dvt.service.RabbitMQSender;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

public class RestControllerTest extends DVTWhatsAppBusinessAPIDemoTest{

    @MockBean
    private RabbitMQSender rabbitMQSender;

    @Override
    @Before
    public void setUp(){
        super.setUp();
    }

    @Test
    public void sendMessage() throws Exception {
        String uri = "/dvt-rabbitmq/producer";
        String sendMessage = "Test Send";
        String messageCode = "Test Code";
        uri = uri + "?message=" + sendMessage + "&code=" +messageCode;

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        String expected = "Message sent to the RabbitMQ DVT Successfully";
        assertEquals(expected, content);
    }

    @Test
    public void testBroadcast() {
        assertThatCode(() -> super.rabbitMQSender.send(new Message("Test", "1234"))).doesNotThrowAnyException();

        Mockito.verify(this.rabbitTemplate).convertAndSend(eq(null), eq(null), any(Message.class));
    }
}
