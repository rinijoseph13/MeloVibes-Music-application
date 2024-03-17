package com.example.sbweb.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.sbweb.entities.Users;
import com.example.sbweb.services.UsersService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

import jakarta.servlet.http.HttpSession;

@Controller
public class PaymentController {
    
    @Autowired
    UsersService usersv;
    
    @PostMapping("/createOrder")
    @ResponseBody
    public String createOrder() {
        Order order = null;
        try {
            RazorpayClient razorpay = new RazorpayClient("rzp_test_NFulou5haNg0RR", "tI5YOskptBS4jppMbYsXoaHi");
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", 50000);
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "receipt#1");
            JSONObject notes = new JSONObject();
            notes.put("notes_key_1", "Tea, Earl Grey, Hot");
            orderRequest.put("notes", notes);
            order = razorpay.orders.create(orderRequest);
        } catch (RazorpayException e) {
            System.out.println("Exception while creating order: " + e.getMessage());
            // Handle exception here, you can return a custom error message or handle it based on your requirements
            return "Error occurred while creating the order.";
        }
        // Check if order is null to avoid NullPointerException
        if (order != null) {
            return order.toString();
        } else {
            // Handle case where order is null (creation failed for some reason)
            return "Order creation failed.";
        }
    }
    
    @PostMapping("/verify")
    @ResponseBody
    public boolean verifyPayment(@RequestParam String orderId, @RequestParam String paymentId, @RequestParam String signature) {
        try {
            // Initialize Razorpay client with your API key and secret
            RazorpayClient razorpayClient = new RazorpayClient("rzp_test_NFulou5haNg0RR", "tI5YOskptBS4jppMbYsXoaHi");
            // Create a signature verification data string
            String verificationData = orderId + "|" + paymentId;

            // Use Razorpay's utility function to verify the signature
            boolean isValidSignature = Utils.verifySignature(verificationData, signature, "tI5YOskptBS4jppMbYsXoaHi");

            return isValidSignature;
        } catch (RazorpayException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @GetMapping("payment-success")
    public String paymentSuccess(HttpSession session) {
        String email = (String) session.getAttribute("email");
        Users user = usersv.getUser(email);
        user.setPremium(true);
        usersv.updateUser(user);
        return "login";
    }
    
    @GetMapping("payment-failure")
    public String paymentFailure() {
        return "login";
    }
}