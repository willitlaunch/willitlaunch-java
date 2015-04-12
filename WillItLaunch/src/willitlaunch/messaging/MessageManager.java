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
    //public final String url = "ws://intense-woodland-5574.herokuapp.com/ws";
    //public final String url = "ws://192.168.137.158:8080/ws";
    public final String url;
    public static MessageManager selfy;
    
    public MessageManager(FlightControlController fcc, String url)
    {
        selfy = this;
        this.fcc = fcc;
        this.url = url;
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
        //System.out.println(obj.toString());
        updateName(obj);
        updateStatus(obj);
        updateOutputControlsFromMessage(obj);
        updateInputControlsFromMessage(obj);
        updateObjectiveListFromMessage(obj);
        updateTimeLeft(obj);
    }
    
    public void updateStatus(JSONObject obj)
    {
        if (!obj.keySet().contains("Status")) return;
        System.out.println(obj.toString());
        String status = obj.get("Status").toString();     
        if (status == "POLL") fcc.enablePollMode();
        if (status == "NOPOLL") fcc.disablePollMode();
    }
    
    public void updateName(JSONObject obj)
    {
        if (!obj.keySet().contains("name")) return;
        fcc.setName(obj.get("name").toString());
    }
    
    public void updateTimeLeft(JSONObject obj)
    {
        if (!obj.keySet().contains("TimeLeft")) return;
        int time = (int)obj.getDouble("TimeLeft");
        fcc.updateClock(time);
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
            if (wid==999 && gid == 999) fcc.goNoGoMode();
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
                wid.setOnChangedEvent(o -> sendUpdatedValue(wid));
              //  wid.updated.addListener(o -> {
              //      sendUpdatedValue(wid);
              //  });
                fcc.createControl(wid);
            }
        } 
    }
    
    public void updateObjectiveListFromMessage(JSONObject obj)
    {
        if (!obj.keySet().contains("objectiveList")) return;
        JSONArray widgetList = obj.getJSONArray("objectiveList");
        fcc.checkList.getItems().clear();
        for(int i=0;i<widgetList.length();i++)
        {
            JSONObject widget = widgetList.getJSONObject(i);
            //fcc.checkList.getItems().add(widget.toString());
        }             
    }
    
    public void sendUpdatedValue(ControlBase obj)
    {
        JSONObject json = new JSONObject();
        int wid = obj.id % 100;
        int gid = (int)Math.round((obj.id - wid) / 100.0);
         
        json.put("Wid", wid);
        json.put("Gid", gid);
        json.put("Value", obj.getValue());
        String jsonString = json.toString();
        System.out.println(jsonString);
        wsc.send(jsonString);
    }
    
    public static MessageManager get()
    {
        return selfy;
    }
}

