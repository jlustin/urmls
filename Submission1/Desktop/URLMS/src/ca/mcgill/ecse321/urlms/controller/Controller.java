package ca.mcgill.ecse321.urlms.controller;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
<<<<<<< HEAD
=======
import ca.mcgill.ecse321.urlms.model.Lab;
import ca.mcgill.ecse321.urlms.model.StaffMember;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

public class Controller {

	/**
	 * This method calls the save method from the application to save the
	 * current urlms to the persistence.
	 */
	public void save() {
		URLMSApplication.save();
	}

}
