package com.SOAPService;

import jakarta.xml.ws.Endpoint;

import com.SOAPService.StoreService;

public class SoapServe{
  public void init(){
    Endpoint.publish("http://0.0.0.0:8081/soap", new StoreService());
    System.out.println("Server running in 80 port");
  }
}
