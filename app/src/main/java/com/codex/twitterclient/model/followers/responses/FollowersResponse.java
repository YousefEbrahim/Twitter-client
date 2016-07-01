package com.codex.twitterclient.model.followers.responses;

import com.codex.twitterclient.model.followers.dtos.FollowerItem;

import java.util.ArrayList;

/**
 * Created by YousefEbrahim on 01/07/2016.
 */
public class FollowersResponse {
   private String next_cursor;
   private String next_cursor_str;
   private int previous_cursor;
   private int previous_cursor_str;
    private ArrayList<FollowerItem> users ;

    public String getNext_cursor() {
        return next_cursor;
    }

    public void setNext_cursor(String next_cursor) {
        this.next_cursor = next_cursor;
    }

    public String getNext_cursor_str() {
        return next_cursor_str;
    }

    public void setNext_cursor_str(String next_cursor_str) {
        this.next_cursor_str = next_cursor_str;
    }

    public int getPrevious_cursor() {
        return previous_cursor;
    }

    public void setPrevious_cursor(int previous_cursor) {
        this.previous_cursor = previous_cursor;
    }

    public int getPrevious_cursor_str() {
        return previous_cursor_str;
    }

    public void setPrevious_cursor_str(int previous_cursor_str) {
        this.previous_cursor_str = previous_cursor_str;
    }

    public ArrayList<FollowerItem> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<FollowerItem> users) {
        this.users = users;
    }
}
