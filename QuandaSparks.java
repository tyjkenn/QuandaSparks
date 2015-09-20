/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Stephen and Ty
 */
public class QuandaSparks extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    YesNoConfirmation confirm;
    AI ai;
    Properties defaultProps;
    Properties appProps;
    String[] users;
    String response;    
    String uin;
    String question;
    String filePath;
    static String currentUser;    
    boolean isSaved;

    public QuandaSparks() {
        try{		
		filePath = "defaultProperties.txt";
		FileInputStream in = new FileInputStream(filePath);
		defaultProps = new Properties();
		defaultProps.load(in);
		in.close();
		
		filePath = "Properties.txt";
		appProps = new Properties(defaultProps);
		in = new FileInputStream(filePath);
		appProps.load(in);
		in.close();		
	} catch (FileNotFoundException ex) {
		System.out.println(filePath + " was not found.");
	} catch (IOException ex){
		System.out.println("IOException occured: " + ex.toString());
	}
	currentUser = appProps.getProperty("currentUser");
        users = appProps.getProperty("users").split(", ");
	initComponents();
        ai = new AI("brain.tyk");
        question = "";
        isSaved = true;
        question = ai.makeSearch("");
        Conversation.setText("Quanda: "+question);
        getRootPane().setDefaultButton(Submit);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Conversation = new javax.swing.JTextArea();
        Submit = new javax.swing.JButton();
        User = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        mSave = new javax.swing.JMenuItem();
        mQuit = new javax.swing.JMenuItem();
        userMenu = new javax.swing.JMenu();
        mSwitchUsers = new javax.swing.JMenuItem();
        mNewUser = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("QuandaSparks");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setForeground(java.awt.Color.white);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Conversation.setColumns(20);
        Conversation.setEditable(false);
        Conversation.setRows(5);
        Conversation.setFocusable(false);
        jScrollPane1.setViewportView(Conversation);

        Submit.setText("Submit");
        Submit.setNextFocusableComponent(User);
        Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitActionPerformed(evt);
            }
        });

        User.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                UserKeyTyped(evt);
            }
        });

        fileMenu.setText("File");

        mSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        mSave.setText("Save");
        mSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mSaveActionPerformed(evt);
            }
        });
        fileMenu.add(mSave);

        mQuit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        mQuit.setText("Quit");
        mQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mQuitActionPerformed(evt);
            }
        });
        fileMenu.add(mQuit);

        jMenuBar1.add(fileMenu);

        userMenu.setText("Users");

        mSwitchUsers.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        mSwitchUsers.setText("Switch Users...");
        mSwitchUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mSwitchUsersActionPerformed(evt);
            }
        });
        userMenu.add(mSwitchUsers);

        mNewUser.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        mNewUser.setText("New User...");
        mNewUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mNewUserActionPerformed(evt);
            }
        });
        userMenu.add(mNewUser);

        jMenuBar1.add(userMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(User, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Submit, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(User)
                    .addComponent(Submit, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitActionPerformed
        Submit.setEnabled(false);response = "\n" + currentUser + ": " + User.getText();
        Conversation.append(response);
        uin = User.getText();
        User.setText(null);
        question = ai.makeSearch(uin);
        Conversation.append("\nQuanda: " + question);
        isSaved = false;
    }//GEN-LAST:event_SubmitActionPerformed

	private void mQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mQuitActionPerformed
	if(!isSaved){
            confirm = new YesNoConfirmation("Your conversation hasn't been saved since you\nresponded last. Would you like to save now?","Save?");
            if(confirm.use()) ai.save();
        }
	try {
			FileOutputStream out = new FileOutputStream(filePath);
			appProps.store(out, "---No Comment---");
			out.close();
		} catch (IOException ex){
			System.out.println("IOException occured: " + ex.toString());
		}
        System.exit(0);
	}//GEN-LAST:event_mQuitActionPerformed

        private void UserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UserKeyTyped
            Submit.setEnabled(true);
}//GEN-LAST:event_UserKeyTyped

        private void mSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mSaveActionPerformed
            isSaved = true;
            ai.save();
        }//GEN-LAST:event_mSaveActionPerformed

        private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
		if(!isSaved){
			confirm = new YesNoConfirmation("Your conversation hasn't been saved since you\nresponded last. Would you like to save now?","Save?");
			if(confirm.use()) ai.save();
		}
		try {
			FileOutputStream out = new FileOutputStream(filePath);
			appProps.store(out, "---No Comment---");
			out.close();
		} catch (IOException ex){
			System.out.println("IOException occured: " + ex.toString());
		}
        System.exit(0);
        }//GEN-LAST:event_formWindowClosing

        private void mNewUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mNewUserActionPerformed
            String newUser = JOptionPane.showInputDialog(this,"Enter New Username", "New User", JOptionPane.PLAIN_MESSAGE);
            if (newUser != null) {
                appProps.setProperty("users", appProps.getProperty("users").concat(", " + newUser));
                users = appProps.getProperty("users").split(", ");
                currentUser = newUser;
                appProps.setProperty("currentUser", currentUser);
            }
        }//GEN-LAST:event_mNewUserActionPerformed

        private void mSwitchUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mSwitchUsersActionPerformed
		String oldUser = currentUser;
		currentUser = (String)JOptionPane.showInputDialog(this, "Choose a User", "Switch Users", JOptionPane.PLAIN_MESSAGE, null, users, oldUser);
		System.out.println(currentUser);
		if (currentUser != null) appProps.setProperty("currentUser", currentUser);
		else currentUser = oldUser;
        }//GEN-LAST:event_mSwitchUsersActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            /*javax.swing.UIManager.setLookAndFeel(
            javax.swing.UIManager.getSystemLookAndFeelClassName());*/
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Conversation;
    private javax.swing.JButton Submit;
    private javax.swing.JTextField User;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem mNewUser;
    private javax.swing.JMenuItem mQuit;
    private javax.swing.JMenuItem mSave;
    private javax.swing.JMenuItem mSwitchUsers;
    private javax.swing.JMenu userMenu;
    // End of variables declaration//GEN-END:variables
}