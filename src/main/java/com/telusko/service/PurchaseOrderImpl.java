package com.telusko.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;

@Service
public class PurchaseOrderImpl implements IPurchaseOrder 
{
	@Autowired
	private JavaMailSender sender;
	
	@Value("{spring.mail.username}")
	private String fromId;
	
	@Override
	public String purchase(String[] items, Double[] prices, String[] toEmail) throws Exception 
	{
		
		double amount=0.0;
		for(double price:prices)
		{
			amount=amount+price;
		}
		String message="Hello! "+Arrays.toString(items)+" with price "+Arrays.toString(prices)+" which is costing total of "+amount
				+" Bill Attached for refference";
		
		MimeMessage mimeMessage = sender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		
		helper.setFrom(fromId);
		helper.setCc(toEmail);
		helper.setSubject("Something Important");
		helper.setSentDate(new Date());
		helper.addAttachment("java.jpg", new ClassPathResource("com/telusko/image/java.jpg"));
		helper.setText(message);
		
		sender.send(mimeMessage);
		
		return "Mail sent! kindly check";
	}

}
