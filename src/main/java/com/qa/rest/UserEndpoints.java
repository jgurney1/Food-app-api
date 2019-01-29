package com.qa.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.business.service.UserService;

@Path("/user")
public class UserEndpoints {

	@Inject UserService service;
	
	@Path("/addAccount")
	@POST
	@Produces({"application/json"})
	public String addAcount(String account) {
		return service.addAccount(account);
	}
	
	@Path("/removeAccount/{id}")
	@DELETE
	@Produces({"application/json"})
	public String removeAccount(@PathParam("id") String email) {
		return service.removeAccount(email);
	}
	
	@Path("/showAllAccounts")
	@GET
	@Produces({"application/json"})
	public String showAllAccounts() {
		return service.showAllAccounts();
	}
	
	@Path("/verifyAccount")
	@POST
	@Produces({"application/json"})
	public String verifyAccount(String account) {
		return service.verifyAccount(account);
	}
	
	@Path("/updateAccount")
	@PUT
	@Produces({"application/json"})
	public String updateAccount(String account) {
		return service.updateAccount(account);
	}
}
