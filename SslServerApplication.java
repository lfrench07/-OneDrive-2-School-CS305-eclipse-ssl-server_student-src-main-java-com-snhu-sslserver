package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.security.MessageDigest;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}
//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";

@RestController
class ServerController{   
    @GetMapping("/hash") //map the "/hash" keyword to the server.
    public String nameGreeting(@RequestParam(value= "inputString", defaultValue = "Hello World Check Sum!")String inputData)throws Exception {
    	return genHash(inputData);
    }
    //This uses the SHA-256 algorithm
    public String genHash(String data) throws Exception{
    	MessageDigest messageD = MessageDigest.getInstance("SHA-256");
    	messageD.update(data.getBytes());
    	
    	byte[] chksumBytes = messageD.digest();  //Message digest will need to be in bytes.
    	 
        StringBuilder hexString = new StringBuilder();  //Converts byte array to a hex string.
        
        for (int i = 0; i < chksumBytes.length; i += 2) {
            hexString.append(String.format("%02x", chksumBytes[i]));
            if (i + 1 < chksumBytes.length) {
                hexString.append(String.format("%02x", chksumBytes[i + 1]));
            }
          }
        
        String name = "Name: ";
        name += "Larry French <p>";
        
        String messRet = "Data:" + " " + String.format(data) + "<p>" ;
        messRet += "Algorithm: SHA-265 <p>CheckSum Value: ";
    	        
        return name + messRet + hexString;
    }
}