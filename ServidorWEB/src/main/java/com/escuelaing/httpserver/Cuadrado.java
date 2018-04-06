/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escuelaing.httpserver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2109734
 */
public class Cuadrado implements Operation{

    @Override
    public URL getUrl(String number) {
        URL url= null;
        try {
            url= new URL("https://agile-springs-99176.herokuapp.com/cuadrado/"+number);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Cuadrado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return url;
    }

    
}
