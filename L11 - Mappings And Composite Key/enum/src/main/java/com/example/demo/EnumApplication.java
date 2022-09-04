package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnumApplication {

	/**
	 *
	 *
	 * Notification Service
	 * 		Design LLD
	 * 		what
	 *
	 * 		as of now (live GMAIL email being sent)
	 * 				---> 	Rest API calls
	 *
	 * 		- when ever a user is created -- we will send him an email with account creation
	 * 	-	whenever his wallet is deducted - we will send him an email with balance deductio
	 *
	 * 		-----			-	B2b api - which has pricing associated with it..
	 *
	 *
	 * 		Notification service
	 *  --- Call/ event would be sent to it 				--> it would enrich the data 	---> call futher end point to send message
	 *
	 **
	 *
	 *
	 *
	 *		Message schema
	 * 			{
	 * 			 	"to - contactInformation" : "mobile number , devide identifier , emailId , mobile number"
	 * 			 	"message type"	: SMS / PUSH / EMAIL / WHATSAPP
	 * 			 "message" :
	 * 			}
	 *
	 *	template	"Hey $name, you account balance has been deducted by $currencyIdentifier $amount."
	 *	dynamicFields : {$name, $currencyIdentifier ,$amount}
	 *
	 * 		DB schema
	 * 		Every table needs to have
	 * 			{
	 * 				"id" :
	 * 				"source" : "user service"
	 * 				"channel value" : ""
	 * 				"channel Type" : "SMS / PUSH / EMAIl"
	 * 	    		"templateId" :
	 * 				"dynamic fields" : {
	 * 				 	"field1" : value1 ...
	 * 				}
	 * 				"created_at"
	 * 				"updated_at"
	 * 			}
	 *
	 *
	 * 		John --- (Yeh 100 $ , 5 times)
	 *
	 * 		John - stressed --
	 * tempalte1	when only in his passbook - 100 $ has been debited
	 *
	 *
	 * 			Happen
	 * 			- Notiifcaiton service
	 * 				can receive same message more than once
	 *
	 *
	 * 			(m1 , m2 ,m3 , m4 , m1)
	 * 			- you do not want to send m1 again ---
	 *
	 *
	 *			- user is sending a message
	 *					- 	{
	 * 	 * 	 * 				"id" : "transactionId"
	 * 	 * 	 * 				"source" : "transaction  service"
	 * 	 * 	 *
	 * 	 * 	 * 			}
	 *
	 *
	 *          		- 	{
	 * 	 * 	 * 	 * 				"id" : "7492749274"
	 * 	 * 	 * 	 * 				"source" : "transaction  service"
	 * 	 * 	 * 	 *
	 * 	 * 	 * 	 * 			}
	 * 	 *
	 * 	 *          		All the userId ---> should be transactionIds -- only then the message will be sent
	 *
	 *          	{
	 * 	 * 	 * 	 * 				"id" : "7492749274"
	 * 	 * 	 * 	 * 				"source" : "user  service"
	 * 	 * 	 * 	 *
	 * 	 * 	 * 	 * 			}
	 *f
	 *
	 *        {
	 * 	 * 				"id" : "transactionId"
	 * 	 * 				"source" : "transaction service service"
	 * 	 * 				"channel value" : "john@yomail.com"
	 * 	 * 				"channel Type" : "EMAIl"
	 * 	 * 	    		"templateId" : tempalte1
	 * 	 * 				"dynamic fields" : {
	 * 	 * 				 	"name" : John
	 * 	 					"amount" : 100
	 * 	 					"currnecy" : USD
	 * 	 * 				}
	 * 	 * 				"created_at" = now()
	 * 	 * 				"updated_at= now()
	 * 	 * 			}
	 * 	 *
	 *
	 *
	 *
	 *
	 *
	 *		Transaction Service (Callback from 3rd party)-------------->
	 *
	 *	OrderService						RazorPay - Payment Gateway provider	(Order, amount)
	 *	Order								- Stripe - Payment Gateway provider  (Order, AMOUNT)
	 *
	 *			- they would provide us with a callback
	 *					(/callback url)
	 *					(orderId : , status : )
	 *				Multiple callbacks -- for same orderId 		------->
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(EnumApplication.class, args);
	}

}
