package ca.mcgill.ecse321.urlms.application;

import java.util.List;

import ca.mcgill.ecse321.urlms.controller.StaffController;
import ca.mcgill.ecse321.urlms.model.ProgressUpdate;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;
import ca.mcgill.ecse321.urlms.view.MainPage;
//import ca.mcgill.ecse321.urlms.view.NewSaveFilePO;

public class URLMSApplication {

		private static URLMS urlms;						//main urlms for the whole application
		
		private static String filename;	//persistence data file name
		
		/**
		 * @param args
		 */
		public static void main(String[] args) {
//			URLMSApplication.setFilename("urlms.xml");
//			StaffController sc =  new StaffController();
//			sc.addStaffMember("Victor",true,true);
//			sc.addStaffMember("Eric",false,true);
//
//			System.out.println(sc.viewStaffMemberName(0));
//			System.out.println(sc.viewStaffMemberID(0));
//			System.out.println(urlms.getLab(0).getStaffMember(0).getResearchRole(0).toString());
//			System.out.println(sc.viewStaffMemberName(1));
//			System.out.println(sc.viewStaffMemberID(1));
//			System.out.println(urlms.getLab(0).getStaffMember(1).getResearchRole(0).toString());
//			sc.removeStaffMember(0);
//			System.out.println(sc.viewStaffMemberName(0));
//			System.out.println(sc.viewStaffMemberID(0));
//			System.out.println(urlms.getLab(0).getStaffMember(0).getResearchRole(0).toString());
//			sc.addProgress("november 27", "du ma", 0);
//			sc.addProgress("dec 8", "du ma presentation", 0);
//			List<ProgressUpdate> progress = sc.viewProgressUpdate(0);
//			for(int i=0; i<progress.size();i++) {
//				System.out.println(progress.get(i).getDate());
//				System.out.println(progress.get(i).getDescription());
//			}
//			sc.addStaffMember("Feras", true, false);
//			System.out.println(urlms.getLab(0).getStaffMember(1).getId());
//			System.out.println(urlms.getLab(0).getStaffMember(1).getName());
//			sc.addStaffMember("JustinToMessUp", true, false);
//			System.out.println(urlms.getLab(0).getStaffMember(2).getId());
//			System.out.println(urlms.getLab(0).getStaffMember(2).getName());





}

		/**
		 * This method will get the current urlms. If it is null, it will fetch for the urlms saved.
		 * @return the current urlms
		 */
		public static URLMS getURLMS() {
			if (urlms == null) {
				urlms = load();	
			}
	 		return urlms;
		}
	
		/**
		 * This method will save the current urlms to the persistence. The data file will be an XLM file created
		 * using XStream.
		 */
		public static void save() {
			PersistenceXStream.saveToXMLwithXStream(urlms);
		}
		
		/**
		 * This method will load the urlms stored in the XML data file. If no load file is found, a new save file
		 * will be created.
		 * @return loaded urlms
		 */
		public static URLMS load() {

			PersistenceXStream.setFilename(filename);
			URLMS urlms = (URLMS) PersistenceXStream.loadFromXMLwithXStream();
			
			//if the file does not exist, create a new save file
			if (urlms == null) {
				urlms = PersistenceXStream.initializeModelManager(filename);
//				NewSaveFilePO nsfpo = new NewSaveFilePO();
//				nsfpo.setVisible(true);
			}
			return urlms;
		}
		
		/**
		 * This method sets the file name to the desired name
		 * @param newFilename file name String
		 */
		public static void setFilename(String newFilename) {
			filename = newFilename;
		}
	}
	
