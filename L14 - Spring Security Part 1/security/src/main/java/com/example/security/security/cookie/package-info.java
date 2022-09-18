package com.example.security.security.cookie;


/**
 *
 *
 *
 *      Sent by the backend to the FE .. use it later to validate the user session
 *
 *
 *      CSRF
 *              Cross site request forgery
 *         phishing paytm / sbi / anybank
 *              message
 *              --> your kyc has expired
 *                              please click on below link and proceed
 *
 *
 *                  Whats happening in the background
 *                  (??)
 *                  downloads the cookies (may be)
 *
 *                  --> directs paytm , https://yourbank.com
 *
 *                      something similar
 *                          -----> payyyytm.com
 *                                  sbbii.com
 *                                  yourbannnnk.com
 *
 *                             Kindly note the html content would be similar to the bank
 *                             (session created) -- > the website (payyytm.com)
 *                                  Login :
 *                                  Password :
 *
 *
 *                                  (post : form / injected here)
 *                                  (Visible -- auto submit this with JS)
 *
 *                                  <Div>
 *                                  <form = on click submit>
 *  *                                          username :
 *  *                                          password:
 *  *                                          cookie:
 *  *                                          transferWalletBalance=($$$$wallet) & amount = ()
 *  *                                      </>
 *  *
 *                                      <>Hey you have won a lotteey please cloick here </>
 *                                      </>
 *
 *
 *                              OPTION call ->
 *                                      CORS (which is validated)
 *                                      Origin (payyyytm)                   ---> passwed to Backend
 *                                          (Origin) paytm.com
 *
 *
 *
 *
 *
 *
 *      CORS
 *              Cross-Origin Resource Sharing (solution)
 *
 *
 *
 *
 *
 */