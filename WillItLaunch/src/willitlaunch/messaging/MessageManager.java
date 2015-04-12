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
    public final String url2 = "ws://192.168.137.158:8080/ws";
    
    public MessageManager(FlightControlController fcc)
    {
        this.fcc = fcc;
    }
    
    public void listen()
    {
        URI uri;
        try { uri = new URI(url2); } catch (Exception e) { return; }      
        wsc = new WebMessager(uri, this);
        wsc.connect();
    }
    
    public void convertMessageToData(String message)
    {
        JSONObject obj = new JSONObject(message);

        isFirstMessage = false;
        updateOutputControlsFromMessage(obj);
        updateInputControlsFromMessage(obj);
        updateObjectiveListFromMessage(obj);
    }
    
    public void updateOutputControlsFromMessage(JSONObject obj)
    {
        if (!obj.keySet().contains("outputWidgets")) return;
        JSONArray widgetList = obj.getJSONArray("outputWidgets");
        for(int i=0;i<widgetList.length();i++)
        {
            JSONObject widget = widgetList.getJSONObject(i);
            int wid = (int)widget.get("Wid");
            int gid = (int)widget.get("Gid");
            int id = wid + gid*100;
            if (!fcc.outputGaugeMap.keySet().contains(id))
            {                                    
                GaugeBase gaugeBase = GaugeBase.createGauge(widget);
                if (gaugeBase != null) fcc.createGauge(gaugeBase);
            }
            fcc.updateGauge(wid+(gid*100), widget);
        }   
    }
    
    public void updateInputControlsFromMessage(JSONObject obj)
    {    
        if (!obj.keySet().contains("inputWidgets")) return;
        JSONArray widgetList = obj.getJSONArray("inputWidgets");
        for(int i=0;i<widgetList.length();i++)
        {
            JSONObject widget = widgetList.getJSONObject(i);
            ControlBase wid = ControlBase.createControl(widget);
            if (wid != null) {
                wid.updated.addListener(o -> sendUpdatedValue(wid));
                fcc.createControl(wid);
            }
        } 
    }
    
    public void updateObjectiveListFromMessage(JSONObject obj)
    {
    }
    
    
    public void sendUpdatedValue(ControlBase obj)
    {
        JSONObject json = new JSONObject();
        int wid = obj.id % 100;
        int gid = (int)Math.round(obj.id - wid / 100.0);
         
        json.append("Wid", wid);
        json.append("Gid", gid);
        json.append("Value", obj);
        wsc.send(json.toString());
    }
}

