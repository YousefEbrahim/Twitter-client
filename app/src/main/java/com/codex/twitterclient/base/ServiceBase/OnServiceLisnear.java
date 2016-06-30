package com.codex.twitterclient.base.servicebase;

/**
 * Created by YousefEbrahim on 10/03/2016.
 */
public  interface  OnServiceLisnear {

     <T> void onUpdate(int servesID, T response);
     <T> void onError(int servesID, T error, boolean isHandel);
}
