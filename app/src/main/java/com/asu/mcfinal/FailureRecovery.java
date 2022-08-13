package com.asu.mcfinal;

import android.content.Context;
import android.widget.Toast;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Set;

public class FailureRecovery {
    public static void failureRecovery(String macID, Context context, MainActivity obj)
            throws InterruptedException {
        Toast.makeText(context, macID + " is at critical battery level!",
                Toast.LENGTH_SHORT).show();
        HashMap<String, Object> deviceMap = (HashMap<String, Object>) obj.sndRcvReg.get(macID);
        Set keySet = obj.sndRcvReg.keySet();
        for(int i=0; i<obj.addressMap.size(); i++) {
            if(!keySet.contains(obj.addressMap.get(i))) {
                obj.connIndex(i);
                obj.sndRcvReg.remove(macID);
                obj.sndRcvReg.put(obj.addressMap.get(i), deviceMap);
                HashMap<String, String> dataMap = obj.generateMap(i);
                JSONObject jsonObject = new JSONObject(dataMap);
                String jsonString = jsonObject.toString();
                Thread.sleep(50);
            }
        }
    }
}
