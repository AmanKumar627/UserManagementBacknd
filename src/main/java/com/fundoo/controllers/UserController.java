package com.fundoo.controllers;








import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fundoo.dto.UserDto;
import com.fundoo.models.Response;
import com.fundoo.models.User;
import com.fundoo.services.UserService;
import com.fundoo.utility.UserToken;




@RestController
@CrossOrigin(origins = {}, allowedHeaders="*",exposedHeaders={"token"})
public class UserController {
	
	
	
	@Autowired
      UserService userService;
	
    
	
	@RequestMapping("/")
	public String welcome()
	{
		return "welcome";
	}

	@RequestMapping(value="/create",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody User user)
	{
	
		userService.create(user);
		System.out.println(user);

		System.out.println("saved successfully");
		return new ResponseEntity<User>(user,HttpStatus.OK);
	
}


	@RequestMapping(value="/read",method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> read(@RequestBody User user )
	{
		//System.out.println("Read : "+user.getUserName()+" "+user.getPassword());
		String status = userService.read(user);
		System.out.println("Status is :"+status);
		Response response = new Response();
		response.setStatus(status);
		
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
		
		
		
		
	
	
	@RequestMapping(value="/forgetpassword",method=RequestMethod.POST)
	public ResponseEntity<?> getPassword(@RequestParam String emailId,User user)
	{
		System.out.println("email id : "+emailId);
		
		userService.forgetPassword(emailId,user );
		return new ResponseEntity<String>(emailId,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> RegisterUser(@RequestBody User user)
	{
	  
	    String emailId = null;
		userService.RegisterUser(emailId);
		

		System.out.println("register succesfully");
		return new ResponseEntity<User>(user,HttpStatus.OK);
	
}
	@RequestMapping(value="/save",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveUser(@RequestBody User user)
	{
	
		userService.saveUser(user);
		System.out.println(user);

		System.out.println("registeruser  successfully");
		return new ResponseEntity<User>(user,HttpStatus.OK);
	
}
}	



//	@RequestMapping(value="/login",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Response> login(@RequestBody User user, HttpServletResponse httpServletResponse){
//
//		System.out.println(user);
//
//		Response response = null;
//
//		UserDto userDto = userService.loginUser(user);
//		if (userDto != null) {
//
//			response = new Response();
//		
//		
//			response.setStatus("Done");
//
//			String token;
//			try {
//				token = UserToken.generateToken(userDto.getId());
//			
//			httpServletResponse.addHeader("token", token);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//			return new ResponseEntity<Response>(response, HttpStatus.OK);
//		} else {
//			response = new Response();
//			response.setStatusCode(404);
//			response.setStatus("Not found");
//			return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
//		}
//
//	}
//		
//	}
//	
//	

//
//	
//	
//	
//	
//	@RequestMapping(value="/registerUser",method=RequestMethod.POST)
//	public ResponseEntity<Response> registerUser(@RequestBody User tempUser )
//	{
//	
//		System.out.println(tempUser);
//		boolean check=userService.addUser(tempUser);
//		
//		response=new Response();
//		if(check)
//		{
//			response.setStatusCode(200);
//			response.setStatus("register successfull");
//			return new ResponseEntity<Response>(response,HttpStatus.OK);
//		}
//		response.setStatus("register unsuccessfull");
//		return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
//	}
//	
//	
//	
//	@RequestMapping("/updateUser/{id}")
//	public ResponseEntity<Response> updateUser(@RequestBody User user,@PathVariable Integer id)
//	{
//		boolean check=userService.updateUser(user,id);
//		response=new Response();
//		if(check)
//		{
//			response.setStatus("updated successfully");
//			return new ResponseEntity<Response>(response,HttpStatus.OK);
//		}
//		response.setStatus("id is not valid");
//		return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
//		
//	}
//	
//	
//	@RequestMapping("/deleteUser/{id}")
//	public ResponseEntity<Response> deleteUser(@PathVariable Integer id)
//	{
//		boolean check=userService.deleteUser(id);
//		response=new Response();
//		if(check)
//		{
//			response.setStatus("delete successfully");
//			return new ResponseEntity<Response>(response,HttpStatus.OK);
//		}
//		response.setStatus("user is not found");
//		return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
//	}
//	
//	
//	
//	@RequestMapping(value="/forgetPassword", method=RequestMethod.POST)
//	public ResponseEntity<Response > forgetPassword(@RequestBody User user)
//	{
//		forgetUser=new User();
//		forgetUser=user;
//		boolean check=userService.forgetPassword(user);
//		response=new Response();
//		if(check)
//		{
//			response.setStatusCode(200);
//			response.setStatus("otp send successfully");
//			return new ResponseEntity<Response>(response,HttpStatus.OK);
//		}
//		response.setStatusCode(404);
//		response.setStatus("email id is not valid");
//		return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
//				
//	}
//	
//
//	@RequestMapping(value="/forgetOtpVerification", method=RequestMethod.POST)
//	public ResponseEntity<Response> forgetOtpVerify(@RequestBody GenerateOtp generateOtp)
//	{
//		boolean check=userService.forgetVerification(generateOtp,forgetUser);
//		response=new Response();
//		if(check)
//		{
//			response.setStatusCode(200);
//			response.setStatus("reset successfully");
//		return new ResponseEntity<Response>(response,HttpStatus.OK);
//		}
//		response.setStatusCode(404);
//		response.setStatus("otp is wrong");
//		return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
//	}
//	
//	
//	
//	
//	
//	
//}	
//	
//
//			
