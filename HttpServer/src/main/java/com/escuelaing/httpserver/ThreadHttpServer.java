/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escuelaing.httpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author danielagonzalez
 */
public class ThreadHttpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try { 
            serverSocket = new ServerSocket(8080);
            ExecutorService executor = Executors.newFixedThreadPool(1); 
            ApplicationContext aplicacion = new ClassPathXmlApplicationContext("applicationContext.xml");
            ComponenteInter comp = aplicacion.getBean(ComponenteInterImpl.class);
            executor.execute(new HttpServer(serverSocket,comp));
        } catch (IOException e) {
            System.err.println("Could not listen on port.");
            System.exit(1);
        }
    }
}
