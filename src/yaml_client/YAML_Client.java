/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yaml_client;

import Person.Person;
import java.io.IOException;
import java.net.Socket;
import yaml_client.gui.FPerson;

/**
 *
 * @author Team 03
 */
public class YAML_Client {
    


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        YAML_ClientSocket client = new  YAML_ClientSocket();
        client.start();
        new FPerson(client.getThread()).setVisible(true);
    }
    
   
    
    
    
}
