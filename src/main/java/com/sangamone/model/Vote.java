package com.sangamone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  seat_id;
    private String seatNo;
    private int congress;
    private int bjp;
    private int jds;
    private int others;
  
 


	public int getId() {
        return seat_id;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public int getCongress() {
        return congress;
    }

    public void setCongress(int congress) {
        this.congress = congress;
    }

    public int getBjp() {
        return bjp;
    }

    public void setBjp(int bjp) {
        this.bjp = bjp;
    }

    public int getJds() {
        return jds;
    }

    public void setJds(int jds) {
        this.jds = jds;
    }

    public int getOthers() {
        return others;
    }

    public void setOthers(int others) {
        this.others = others;
    }

   

    
}

