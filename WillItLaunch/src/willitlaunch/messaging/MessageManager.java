package willitlaunch.messaging;

import java.net.URI;
import java.net.URISyntaxException;

public class MessageManager { 
    
    public void test() {
        try {
            // open websocket
            final WebSocketClientEndPoint clientEndPoint = new WebSocketClientEndPoint(new URI("wss://real.okcoin.cn:10440/websocket/okcoinapi"));

            // add listener
            clientEndPoint.addMessageHandler(new WebSocketClientEndPoint.MessageHandler() {
                public void handleMessage(String message) {
                    System.out.println(message);
                }
            });

            // send message to websocket
            clientEndPoint.sendMessage("{'event':'addChannel','channel':'ok_btccny_ticker'}");

            // wait 5 seconds for messages from websocket
            Thread.sleep(5000);

        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
    }
}

