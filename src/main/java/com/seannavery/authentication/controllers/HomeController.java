package com.seannavery.authentication.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.seannavery.authentication.models.LoginUserModel;
import com.seannavery.authentication.models.UserModel;
import com.seannavery.authentication.services.UserService;

@Controller
public class HomeController {

	// Add once service is implemented:
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String index(Model model, @ModelAttribute("user") UserModel user) {
		model.addAttribute("newUser", new UserModel());
		model.addAttribute("newLogin", new LoginUserModel());
		return "index.jsp";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") UserModel newUser, BindingResult result, Model model,
			HttpSession session) {
		userService.register(newUser, result);
		// TO-DO Later -- call a register method in the service
		// INSIDE THE SERVICE to do some extra validations and create a new user!

		if (result.hasErrors()) {
			// will still check validations in models
			model.addAttribute("newLogin", new LoginUserModel());
			return "index.jsp";
		}

		// No errors!
		// TO-DO Later: Store their ID from the DB in session,
		// in other words, log them in.
		session.setAttribute("userId", newUser.getId());

		return "redirect:/dashboard";
	}

	@PostMapping("/login")
 public String login(@Valid @ModelAttribute("newLogin") LoginUserModel newLogin, 
         BindingResult result, Model model, HttpSession session) {
		
     // Add once service is implemented:
		UserModel user = userService.login(newLogin, result);
 
     if(result.hasErrors()) {
         model.addAttribute("newUser", new UserModel());
         return "index.jsp";
     }
 
     // No errors! 
     session.setAttribute("userId", user.getId());

     return "redirect:/dashboard";
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model, @ModelAttribute("user") UserModel user, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long loggedInId = (Long) session.getAttribute("userId");
		
		model.addAttribute("loggedInUser", userService.findUser(loggedInId));
		return "dashboard.jsp";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
}
