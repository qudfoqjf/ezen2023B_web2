package ezenweb.config;

import ezenweb.controller.ChatSocket;
import jakarta.websocket.WebSocketContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.reactive.GraphQlWebFluxAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.net.http.WebSocket;

@Configuration      //스프링 컨테이너에 빈 등록
@EnableWebSocket    // 웹소켓 매핑
public class WebSocketMapping implements WebSocketConfigurer {
    //* 스프링 버전에 따라 라이브러리 이름 다를 수 있음.
    //* spring2.x : WebSocketConfigurer spring3.x:Web
    @Autowired private ChatSocket socket;
    @Override   //1. 웹소켓 매핑 등록
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //- ws로 요청된 url들을 어디로 핸들러 할껀지 설정
        registry.addHandler(socket,"/chat");
    }




    //1. 웹소켓 매핑 등록






}
