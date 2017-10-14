package ca.mcgill.ecse321.urlms.application;

import ca.mcgill.ecse321.urlms.model.FundingManager;
import ca.mcgill.ecse321.urlms.model.InventoryManager;
import ca.mcgill.ecse321.urlms.model.StaffManager;
import ca.mcgill.ecse321.urlms.model.StaffMember;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;
import ca.mcgill.ecse321.urlms.view.MainPage;

public class URLMSApplication {

		private static URLMS urlms;	
		
		private static String filename = "urlms.xml";
		
		/**
		 * @param args
		 */
		public static void main(String[] args) {
			// start UI
			// TODO startup the UI corresponding to the right mode?
	        java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	            	new MainPage().setVisible(true);
	            	
	            }
	        });        
		}

		public static URLMS getURLMS() {
			if (urlms == null) {
				urlms = load();	
			}
	 		return urlms;
		}
	
		public static void save() {
			PersistenceXStream.saveToXMLwithXStream(urlms);
		}
		
		public static URLMS load() {
			PersistenceXStream.setFilename(filename);
			URLMS sm;
			sm = (URLMS) PersistenceXStream.loadFromXMLwithXStream();
			// model cannot be loaded - create empty TileO
			if (sm == null) {
				sm = PersistenceXStream.initializeModelManager("urlms.xml");
			}
			return sm;
		}
		
		public static void setFilename(String newFilename) {
			filename = newFilename;
		}
	}
	
