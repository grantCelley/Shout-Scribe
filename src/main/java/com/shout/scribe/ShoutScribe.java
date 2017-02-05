/**
 * This is the display and main class of the project
 * @author Grant E. Celley
 */

package com.shout.scribe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;


public class ShoutScribe extends JFrame implements ActionListener{

	JTextArea output;//where the output will be displayed
	JButton button;//the button which will start the dictation process
	JPanel panel;//the panel where all the components will live in

	Dictator dictator;//The object that will be doing the dictation

	boolean listening;//tells us if it listening

	/**
	 * The constructor for the class
	 * will create the gui
	 * @author Grant E. Celley
	 */
	public ShoutScribe() {
		//creates the panel
		panel = new JPanel();
		
		//creates the output and makes it scrollable
		output = new JTextArea(20,40);
		output.setLineWrap(true);

		JScrollPane scrollPane = new JScrollPane(output);

		//creates the button
		button = new JButton("Start listening");

		//adds the output to the panel
		panel.add(scrollPane);

		//makes the button able to react to being clicked
		button.addActionListener(this);
		
		//adds the button to the panel
		panel.add(button);
		
		listening = false;//sets listening to false
		
		//final initialization of the window
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//adds the panel to the window
		this.add(panel);
		this.setVisible(true);//finally makes it visable

	}

	/**
	 * This is called when the button is pressed
	 * will turn dictation on and off
	 * @author Grant E. Celley
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//cheaks if the button is clicked
		if(e.getSource().equals(button)){
			//if it is listening it stops else it starts to listen
			if(listening){
				dictator.end();
				listening = false;
				button.setText("Start Listening");
			}else{
				dictator = new Dictator(this);
				dictator.start();
				listening = true;
				button.setText("Stop Listening");
			}
		}

	}
	
	/**
	 * Writes data to the output
	 * is called by the dictator object
	 * @param text A string that gets printed to the output
	 * @author Grant E. Celley
	 */
	public void writeData(String text){
		output.append(text);
	}
	
	/**
	 * The main function/entry point of the program
	 * it makes a ShoutScribe object and calls it's constructor
	 * @param args the comandline arguments
	 * @author Grant E. Celley
	 */
	public static void main(String[] args) {
		ShoutScribe shoutScribe = new ShoutScribe();
	}

}
