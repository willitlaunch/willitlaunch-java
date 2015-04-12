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
import willitlaunch.gauges.GaugeBase;

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
            GaugeBase wid = GaugeBase.createControl(widget);
            if (wid != null) fcc.createGauge(wid);
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
            try
            {
            JSONObject widget = widgetList.getJSONObject(i);
            int wid = (int)widget.get("wid");
            int gid = (int)widget.get("gid");
            fcc.updateGauge(wid+(gid*100), widget);
            }
            catch(Exception e)
            {
                System.out.println("");
                      
            }
        }
    }
    
    public void sendUpdatedValue(ControlBase obj)
    {
        JSONObject json = new JSONObject();
        int wid = obj.id % 100;
        int gid = (int)Math.round(obj.id - wid / 100.0);
         
        json.append("wid", wid);
        json.append("gid", gid);
        json.append("value", obj);
        wsc.send(json.toString());
    }
}

