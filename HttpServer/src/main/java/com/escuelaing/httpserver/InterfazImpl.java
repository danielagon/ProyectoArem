/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escuelaing.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author danielagonzalez
 */
@Service
public class InterfazImpl implements Interfaz{
    
    @Override
    public String getValue(String number){
        URL urlApp = null;
        String datos = "",input = null;
        try{
            urlApp = new URL("https://agile-springs-99176.herokuapp.com/cuadrado/"+number);
        }catch (MalformedURLException e){
            Logger.getLogger(InterfazImpl.class.getName()).log(Level.SEVERE,null,e);
        }
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(urlApp.openStream()));
            while ((input = br.readLine()) != null){
                datos+=input;
            }
        } catch (IOException ex) {
            Logger.getLogger(InterfazImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }
}