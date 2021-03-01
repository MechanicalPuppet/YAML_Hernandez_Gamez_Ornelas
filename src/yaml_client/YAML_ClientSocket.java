/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yaml_client;

import java.io.IOException;
import java.net.Socket;
import yaml_client.gui.FPerson;

/**
 *
 * @author Team 03
 */
public class YAML_ClientSocket {

    private YAMLClientThread cThread;

    public void start() {
        // TODO code application logic here
        try {
            Socket socket = new Socket("localhost", 666);
            cThread = new YAMLClientThread(socket);
            Thread thread = new Thread(cThread);
            thread.start();

        } catch (IOException x) {
            System.out.println(x.getMessage() + "Falló en el YAML_Client al momento de la creación del Socket e Hilo.");
        }
    }

    public YAMLClientThread getThread() {
        return this.cThread;
    }
}
