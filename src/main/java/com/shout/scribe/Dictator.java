/**
 * The file contains the Dictator class
 * It does all the dictation
 * @author Grant E. Celley <grant.celley@gmail.com>
 * @version 0.5
 */
package com.shout.scribe;

//the imports
import java.io.IOException;
//the imports from sphinx(the speech to text engin
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class Dictator extends Thread {

	ShoutScribe ss;//the ShoutScribe that created this object
	boolean cont;//a conditional if we should continue dictation
	
	/**
	 * The constructor of the class
	 * @param shoutScribe the ShoutScribe it needs to communicate with
	 * @author Grant E. Celley
	 */
	public Dictator(ShoutScribe shoutScribe) {
		this.ss = shoutScribe;

	}
	
	/**
	 * The run function from the thread class
	 * this will run as a different thread
	 * this will do all of the dictation
	 * @author Grant E. Celley
	 */
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
	
	/**
	 * this will end the loop
	 * @author Grant E. Celley
	 */
	public void end() {
		cont = false;
	}

}
