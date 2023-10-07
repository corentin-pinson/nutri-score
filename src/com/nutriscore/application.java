package com.nutriscore;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import com.nutriscore.domain.NutriScore;
import com.nutriscore.domain.RandomNumberApiDelegetate;
import com.nutriscore.view.NutriScoreFrame;

public class application {
	private static NutriScoreFrame COMPONENT;
	private static RandomNumberApiDelegetate randomNumberDelegate = new RandomNumberApiDelegetate();

	public static void main(String[] args) {
		JFrame frame = new JFrame("Nutri Score Widget");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		COMPONENT = new NutriScoreFrame();
		frame.add(COMPONENT);
		frame.pack();
		frame.setVisible(true);

		// Periodically update current score
		updateCurrentScore();
	}

	public static void updateCurrentScore() {
		Runnable updateNutriScoreTask = () -> {
			try {
				int randomNumber = randomNumberDelegate.getRandomNumber(0, 4);
				NutriScore nutriscore = NutriScore.values()[randomNumber];
				COMPONENT.setScore(nutriscore);
				System.out.println("New nutriscore: " + nutriscore.getLetter());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error while retrieving random number: " + e.getMessage());
			}
		};

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(updateNutriScoreTask, 3, 10, TimeUnit.SECONDS);
	}
}