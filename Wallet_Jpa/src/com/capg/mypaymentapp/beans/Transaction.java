package com.capg.mypaymentapp.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Transaction {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int transactionId;
private String mobileNo;
private String transactionLog;

public Transaction() {
	super();
}
public Transaction(String mobileNo, String s) {
	this.mobileNo=mobileNo;
	transactionLog=s;
}
public String getTransactionLog() {
	return transactionLog;
}
public void setTransactionLog(String transactionLog) {
	this.transactionLog = transactionLog;
}

} 
