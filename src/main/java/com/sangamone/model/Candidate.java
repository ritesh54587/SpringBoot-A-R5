package com.sangamone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="candidate")
public class Candidate {
	
	
	 @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	 
	 private int id;
	 private String seatNo;
	 private String congressCandidateName;
	    private String BJPCandidateName;
	    private String JDSCandidateName;
	    private String othersCandidateName;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getCongressCandidateName() {
			return congressCandidateName;
		}
		public void setCongressCandidateName(String congressCandidateName) {
			this.congressCandidateName = congressCandidateName;
		}
		public String getBJPCandidateName() {
			return BJPCandidateName;
		}
		public void setBJPCandidateName(String bJPCandidateName) {
			BJPCandidateName = bJPCandidateName;
		}
		public String getJDSCandidateName() {
			return JDSCandidateName;
		}
		public void setJDSCandidateName(String jDSCandidateName) {
			JDSCandidateName = jDSCandidateName;
		}
		public String getOthersCandidateName() {
			return othersCandidateName;
		}
		public void setOthersCandidateName(String othersCandidateName) {
			this.othersCandidateName = othersCandidateName;
		}
		public String getSeatNo() {
			return seatNo;
		}
		public void setSeatNo(String seatNo) {
			this.seatNo = seatNo;
		}
	  

}
