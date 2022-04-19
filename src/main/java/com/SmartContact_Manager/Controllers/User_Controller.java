package com.SmartContact_Manager.Controllers;

import java.net.http.HttpRequest;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.SmartContact_Manager.Dao.ContactRepository;
import com.SmartContact_Manager.Dao.UserRepository;
import com.SmartContact_Manager.Entities.Contacts;
import com.SmartContact_Manager.Entities.Users;
import com.SmartContact_Manager.Helper.message;


@Controller
@RequestMapping("/user")
public class User_Controller {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ContactRepository contactRepo;
	
	// handller for when user login into the system {user dashboard}
	
	@RequestMapping("/index")
	public String dashboard(Model model , Principal principal)
	{
		
		String username = principal.getName();  // to get the unique attribute from Database
		
		Users users = userRepo.getUserByUserName(username); // fetching the complete user detail based on emai_id from database 
		System.out.println("inside user page");
		
		System.out.println("Userinformation"+users);
		
		model.addAttribute("user", users);  // sending user data to the view page 
		return "user_dashboard";
	}
	
	// handeller for view of add new contact form

	@GetMapping("/add_contact")
	public String addnew_contact(Model model)
	{
	//	model.addAttribute("title","Please enter required details");
		model.addAttribute("contacts",new Contacts());
		
		return "new_contact_form";
	}
    // handeller for processing user add new contact form after click on submit button
	@PostMapping("/process-contact")
	public String process(@ModelAttribute Contacts contact , Principal principal , HttpSession session)
	{
		//fetcher unique username from user DB
		
		try {
			String username = principal.getName();
			
			
			//fetching user information 
			Users user = userRepo.getUserByUserName(username);
			
			//many to one mapping
			contact.setUser(user);
			
			//add contact gets from contact form to user DB automtically reflect to contact DB( here we not using contact repository
			user.getContacts().add(contact);
			
			this.userRepo.save(user);
			
			System.out.println("contacts details are"+ contact);
			 session.setAttribute("message",new message("Successfully register","alert-success"));
			return "new_contact_form";
		}
		
		catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    	//	model.addAttribute("user",users);
    		session.setAttribute("message",new message("Something went wrong"+e.getMessage(),"alert-danger"));
    		return "new_contact_form";
		}
	}
	// handeller for pagination
	
	@GetMapping("/view_contact/{page}")
	public String view_contact(@PathVariable("page") Integer page,Model model,Principal principal)
	{
		String username= principal.getName();
		
		//fetching user information 
		Users user = userRepo.getUserByUserName(username);
		
		Pageable pageable = PageRequest.of(page, 5);
		
		Page<Contacts> contact = this.contactRepo.getContactByUser(user.getId(),pageable);
		
		System.out.println(contact);
		
		
		
		model.addAttribute("contacts", contact);
		//this page is page no mentioned in url
		model.addAttribute("currentPage",page);
		
		model.addAttribute("totalPages",contact.getTotalPages());
		
		return "view_all_contacts";
		
	}
	
	@GetMapping("/contact/{cId}")
	public String show_user_profile(@PathVariable("cId") Integer Cid , Model m, Principal principal)
	{
		// fetching contact by  contact id
		Optional<Contacts> contactOptional = this.contactRepo.findById(Cid);
		// fetching complete contact detail from Contact DB
		 Contacts contact = contactOptional.get();
		 
		 // adding contact information to view page
		
		
		 // the elow code is security bug to ensure user can view only his contact 
		  // expalined in video no 61
		
		 String username = principal.getName();
		 
		 Users user = this.userRepo.getUserByUserName(username);
		 
		 if(user.getId()==contact.getUser().getId())
		 {
			 m.addAttribute("contacts", contact);
		 }
		 
		 
		 System.out.println("contact information"+ contact);
		 
		return "show_full_contact_info";
	}
	
	// deleting the particular content
	
	@RequestMapping(value="/delete/{cId}" , method= {RequestMethod.GET,RequestMethod.DELETE})
	public String delete_contact(@PathVariable("cId") Integer Cid,Model model, HttpSession session,Principal principal)
	{
		System.out.println("hello");
		// fetching contact by  contact id
		 Contacts contact = this.contactRepo.findById(Cid).get();
		// fetching complete contact detail from Contact DB
		  
		//  System.out.println(contact.getMail());
		  
		//  System.out.println(contact.getUser());
		  
		  
	
		 // this.contactRepo.delete(contact);  // this method does not work trying new method
		
		 /*  the below code is working fine but we need to Delete request thats why comment out below code
	// extracting the user from user DB / then get all contact list / and remove particular contact from that list
		  String username = principal.getName();
		  
		  Users user = this.userRepo.getUserByUserName(username);
		  
		 // System.out.println("hello");
		  user.getContacts().remove(contact);
		//  System.out.println("user deleted");
		  this.userRepo.save(user);
		  
		*/  
		
		 // if we don use deletemapping annotation we neeed to comment out below line
		 this.contactRepo.delete(contact);
		  
		  session.setAttribute("message",new message("Successfully deleted","alert-success"));
		return "redirect:/user/view_contact/0";
	}
	
	@RequestMapping("/update_contact/{cId}")
	public String update_contact_util(@PathVariable("cId") Integer Cid,Model model,Principal principal)
	{
		System.out.println("inside contact update page");
		
		 Contacts contact = this.contactRepo.findById(Cid).get();
		
		model.addAttribute("contacts",contact);
		
		return "update_user_contact";
	}
	
	@PostMapping("/process-contact-update/{cId}")
	public String update_contact(
			@ModelAttribute Contacts contact,
			@PathVariable("cId") Integer Cid,
			Principal principal
			, HttpSession session)
	{
		
		 Contacts contact1 = this.contactRepo.findById(Cid).get();
		 System.out.println("contact is ------------->>>>"+contact1);
	
		 // here we use principal because when we save contact only the user column in table is null , resulting the contact is not shows on grid
		 String username = principal.getName();
			
			
			//fetching user information 
			Users user = userRepo.getUserByUserName(username);
			
			//many to one mapping
			contact.setUser(user);
		 contactRepo.save(contact);
		 session.setAttribute("message",new message("Successfully updated the contact","alert-success"));
		 
		return "redirect:/user/view_contact/0";
	}
}
