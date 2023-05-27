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
    private String winner;
    private int winnerVotes;
  
 

    public int getWinnerVotes() {
		return winnerVotes;
	}

	public void setWinnerVotes(int winnerVotes) {
		this.winnerVotes = winnerVotes;
	}

    public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public int getSeat_id() {
		return seat_id;
	}

	public void setSeat_id(int seat_id) {
		this.seat_id = seat_id;
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

