package com.codex.twitterclient.base.ServiceBase;


import com.codex.twitterclient.base.Constants.ExceptionConstance;

/**
 * Created by YousefEbrahim on 02/06/2016.
 */
public class ExceptionBase {
    private int code;
    private String message;
    private int messageResource;
    private int iconResource;
   public ExceptionBase(int code,String message,int iconResource){
       this.code = code;
       this.message = message;
       this.iconResource = iconResource;


    }
    public ExceptionBase(int code ){
        this.code = code;
         messageResource = ExceptionConstance.getMassage(code);
         iconResource =  ExceptionConstance.getIcon(code);

    }

    public int getCode() {
        return code;
    }



    public String getMessage() {
        return message;
    }



    public int getIconResource() {
        return iconResource;
    }


    public int getMessageResource() {
        return messageResource;
    }
}
