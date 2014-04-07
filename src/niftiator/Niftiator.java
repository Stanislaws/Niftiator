/*
 *  Niftiator - removes these characters: 
 * \ / : * ? " < > | 
 * from a string of characters and replaces them with dashes (-)
 *  Copyright (C) 2014  Jan Zajaczkowski
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package niftiator;

import java.util.regex.Pattern;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.DefaultEditorKit;


/**
 *
 * @author XZAJACZK
 */
public class Niftiator extends JFrame{
    
    private final JButton paste = new JButton("P");    
    private final JTextField folder = new JTextField(25);
    private final JButton fix = new JButton("F");
    private final JButton reset = new JButton("R");
    private final JMenu File, Edit, Process, Help;
    private final JMenuBar Bar;
    private final JMenuItem Exit, Paste, SelectAll, About, Extract;
    
    private final JPanel jp = new JPanel();
    
        public Niftiator(){
        
        //jp.setLayout(new BorderLayout());
        Bar = new JMenuBar();
        
        File = new JMenu("File");
        File.setMnemonic(KeyEvent.VK_F);
        
        Exit = new JMenuItem("Exit");
        Exit.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_X, ActionEvent.ALT_MASK));
        Exit.setMnemonic(KeyEvent.VK_X);
        File.add(Exit);
        
        Bar.add(File);
        
        Edit = new JMenu("Edit");
        Edit.setMnemonic(KeyEvent.VK_E);
        
        SelectAll = new JMenuItem("Select All");
        SelectAll.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        SelectAll.setMnemonic(KeyEvent.VK_A);
        Edit.add(SelectAll);
        
        Edit.addSeparator();
        
        Paste = new JMenuItem(new DefaultEditorKit.PasteAction());
        Paste.setText("Paste");
        Paste.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        Paste.setMnemonic(KeyEvent.VK_P);
        Edit.add(Paste);
        
        Bar.add(Edit);
        
        Process = new JMenu("Process");
        Process.setMnemonic(KeyEvent.VK_P);
        
        Extract = new JMenuItem("Fix");
        Extract.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        Extract.setMnemonic(KeyEvent.VK_F);
        
        Process.add(Extract);
        
        Bar.add(Process);
        
        Help = new JMenu("Help");
        Help.setMnemonic(KeyEvent.VK_H);
        
        About = new JMenuItem("About");
        About.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_A, ActionEvent.ALT_MASK));
        About.setMnemonic(KeyEvent.VK_A);
        Help.add(About);
        
        Bar.add(Help);
        
        setJMenuBar(Bar);
        

        paste.setPreferredSize(new Dimension(20,20));     
        paste.setBorder(null);
        paste.setBorderPainted(false);
        paste.setMargin(new Insets(0,0,0,0));
        paste.setToolTipText("Click to Paste the copied folder name.");
        jp.add(paste);
        //set the parameters of the fix button
        fix.setPreferredSize(new Dimension(20,20));     
        fix.setBorder(null);
        fix.setBorderPainted(false);
        fix.setMargin(new Insets(0,0,0,0));
        fix.setToolTipText("Click the Fix the folder name entered in the box.");
        //add the fix button to the jpanel
        jp.add(fix);
        //sets the tooltip of the phone box
        folder.setToolTipText("Enter the folder name to be formatted.");
        //add the phone field to the jpanel
        jp.add(folder);        
        //set the parameters of the reset button
        reset.setPreferredSize(new Dimension(20,20));
        reset.setBorder(null);
        reset.setBorderPainted(false);
        reset.setMargin(new Insets(0,0,0,0));
        reset.setToolTipText("Click to Reset the contents of the box.");
        //add the reset button to the jpanel
        jp.add(reset);
        //add the jpanel
        add(jp);
        
        paste.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                pasteFolder();
            }
        });
        //define an action for the phone field
        folder.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                fixFolder();
            }       
        });
        
        //define an action for the fix button
        fix.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                fixFolder();
            }       
        });
        //define an action for the reset button
        reset.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFolder();
            }      
        });
        
        Exit.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        }
        );
        
        SelectAll.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                selectall();
            }
            
        });
        
        Extract.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                fixFolder();
            }
            
        });
        
        About.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                about();
            }
            
        });
        
        Paste.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                pasteFolder();
            }
            
        });
    }
        
        /**
     * Shows information about the author and the license
     */
    public void about(){
        String aoutput = "Niftiator - removes specific characters from the box "
                        + "and replaces\nthem with another character.\n" +
                    "Copyright © 2014 Jan Zajaczkowski\n" +
                    "\n" +
                    "This program is free software: you can "
                        + "redistribute it and/or modify\n" +
                    "it under the terms of the GNU General Public "
                        + "License as published by\n" +
                    "the Free Software Foundation, either version 3 "
                        + "of the License, or\n" +
                    "(at your option) any later version.\n" +
                    "\n" +
                    "This program is distributed in the hope that it "
                        + "will be useful,\n" +
                    "but WITHOUT ANY WARRANTY; without even the "
                        + "implied warranty of\n" +
                    "MERCHANTABILITY or FITNESS FOR A PARTICULAR "
                        + "PURPOSE.  See the\n" +
                    "GNU General Public License for more details.\n" +
                    "\n" +
                    "You should have received a copy of the GNU "
                        + "General Public License\n" +
                    "along with this program.  If not, see "
                        + "<http://www.gnu.org/licenses/>.";
        JOptionPane.showMessageDialog(jp,aoutput,"About",
                JOptionPane.PLAIN_MESSAGE);        
    }
    
    public void pasteFolder(){
        folder.setText("");
        folder.paste();
        folder.requestFocus();
        folder.selectAll();
    }
    
    public void resetFolder(){
        folder.setText("");
        folder.requestFocus();
        folder.selectAll();
    }
    
    public void fixFolder(){
        try {
            String t = folder.getText();
            if(t.length() > 0){
                String r = "-";
                String u = t.replaceAll(Pattern.quote("\\"),r);
                u = u.replaceAll(Pattern.quote("/"), r);
                u = u.replaceAll(Pattern.quote(":"), r);
                u = u.replaceAll(Pattern.quote("*"), r);
                u = u.replaceAll(Pattern.quote("?"), r);
                u = u.replaceAll(Pattern.quote("\""), r);
                u = u.replaceAll(Pattern.quote("<"), r);
                u = u.replaceAll(Pattern.quote(">"), r);
                u = u.replaceAll(Pattern.quote("|"), r);
                folder.setText(u);
                folder.requestFocus();
                folder.selectAll();
                folder.copy();
            }
        }
        catch(StringIndexOutOfBoundsException e){
            
        }
    }
    
    public void selectall(){
        folder.requestFocus();
        folder.selectAll();
    }
    public void exit(){
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Niftiator n = new Niftiator();
        n.setLocationRelativeTo(null);
        n.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        n.setTitle("Niftiator");
        n.pack();
        n.setVisible(true); 
        n.setAlwaysOnTop(true);
        n.setResizable(false);
    }
    
}
