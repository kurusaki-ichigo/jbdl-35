	/**
	 *
	 * 		- Wont be integrating banks api here
	 *
	 *
	 * 	Design ( Payment Wallet as a service (build))
	 *
	 * 		Major Actors
	 * 		Actors -- Entities ? --- Blocker ----
	 *
	 * 			--> LOAN ACCOUNT(), SAVING ACCOUNT , CURRENT ACCOUNT
	 * 	    - Banking Application ----> external posting (transaction is success) ---> RBI -- ISO format
	 * 	    - Standing Instructions -- ?
	 * 	    			----> (open bank api to integration)
	 *
	 *
	 * 		What are the different features looking forward to
	 *
	 * 		- Transactions {Ledger} (base)  (	 * 	    - Load money (Payment) , transfer)
	 * 			-- User A -- User B
	 * 	    - Notification (act as confirmation) - email , sms (AWS SNS), whatsapp (b2b charges the merchant) -> what tickets (charges)
	 * 	 	- Pay for service (api integration)	-- to book tickets
	 * 	 	- History (user can see its last 30 transactions made)
	 * 	 	- Statement Generation - Loan statement , how many transactions as user you have done in a month or specific period (sort of history)
	 *		- mock the banks api --> let it be successful --> application running on different port (success resopnse)
	 *				--> cron to invoke that
	 *		- Send a mail to payer for asking money .
	 *
	 *
	 * 		Identify Actors and their association
	 *			1								1
	 *		User ------(transaction)-----     (Wallet)
	 * 			-------------- (Generate Statement)
	 *
	 * 		Microservice
	 * 		-	User (CRUD)
	 * 		-	Wallet (user current balance , display)
	 * 		- 	Transaction / Ledger (mark in pending , success , failure)
	 * 		- 	Notification
	 * 		-  Statement (wont be -- everyone to implement)
	 *
	 *
	 *
	 *      
            ********* NOT COVERED HERE ******************
            onboarding process                  (10,0000)
                    --> Application exist
                            ---> kyc (know your customer)
                            ---> kyb (know your business)

                --> User details of either 
                        ---> Passport 
                        --> govt issued Id

                --> Business details of either 
                        ---> GST No 
                        --> TIN No.

                (are associating any bank account with it)
                (need to validate a bank account by doing penny drop)
                    
	 *
	 *
	 *
	 *
	 *
	 *
	 *		Sending a push notification to a browser work
	 *			(generic) -- promos , marketing event
	 *
	 *		customer specific -- special discount on his/her birthday
	 *		(user)
	 *
	 *	store browzer token (when ever you open a tab , a request to our server is made - making a connection live ,
	 *	then transfer messages)
	 *
	 *
	 * @param args
	 */