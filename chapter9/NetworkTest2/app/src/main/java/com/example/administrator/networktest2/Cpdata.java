package com.example.administrator.networktest2;

public class Cpdata {
    private String cpid;
    private String name;
    private String birthday;
    public String creditrating;
    public void setId(String id){
        this.cpid =id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setVersion(String version){
        this.birthday =version;
    }
    public void setCreditrating(String creditrating){this.creditrating=creditrating;}
    public String getCpid(){
        return  cpid;
    }
    public String getName(){
        return name;
    }

   public  String getBirthday(){
        return birthday;
   }
   public String getCreditrating(){return creditrating;}

}
