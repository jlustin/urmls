package ca.mcgill.ecse321.urlms.application;

import ca.mcgill.ecse321.urlms.model.FundingManager;
import ca.mcgill.ecse321.urlms.model.InventoryManager;
import ca.mcgill.ecse321.urlms.model.StaffManager;
import ca.mcgill.ecse321.urlms.model.StaffMember;
import ca.mcgill.ecse321.urlms.model.URLMS;

public class URLMSApplication {
    //private static URLMS urlms = new URLMS(new StaffManager(new URLMS(0)), new InventoryManager(new URLMS(0)), new FundingManager(0, new URLMS(0)));
    private static URLMS urlms;
//		private static StaffManager staffManager = new StaffManager(urlms);
//		private static StaffMember staffMember = new StaffMember("Victor", 123, staffManager);

    //staffManager.addStaffMember(staffMember);

    private static String filename = "data.urlms";
    //public static TileODesignPage dp = new TileODesignPage();
    //public static TileOPlayPage pp = new TileOPlayPage();

    /**
     * @param args
     */
    public static void main(String[] args) {

    }

    public static URLMS getURLMS() {
        if (urlms == null) {
            urlms = new URLMS(0);			// ONLY FOR TEST, NEED TO IMPLEMENT WITH LOAD LATER
//				urlms = load();
        }
        return urlms;
    }
    //public URLMS(StaffManager aStaffManager, InventoryManager aInventoryManager, FundingManager aFundingManager)
//		public static void save() {
//			PersistenceObjectStream.serialize(tileO);
//		}
//
//		public static TileO load() {
//			PersistenceObjectStream.setFilename(filename);
//			tileO = (TileO) PersistenceObjectStream.deserialize();
//			// model cannot be loaded - create empty TileO
//			if (tileO == null) {
//				tileO = new TileO();
//			}
//			return tileO;
//		}

    public static void setFilename(String newFilename) {
        filename = newFilename;
    }
}