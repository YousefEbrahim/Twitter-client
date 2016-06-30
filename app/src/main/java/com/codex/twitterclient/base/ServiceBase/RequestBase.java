package com.codex.twitterclient.base.ServiceBase;


import com.codex.twitterclient.base.DtoBase.UrlData;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import java.util.Iterator;
import java.util.Map;


/**
 * Created by YousefEbrahim on 29/05/2016.
 */
public class RequestBase {

    public UrlData urlData ;
    ServicesBase  servicesBase;


    public RequestBase(ServicesBase servicesBase){
        this.servicesBase = servicesBase;
        urlData = new UrlData();
        urlData.setService_id(servicesBase.getServiceId());
        urlData.setConnectionType(servicesBase.TypeOfConnection());
        urlData.setUrl(servicesBase.getURL());
    }

    public RequestBody PostRequest(){
        FormEncodingBuilder formEncodingBuilder=new FormEncodingBuilder();
        if (urlData.getKeysAndValues()!=null && urlData.getKeysAndValues().size()>0){
            Iterator<Map.Entry<String, Object>> iterator = urlData.getKeysAndValues().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String,Object> pairs = (Map.Entry<String,Object>)iterator.next();
                Object value =  pairs.getValue();
                String key = pairs.getKey();
                if (key!=null && value != null) {
                    formEncodingBuilder.add(key, value.toString());
                }
            }
        }
        RequestBody formBody = formEncodingBuilder.build();
        return formBody;
    }

    public String GetRequest(){
        String url = "";
        if (urlData != null && urlData.getKeysAndValues().size()>0){
            Iterator<Map.Entry<String, Object>> iterator = urlData.getKeysAndValues().entrySet().iterator();
            int i = 0;
            while (iterator.hasNext()) {
                Map.Entry<String,Object> pairs = (Map.Entry<String,Object>)iterator.next();
                Object value =  pairs.getValue();
                String key = pairs.getKey();
                i++;
                url += key  + "=" + value;
                if (i != urlData.getKeysAndValues().size()){
                    url +="&";

                }

            }

        }
        return url;
    }
}
