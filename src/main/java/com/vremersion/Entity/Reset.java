package com.vremersion.Entity;

/**
 * @author XP
 * @date 2018/5/16 20:41
 */
public class Reset {
    private int id;
    private String email;
    private String signature;
    private long outtime;

    public Reset(){};

    public Reset(String email, String signature, long outtime){
        this.email = email;
        this.signature = signature;
        this.outtime = outtime;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setSignature(String signature){
        this.signature = signature;
    }

    public String getSignature(){
        return signature;
    }

    public void setOuttime(long outtime){
        this.outtime = outtime;
    }

    public long getOuttime(){
        return outtime;
    }

}
