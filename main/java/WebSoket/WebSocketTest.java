package WebSoket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vdurmont.emoji.EmojiParser;
import daomain.Message;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import service.MessageService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/helloWebSocket", configurator = HttpSessionConfigurator.class)
public class WebSocketTest {
    private static Map<String, Session> users = new ConcurrentHashMap<String, Session>();
    private MessageService messageService;
    private Map<String, Object> UserProperties;
    private String name;
    private String id;
    private Gson gson=new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm").create();
    @OnOpen
    public void onopen(Session session, EndpointConfig config) {
        UserProperties = config.getUserProperties();
        ServletContext servletcontext=(ServletContext)UserProperties.get("ServletContext");
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(servletcontext);
        messageService= (MessageService) wac.getBean("MessageService");
        Map<String, List<String>> props = (Map) UserProperties.get("HandshakeRequestParameters");
        id=props.get("id").get(0);
        name=props.get("name").get(0);
        users.put(id, session);
        System.out.println(name+"进入");
    }

    @OnClose
    public void onclose(Session session) {
        users.remove(id);
    }
    @OnMessage
    public void onsend(String msg,Session session) throws IOException {
        Message message=gson.fromJson(msg, Message.class);
        message.setForm(id);
        message.setName(name);
        message.setDate(new Date());
        message.setMessage(EmojiParser.parseToHtmlDecimal(message.getMessage()));
        messageService.save(message);
        String messagestr=gson.toJson(message);
        System.out.println(messagestr);
        if (users.containsKey(message.getTo()))users.get(message.getTo()).getAsyncRemote().sendText(messagestr);
        session.getAsyncRemote().sendText(messagestr);
    }
}
