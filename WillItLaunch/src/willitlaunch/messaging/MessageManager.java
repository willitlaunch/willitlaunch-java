package willitlaunch.messaging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONObject;

public class MessageManager { 
    WebSocketClient wsc;
    Socket socket;
    
    public final String url = "ws://intense-woodland-5574.herokuapp.com/ws";
    
    
    public void listen()
    {
        URI uri;
        try { uri = new URI(url); } catch (Exception e) { return; }      
        wsc = new WebMessager(uri, this);
        wsc.connect();
    }
    
    public void convertMessageToData(String message)
    {
        // Determin if the message is the initial message or an update
        
        
    }
    
    public void createControlsFromMessage(String message)
    {
        
    }
    
    public void updateControlsFromMessage(String message)
    {
        JSONObject obj = new JSONObject(message);
        JSONArray widgetList = obj.getJSONArray("outputWidgets");
        for(int i=0;i<widgetList.length();i++)
        {
            JSONObject widget = widgetList.getJSONObject(i);
            
            // Need to search for the matching widget
            
            
            
        }
    }
}

