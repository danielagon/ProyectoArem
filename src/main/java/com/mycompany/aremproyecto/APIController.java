/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aremproyecto;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author Guzman
 */
@RestController
@RequestMapping(value = "/cuadrado")
public class APIController {
    

    @RequestMapping(path = "/{idmesa}", method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetIdMesaOrdersAPI(@PathVariable int idmesa) {
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(Operation.Square(idmesa), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(APIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error, no se encontro la mesa", HttpStatus.NOT_FOUND);
        }
    }

}
