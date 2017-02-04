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

	Configuration configuration;
	LiveSpeechRecognizer recognizer;
	SpeechResult result;

	JTextArea output;
	JButton button;
	JPanel panel;

	Dictator dictator;

	boolean listening;


	public ShoutScribe() {


		listening = false;

		panel = new JPanel();
		output = new JTextArea(20,40);
		output.setLineWrap(true);

		JScrollPane scrollPane = new JScrollPane(output);

		button = new JButton("Start listening");

		panel.add(scrollPane);
		button.addActionListener(this);

		panel.add(button);

		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);

		this.setVisible(true);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(button)){

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

	public void writeData(String text){
		output.append(text);
	}

	public static void main(String[] args) {
		ShoutScribe shoutScribe = new ShoutScribe();
	}

}
