package com.gigaspaces.ps.remoteservice.client;

import com.gigaspaces.ps.remoteservice.common.CallInfo;
import com.gigaspaces.ps.remoteservice.common.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.UUID;

/**
 * User: jpletka
 * Date: 5/25/16
 * Time: 12:55 PM
 */
public class ClientApp {

    private static final String CONFIG_PATH="classpath:application-context.xml";

    @Autowired
    Service remoteService;

    public ClientApp(){

    }
    public void run(){
        System.out.println("Starting Tests");
        String callId = UUID.randomUUID().toString();
        CallInfo info = remoteService.startNewCall(callId);
        CallInfo other = remoteService.startNewCall(callId);
        assert(other.getHostId() == info.getHostId());
        boolean wasFound = remoteService.releaseCall(callId);
        assert(wasFound);
        System.out.println("Done and all tests passed");


    }

    public static void main(String args[]){
        ApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_PATH);
        ClientApp app = (ClientApp) context.getBean("clientApp");
        app.run();
    }
}
