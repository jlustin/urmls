package ca.mcgill.ecse321.urlms.application;

import ca.mcgill.ecse321.urlms.model.URLMS;
//import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;


public class URLMSApplication {

		private static URLMS urlms;						//main urlms for the whole application
		
		private static String filename;	//persistence data file name
		
		/**
		 * @param args
		 */
//		public static void main(String[] args) {
//			// start the Main Page UI
//	        java.awt.EventQueue.invokeLater(new Runnable() {
//	            public void run() {
//	            	new MainPage().setVisible(true);
//	            	
//	            }
//	        });        
//		}

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
			//PersistenceXStream.setFilename(filename);
			URLMS urlms;
			urlms = (URLMS) PersistenceXStream.loadFromXMLwithXStream();
			
			//if the file does not exist, create a new save file
			if (urlms == null) {
				urlms = PersistenceXStream.initializeModelManager("urlms.xml");
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
	
