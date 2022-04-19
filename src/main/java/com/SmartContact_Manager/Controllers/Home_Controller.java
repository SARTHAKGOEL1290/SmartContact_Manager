package com.SmartContact_Manager.Controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.SmartContact_Manager.Dao.UserRepository;
import com.SmartContact_Manager.Entities.Users;
import com.SmartContact_Manager.Helper.message;

@org.springframework.stereotype.Controller

public class Home_Controller {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping("/")
	public String home(Model model)
	{
		System.out.println("this is home page");
		model.addAttribute("title","this is the home page of Smart Contact manager");
		System.out.println("after first commit------------>");
		
		return "home";
	}
	
	@RequestMapping("/about")
	public String about_info(Model model)
	{
		System.out.println("this is home page");
		model.addAttribute("title","this is the about page of Smart Contact manager");
		return "about";
	}

	@RequestMapping("/signup")
	public String signup_info(Model model)
	{
		System.out.println("this is home page");
		//model.addAttribute("title","Enter User mandatory details");
		model.addAttribute("user" ,new Users());
		return "SignUp";
	}
	
    @RequestMapping(value ="/do_register",method = RequestMethod.POST)
	public String after_signup(@ModelAttribute("user") Users users,@RequestParam(value="agreement", defaultValue = "false") boolean agreement , Model model,HttpSession session)
	{
    	try {
    		if(!agreement)
        	{
        		System.out.println("you have not accept terms and conditions");
        		throw new Exception("you have not accept terms and conditions");
        	}
        	users.setRole("user");
        	users.setEmbaded(true);
        	users.setPassword(passwordEncoder.encode(users.getPassword()));
    		
        	System.out.println(users.getPassword());
    		System.out.println("agreement is "+agreement);
    		System.out.println("Users"+users);
    		
    		
    	    Users result = this.userRepo.save(users);
    		
    	   model.addAttribute("user",result);
    	   model.addAttribute("user",new Users());
    	   session.setAttribute("message",new message("Successfully register","alert-success"));	
    		return "SignUp";
    		
    		
    	}
    	catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    		model.addAttribute("user",users);
    		session.setAttribute("message",new message("Something went wrong"+e.getMessage(),"alert-danger"));
    		return "SignUp";
		}
    	
    	
    	
	}
    
    // this is handeller for sign in page
    @GetMapping("/signin")
	public String custom_login(Model model)
	{
		return "login";
	}
    
    // this is user index handaller

	
}
