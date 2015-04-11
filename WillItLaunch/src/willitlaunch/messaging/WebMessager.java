/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package willitlaunch.messaging;

import java.net.URI;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

/**
 *
 * @author Tom
 */
public class WebMessager extends WebSocketClient{

    public MessageManager msg;
    
    public WebMessager(URI serverURI, MessageManager msg) {
        super(serverURI, new Draft_17());
        this.msg = msg;
        WebSocketImpl.DEBUG = true;
    }

            @Override
            public void onOpen(ServerHandshake sh) {
                System.out.println("OPEN FOR BUZINNNEZZ"); 
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
            @Override
            public void onMessage(String string) {
                System.out.println("---- begin ----"); 
                System.out.println(string);
                msg.convertMessageToData(string);
                System.out.println("--- end ----"); 
            } 
            
            @Override
            public void onClose(int code, String string, boolean bln) {
                System.out.println("WE IZ CLOZED NOW as " + code); 
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
            @Override
            public void onError(Exception excptn) {
                System.out.println("IZZ ALL GON RONG AYE"); 
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        
}

