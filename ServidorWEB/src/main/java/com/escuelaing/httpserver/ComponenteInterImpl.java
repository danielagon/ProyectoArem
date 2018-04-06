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

/**
 *
 * @author danielagonzalez
 */
public class ComponenteInterImpl implements ComponenteInter{
    
    @Override
    public String getValue(String number, Class clase){
        URL urlApp = null;
        String datos = "",input = null;
        Operation operation=null;
        try {
            operation = (Operation) Class.forName(clase.getName()).newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComponenteInterImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ComponenteInterImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ComponenteInterImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            urlApp= operation.getUrl(number);
            //urlApp = new URL("https://agile-springs-99176.herokuapp.com/"+nameClass+"/"+number);
        
        
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(urlApp.openStream()));
            while ((input = br.readLine()) != null){
                datos+=input;
            }
        } catch (IOException ex) {
            Logger.getLogger(ComponenteInterImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }
}
