Kayako Java SDK
================


Examples
==========

Here are some samples for the SDK usage.

```java
package com.kayako.test.api;

import com.kayako.api.configuration.Configuration;
import com.kayako.api.department.Department;
import com.kayako.api.enums.AppEnum;
import com.kayako.api.enums.SalutationEnum;
import com.kayako.api.ticket.Ticket;
import com.kayako.api.ticket.TicketPriority;
import com.kayako.api.user.User;
import com.kayako.api.user.UserGroup;
import com.kayako.api.user.UserOrganization;

public class TestTicket{
	
	public void TestCreation() {
	
		Configuration config = Configuration.init("<API URL>", "<API Key>", "<Secret Key>");
		Configuration.setConfiguration(config);
	
		try {		
			// Get User Group
			UserGroup ug = new UserGroup().populate(UserGroup.getAll(UserGroup.getController()).filterByComponentValue("title", "Registered").getFirstComponent());
			
			// Get User organization
			UserOrganization uo = new UserOrganization().populate(UserOrganization.getAll(UserOrganization.getController()).getFirstComponent());
			
			// Create User
			User user = ug.createUser("Sampl222333eUser", "sampleuser@example.com", "abc123").setUserOrganization(uo).setSalutation(SalutationEnum.MR).setSendWelcomeEmail(false).create();
		
			// Get priority component
			TicketPriority tp = new TicketPriority().populate(TicketPriority.getAll().filterByComponentValue("title", "Urgent").getFirstComponent());
						
			// Get Department
			Department department = new Department().populate(Department.getAll(Department.getController()).filterByComponentValue("App", "Tickets").getFirstComponent());

			// Get Ticket Status & type
			TicketStatus status = new TicketStatus().populate(TicketStatus.getAll(TicketStatus.getController()).getFirstComponent());
			TicketType type = new TicketType().populate(TicketType.getAll(TicketType.getController()).getFirstComponent());
			
			// Create ticket
			Ticket ticket = (Ticket) user.createTicket(department, "This is sample ticket Content", "This is sample Ticket Subject").setPriority(tp).setCreationType(CreationTypeEnum.DEFAULT).setStatus(status).setType(type).create();
			
			System.out.println(" ticket ID :: " + ticket.getId());
		} catch(Exception exception) {
			exception.printStackTrace();
		}
			
		return;
	}
	
}






```
