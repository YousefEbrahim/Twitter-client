package com.codex.twitterclient.base.DtoBase;



import com.codex.twitterclient.base.Constants.ServiceIDConstance;

import java.util.HashMap;
import java.util.Map;



/**
 * Created by YousefEbrahim on 30/05/2016.
 */
public class UrlData {
    private int service_id;
    private String url ;
    private int connectionType = ServiceIDConstance.GET;
    private Map<String,Object> keysAndValues = new HashMap<>();

    public boolean isPost(){
        if (connectionType == ServiceIDConstance.POST){
            return true;
        }else{
            return  false;
        }
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(int connectionType) {
        this.connectionType = connectionType;
    }

    public Map<String, Object> getKeysAndValues() {
        return keysAndValues;
    }

    public void setKeysAndValues(Map<String, Object> keysAndValues) {
        this.keysAndValues = keysAndValues;
    }
}
