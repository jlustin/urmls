package ca.mcgill.ecse321.urlms.application;

import ca.mcgill.ecse321.urlms.model.URLMS;

public class URLMSApplication {
		private static URLMS urlms;
		private static String filename = "data.urlms";
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
//	            	new MainPage().setVisible(true);
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
//			if (urlms == null) {
//				// load model
//				urlms = load();
//			}
	 		return urlms;
		}
		
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
	
