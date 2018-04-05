/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escuelaing.httpserver;

/**
 *
 * @author 2109734
 */
public class Cuadrado {

    public static void main(String[] args){

        ClassLoader classLoader = Cuadrado.class.getClassLoader();
        
        try {
            Class aClass = classLoader.loadClass("com.escuelaing.Cuadrado");
            System.out.println("aClass.getName() = " + aClass.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
