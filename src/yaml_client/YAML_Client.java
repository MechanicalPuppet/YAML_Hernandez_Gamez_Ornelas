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
 * @author Jbran
 */
public class YAML_Client {
    
    private static YAMLClientThread cThread;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        try{
        Socket socket = new Socket("localhost", 666);
        cThread = new YAMLClientThread(socket);
        Thread thread = new Thread(cThread);
        thread.start();
        
        new FPerson(cThread).setVisible(true);
        
        } catch(IOException x){
            System.out.println(x.getMessage() + "Falló en el YAML_Client al momento de la creación del Socket e Hilo.");
        }
    }
    
    public YAMLClientThread getThread(){
        return this.cThread;
    }
    
    
    
}
