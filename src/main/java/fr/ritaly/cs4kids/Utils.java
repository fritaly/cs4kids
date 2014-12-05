package fr.ritaly.cs4kids;

import javax.swing.UIManager;

public class Utils {

	public static void setLookAndFeel() {
		for (UIManager.LookAndFeelInfo laf : UIManager
				.getInstalledLookAndFeels()) {
			
			if ("Nimbus".equals(laf.getName())) {
				try {
					UIManager.setLookAndFeel(laf.getClassName());

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}
}
