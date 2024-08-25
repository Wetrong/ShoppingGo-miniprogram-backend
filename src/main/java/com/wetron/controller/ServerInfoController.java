package com.wetron.controller;

import org.apache.catalina.util.ServerInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
/**
 * @description:TODO
 * @author: Wetron
 * @create: 2024/4/29 7:58
 */
@RestController
public class ServerInfoController {
    @GetMapping("/server-info")
    public String getServerInfo(HttpServletRequest request) {
        String serverInfo = request.getServletContext().getServerInfo();
        System.out.println(serverInfo);
        return "Server Info: " + ServerInfo.getServerInfo();
    }
}
