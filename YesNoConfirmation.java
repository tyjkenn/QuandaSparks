//package interjournal;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class YesNoConfirmation {
	
	String question;
	String title;
	
	public YesNoConfirmation(String question, String title){
		this.question = question;
		this.title = title;
	}
	
	public boolean use(){
		if(JOptionPane.showConfirmDialog(new JFrame(),
        question, title, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
			return true;
		}
		return false;
	}
}
