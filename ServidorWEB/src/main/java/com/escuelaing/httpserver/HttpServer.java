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

/**
 *
 * @author danielagonzalez
 */
public class HttpServer implements Runnable{
    
    private ServerSocket clientSocket = null;
    private ComponenteInter comp;

    public HttpServer(ServerSocket clientSocket, ComponenteInter comp) {
        this.clientSocket = clientSocket;
        this.comp = comp;
    }
    
    @Override
    public void run(){
        try {
            while (true){
                Socket socket = null;
                socket = clientSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                String inputLine, outputLine,datos;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received: " + inputLine);
                    if (inputLine.contains("GET")) {
                        String input = inputLine.split(" ")[1];
                        if (input.equals("/") || input.equals("/index.html")){
                            URL file = HttpServer.class.getResource("/index.html");
                            datos = "";
                            BufferedReader br = new BufferedReader(new InputStreamReader(file.openStream()));
                            String texto;
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
                        }else if (input.contains("/response")){
                            ClassLoader classLoader = HttpServer.class.getClassLoader();
                            Class aClass = null;
                            try {
                                aClass = classLoader.loadClass("com.escuelaing.httpserver."+input.split("/")[2]);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            outputLine = "HTTP/1.1 200 OK\r\n"
                                    + "Content-Type: " 
                                    + "text/html"
                                    + "\r\n\r\n"
                                    +comp.getValue(String.valueOf(input.split("/")[3]),aClass); 
                            out.println(outputLine);
                        }
                    }
                    if (!in.ready()){
                        break;
                    }
                    if (inputLine.equals("")){
                        break;
                    }
                } 
                out.close();
                in.close();
                socket.close();
            }
        }catch (IOException e){
            Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
