package com.mercury.SpringBootRESTDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
/***
 *
 * Created by 80071482 on 2018/2/23.
 * Author Zhangboyi (Bony)
 ***/


@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}