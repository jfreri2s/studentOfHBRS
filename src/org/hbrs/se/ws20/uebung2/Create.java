package org.hbrs.se.ws20.uebung2;

public class Create implements Member {
    private Integer id;
    public Create(Integer ID){
        id = ID;
    }

    public Integer getID() {
        return id;
    }
    public String toString(){
       return  "Member (ID = " + this.getID()+ ")";
    }
}
