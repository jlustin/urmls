package ca.mcgill.ecse321.urlms.application;

import ca.mcgill.ecse321.urlms.model.FundingManager;
import ca.mcgill.ecse321.urlms.model.InventoryManager;
import ca.mcgill.ecse321.urlms.model.StaffManager;
import ca.mcgill.ecse321.urlms.model.StaffMember;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;
import ca.mcgill.ecse321.urlms.view.MainPage;

public class URLMSApplication {
		//private static URLMS urlms = new URLMS(new StaffManager(new URLMS(0)), new InventoryManager(new URLMS(0)), new FundingManager(0, new URLMS(0)));
		private static URLMS urlms;
//		private static StaffManager staffManager = new StaffManager(urlms);
//		private static StaffMember staffMember = new StaffMember("Victor", 123, staffManager);
		
		//staffManager.addStaffMember(staffMember);
		
		private static String filename = "staff.xml";
		//public static TileODesignPage dp = new TileODesignPage();
		//public static TileOPlayPage pp = new TileOPlayPage();
		
		/**
		 * @param args
		 */
		public static void main(String[] args) {
			// start UI
			// TODO startup the UI corresponding to the right mode?
	        java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	            	new MainPage().setVisible(true);
	            	
	           // 	PersistenceXStream.readBahaye();
//	                if (tileO.hasGames()) {
//	                	if (getTileO().getCurrentGame().getMode() == Mode.DESIGN){
//	                    	dp.setVisible(true);       
//	                    }
//	                    else {
//	                    	pp.setVisible(true);
//	                    }
//	                }
	            }
	        });        
		}

		public static URLMS getURLMS() {
			if (urlms == null) {
				urlms = new URLMS(0);			// ONLY FOR TEST, NEED TO IMPLEMENT WITH LOAD LATER
//				urlms = load();	
			}
	 		return urlms;
		}
		//public URLMS(StaffManager aStaffManager, InventoryManager aInventoryManager, FundingManager aFundingManager)
		public static void save() {
			PersistenceXStream.saveToXMLwithXStream(urlms.getStaffManager());
		}
		
		public static URLMS load() {
			PersistenceXStream.setFilename(filename);
			URLMS sm = getURLMS();
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
	
