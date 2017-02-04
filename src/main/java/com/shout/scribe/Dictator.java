package com.shout.scribe;

import java.io.IOException;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class Dictator extends Thread {

	ShoutScribe ss;
	boolean cont;

	public Dictator(ShoutScribe shoutScribe) {
		this.ss = shoutScribe;

	}

	public void run() {
		cont = true;
		while(cont){
			Configuration configuration;
			LiveSpeechRecognizer recognizer;
			SpeechResult result;
			try {
				//create the configuration
				configuration = new Configuration();
				// Set path to acoustic model.
				configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
				// Set path to dictionary.
				configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
				// Set language model.
				configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

				recognizer = new LiveSpeechRecognizer(configuration);
				while (cont) {
					recognizer.startRecognition(true);
					result = recognizer.getResult();
					System.out.println("Before the recognition stopped");
					recognizer.stopRecognition();

					ss.writeData(result.getHypothesis() + " ");
					System.out.println("After the recognition stopped");
				}
			} catch (IOException e) {
				System.out.println("Exeption: " + e);

			}
		}
	}

	public void end() {
		cont = false;
	}

}
