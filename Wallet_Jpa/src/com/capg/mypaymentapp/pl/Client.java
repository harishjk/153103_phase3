package com.capg.mypaymentapp.pl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import com.capg.mypaymentapp.beans.Customer;
import com.capg.mypaymentapp.beans.Wallet;
import com.capg.mypaymentapp.exception.InvalidInputException;
import com.capg.mypaymentapp.pl.Client;
import com.capg.mypaymentapp.service.WalletServiceImpl;

public class Client {
	 public void menu() {
		   try
		   {
		   WalletServiceImpl w=new WalletServiceImpl();
		 Scanner sc=new Scanner(System.in);
		     System.out.println("1.add user");
			 System.out.println("2.show balance");
			 System.out.println("3.fund transfer");
			 System.out.println("4.deposit money");
			 System.out.println("5.withdraw money");
			 System.out.println("6.print transaction");
			 System.out.println("0.Exit");
			 int i1=sc.nextInt();
			 switch(i1)
			 {
			 case 1: System.out.println("enter your phone Number");
					 String mobileNo= sc.next();
					 validateNumber(mobileNo);
					 System.out.println("enter yor Name");
					 String name= sc.next();
					 validateName(name);
				     if(w.createAccount(name, mobileNo,new BigDecimal(0))!=null)
				     {
				    	 System.out.println("account created");
				     }
				     break;
			 case 2:System.out.println("enter your phone Number");
			        String mobileNo2= sc.next(); 
			        validateNumber(mobileNo2);
			  	    System.out.println("Balance is" + w.showBalance(mobileNo2).getWallet().getBalance());
			 break;
			 case 3: System.out.println("enter your phone number");
			 String sMobileNo= sc.next();
			 validateNumber(sMobileNo);
			 System.out.println("enter the reciever phone number");
			 String tMobileNo= sc.next();
			 validateNumber(tMobileNo);
			 System.out.println("enter the amount");
			 BigDecimal amo=sc.nextBigDecimal();
			 if(w.fundTransfer(sMobileNo, tMobileNo,amo)!=null)
			 {
				 System.out.println("fund transferred succesfully");
			 }
			 break;
			 case 4:System.out.println("enter your phone Number");
	          String mobileNo3= sc.next();
	          validateNumber(mobileNo3);
				 System.out.println("enter the money to deposit");
			 BigDecimal depositAmount= sc.nextBigDecimal();
			 validateAmount(depositAmount);
			 if(w.depositAmount(mobileNo3,depositAmount)!=null)
			 {
				 System.out.println("money deposited");
			 }
			 break;
			 case 5:System.out.println("enter your phone Number");
	          String mobileNo4= sc.next();
	          validateNumber(mobileNo4);
				 System.out.println("enter the money to withdraw");
			  BigDecimal withDrawAmount = sc.nextBigDecimal();
			  validateAmount(withDrawAmount);
			 if(w.withdrawAmount(mobileNo4, withDrawAmount)!=null)
			 {
				 System.out.println("debited");
			 }
			 break;
			 case 6:System.out.println("enter your phone Number");
	          String mobileNo5= sc.next();
	          validateNumber(mobileNo5);
	          ArrayList l=(ArrayList) w.printTransaction(mobileNo5);
	          Iterator i=l.iterator();
	          while(i.hasNext())
	          {
			System.out.println(i.next());
	          }
			 break;
			 case 0:
					System.exit(0);
			default:System.out.println("Invalid Selection");
			 }
			 }
		   catch(InputMismatchException e)
		   {
			   System.out.println("invalid input");
		   }
		   catch(Exception e)
		   {
			   e.printStackTrace();
			   System.out.println(e.getMessage());
		   }
	   }
	 public boolean validateNumber(String number)
	 {
		 String phonePattern="[1-9][0-9]{9}";
		 if(number.matches(phonePattern))
		 {
			
			 return true;
		 }
		 throw new InvalidInputException("invalid mobile number");
	 }
	 public boolean validateName(String name)
	 {
		 String namePattern = "[A-Za-z]+";
		 if(name.matches(namePattern))
		 {
			 return true;
		 }
		 throw new InvalidInputException("invalid name");
	 }
	 public boolean validateAmount(BigDecimal amount)
	 {
		 if(amount.compareTo(new BigDecimal(0))>0)
		 {
			 return true;
		 }
		 throw new InvalidInputException("number cannot be Zero/in negative");
	 }
	   public static void main( String[] args ){
		   Client c = new Client();
	       while(true)
	       {
	    	   c.menu();
	       }
	    }}
