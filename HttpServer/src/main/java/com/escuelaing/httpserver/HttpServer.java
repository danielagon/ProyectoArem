/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escuelaing.httpserver;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

/**
 *
 * @author danielagonzalez
 */
public class HttpServer implements Runnable{
    
    private Socket clientSocket = null;
    private ComponenteInter comp;

    public HttpServer(Socket clientSocket, ComponenteInter comp) {
        this.clientSocket = clientSocket;
        this.comp = comp;
    }
    
    @Override
    public void run(){
        try {
            while (true){
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String inputLine, outputLine,texto,datos="";
                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.contains("GET")) {
                        inputLine = inputLine.split(" ")[1];
                        if (inputLine.contains("/") || inputLine.contains("/index.html")){
                            Resource file = new ClassPathXmlApplicationContext("applicationContext.xml").getResource("/index.html");
                            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
                            while ((texto = br.readLine()) != null){
                                datos+=texto;
                            }
                            br.close();
                            outputLine = "HTTP/1.1 200 OK\r\n"
                                    + "Content-Type: "
                                    + "text/html"
                                    +"\r\n\r\n"
                                    + datos;
                            out.println(outputLine);
                        }else if (inputLine.contains("cuadrado")){
                            outputLine = "HTTP/1.1 200 OK\r\n"
                                    + "Content-Type: " 
                                    + "text/html"
                                    + "\r\n\r\n"
                                    +comp.getValue(String.valueOf(inputLine.split("/")[2])); 
                            out.println(outputLine);
                        }
                    }
                    if (!in.ready()){
                        break;
                    }
                }
                out.close();
                in.close();
                clientSocket.close(); 
            }
        }catch (IOException e){
            Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
