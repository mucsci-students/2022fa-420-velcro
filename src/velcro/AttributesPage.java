/**
 * Filename: AttributesPage.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: A page for interfacing with Attributes options.
 * 
 */

package velcro;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

import javax.swing.JButton;

public class AttributesPage {

	JFrame attributesPage = new JFrame();
	private JTextField textField;
	private JTextField txtuseOnlyFor;

	// Page buttons & layout.
	AttributesPage(Instance thisInstance) {
		attributesPage.setTitle("Velcro CSCI 420 :: Attributes");
		attributesPage.setBounds(100, 100, 700, 500);
		attributesPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		attributesPage.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBounds(54, 119, 255, 51);
		attributesPage.getContentPane().add(textField);
		textField.setColumns(10);
		
		txtuseOnlyFor = new JTextField();
		txtuseOnlyFor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtuseOnlyFor.setColumns(10);
		txtuseOnlyFor.setBounds(369, 119, 255, 51);
		attributesPage.getContentPane().add(txtuseOnlyFor);
		
		// Button to add attributes.
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(54, 247, 159, 69);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!containsAlphaNumeric(textField.getText())) {
					JOptionPane.showMessageDialog(attributesPage, "Please enter an attribute name.");
					return;
				}
				// Checks if Attribute already exists.
				Attributes orig = thisInstance.getAttribute(textField.getText());
				if (orig != null) {
					JOptionPane.showMessageDialog(attributesPage, "Attribute already exists!");
					return;
				}
				thisInstance.addAttribute(textField.getText());
				JOptionPane.showMessageDialog(attributesPage, "Attribute added successfully.");
			}});
		attributesPage.getContentPane().add(btnNewButton);
		
		// Button to delete attributes.
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnDelete.setBounds(265, 247, 159, 69);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!containsAlphaNumeric(textField.getText())) {
					JOptionPane.showMessageDialog(attributesPage, "Please enter an attribute name.");
					return;
				}
				if (thisInstance.removeAttribute(textField.getText()))
					JOptionPane.showMessageDialog(attributesPage, "Attribute removed successfully.");
				else
					JOptionPane.showMessageDialog(attributesPage, "Attribute removal failed, attribute not found.");
			}});
		attributesPage.getContentPane().add(btnDelete);
		
		// Button to rename attributes.
		JButton btnRename = new JButton("Rename");
		btnRename.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnRename.setBounds(472, 247, 159, 69);
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Checks for empty text boxes.
				if (!containsAlphaNumeric(textField.getText()) || !containsAlphaNumeric(txtuseOnlyFor.getText())) {
					JOptionPane.showMessageDialog(attributesPage, "Please enter an attribute and new name.");
					return;
				}
				// Checks for attribute existing.
				Attributes orig = thisInstance.getAttribute(textField.getText());
				if (orig == null) {
					JOptionPane.showMessageDialog(attributesPage, "Attribute not found.");
					return;
				}
				// Checks if rename is already taken.
				Attributes orig2 = thisInstance.getAttribute(txtuseOnlyFor.getText());
				if (orig2 != null) {
					JOptionPane.showMessageDialog(attributesPage, "Attribute already exists!");
					return;
				}
				if (orig.rename(txtuseOnlyFor.getText())) {
					JOptionPane.showMessageDialog(attributesPage, "Attribute renamed successfully.");
				} else {
					JOptionPane.showMessageDialog(attributesPage, "Attribute not found.");
				}
			}});
		attributesPage.getContentPane().add(btnRename);
		
		JLabel lblNewLabel = new JLabel("Attribute Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(115, 63, 150, 45);
		attributesPage.getContentPane().add(lblNewLabel);
		
		JLabel lblReplaceClassName = new JLabel("Rename Attribute Name");
		lblReplaceClassName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblReplaceClassName.setBounds(400, 63, 204, 45);
		attributesPage.getContentPane().add(lblReplaceClassName);
		
		// Button to return to the landing page.
		JButton btnHomepage = new JButton("Homepage");
		btnHomepage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnHomepage.setBounds(265, 359, 159, 69);
		btnHomepage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attributesPage.dispose();
				LandingPage window = new LandingPage(thisInstance);
				window.homepage.setVisible(true);
			}});
		attributesPage.getContentPane().add(btnHomepage);
		
		// Help text with help info message.
		JLabel lblNewLabel_1 = new JLabel("Help");
		lblNewLabel_1.setToolTipText("Attributes Help");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(623, 11, 51, 29);
		
		String s = "<html>'Attribute Name' : Field1 &#9 'Rename Attribute Name' : Field2<br>To add an Attribute, enter a unique attribute name in Field1 and press the 'Add' button.<br> The action will fail if there is already an attribute with that name or if the entered name if invalid.<br> To delete an Attribute, enter the name of the attribute that you wish to delete in Field1 and press the 'Delete' button.<br> The action will fail if there is no attribute with the given name.<br>To rename an Attribute, enter the current name of the attribute that you wish to change in Field1,<br> and the name that you wish to change it to in Field2.<br> To initiate the action, hit the 'Rename' button once both fields have been entered. The action will fail if there is no<br> existing attribute with the given name or if the new name being entered is either already in use or is invalid.</html>";
		
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			 
            	@Override
            	public void mouseClicked(MouseEvent e) {
            		JOptionPane.showMessageDialog(attributesPage, s);
            	}
 
            	@Override
            	public void mouseExited(MouseEvent e) {
            	}
 
            	@Override
           	public void mouseEntered(MouseEvent e) {
            	}
 
        	});
		attributesPage.getContentPane().add(lblNewLabel_1);
		
		attributesPage.setVisible(true);
		
	}
	
	private static boolean containsAlphaNumeric(String input) {
		Pattern p = Pattern.compile("^[a-zA-Z0-9]*$");
		return p.matcher(input).find();
	}
}
