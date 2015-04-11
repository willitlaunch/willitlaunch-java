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
import willitlaunch.FlightControlController;
import willitlaunch.controls.ControlBase;

public class MessageManager { 
    FlightControlController fcc; 
    WebSocketClient wsc;
    Socket socket;
    boolean isFirstMessage = true;
    public final String url = "ws://intense-woodland-5574.herokuapp.com/ws";
    
    public MessageManager(FlightControlController fcc)
    {
        this.fcc = fcc;
    }
    
    public void listen()
    {
        URI uri;
        try { uri = new URI(url); } catch (Exception e) { return; }      
        wsc = new WebMessager(uri, this);
        wsc.connect();
    }
    
    public void convertMessageToData(String message)
    {
        JSONObject obj = new JSONObject(message);
        if (isFirstMessage)
        {
            isFirstMessage = false;
            createOutputControlsFromMessage(obj);
            createInputControlsFromMessage(obj);
            createObjectiveListFromMessage(obj);
        }
        else
        {
            updateOutputControlsFromMessage(obj);
        }
    }
    
    public void createOutputControlsFromMessage(JSONObject obj)
    {
        JSONArray widgetList = obj.getJSONArray("outputWidgets");
        for(int i=0;i<widgetList.length();i++)
        {
            JSONObject widget = widgetList.getJSONObject(i);
            ControlBase wid = ControlBase.createControl(widget);
            fcc.createGauge(wid);
        }    
    }
    
    public void createInputControlsFromMessage(JSONObject obj)
    {
    }
    
    public void createObjectiveListFromMessage(JSONObject obj)
    {
    }
    
    public void updateOutputControlsFromMessage(JSONObject obj)
    {
        JSONArray widgetList = obj.getJSONArray("outputWidgets");
        for(int i=0;i<widgetList.length();i++)
        {
            JSONObject widget = widgetList.getJSONObject(i);
            int id = (int)obj.get("wid") + (int)obj.get("gid")*100;
            fcc.updateGauge(id, widget);
        }
    }
}

