/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yaml_client;

import Parser.Parser;
import Person.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Observable;

/**
 *
 * @author Team 03
 */
public class YAMLClientThread extends Observable implements Runnable {

    private Socket socket;

    public YAMLClientThread(Socket socket) {
        this.socket = socket;
    }

    public void sendRequest(Person person) {

        ObjectMapper om = new ObjectMapper(new YAMLFactory());

        try {

            File fileToSend = new File("person.yaml");
            om.writeValue(fileToSend, person);

            byte[] buffer = new byte[1024];

            buffer = Files.readAllBytes(fileToSend.toPath());

       
            socket.getOutputStream().write(buffer.length);
            socket.getOutputStream().write(buffer);
            

        } catch (IOException x) {
            System.out.println(x.getMessage() + "Problem on SendRequest");
        }

    }

    public Person recieveRequest() {
        Person person = null;
        try {
         

            int length = socket.getInputStream().read();
            byte[] message = new byte[length];

            if (length > 0) {
                socket.getInputStream().read(message, 0, message.length);
            }

            Parser parser = new Parser(message);

            person = parser.parsear();

        } catch (IOException x) {
            System.out.println(x.getMessage() + "Problems on RecieveRequest");
        }

        return person;
    }

    @Override
    public void run() {
        System.out.println("I made it!!");
        Person person;
        do {
            person = recieveRequest();
            if (person != null) {
                this.setChanged();
                this.notifyObservers(person);
                this.clearChanged();
            }
        } while (person != null);

    }

}
