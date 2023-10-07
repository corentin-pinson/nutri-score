package com.nutriscore.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import com.nutriscore.domain.NutriScore;

/**
 * Widget displaying the nutri-score of a meal
 */
public class NutriScoreFrame extends JPanel {
	private static final long serialVersionUID = -4567748542012458646L;
	private static final int SCORE_BOX_WIDTH = 50;
	private static final int SCORE_BOX_HEIGHT = 200;

	private NutriScore currentScore = null;

	public NutriScoreFrame() {
		setPreferredSize(new Dimension(SCORE_BOX_WIDTH * NutriScore.values().length, SCORE_BOX_HEIGHT));
	}

	/**
	 * Update the current score on the nutri-score widget
	 * 
	 * @param score The new score
	 */
	public void setScore(NutriScore score) {
		if (!score.equals(currentScore)) {
			currentScore = score;
			repaint();
		}
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);

		// Paint every scores
		for(int i = 0; i < NutriScore.values().length; i++) {
			paintScore(graphics, i * SCORE_BOX_WIDTH, 0, NutriScore.values()[i]);
		}
	}

	protected void paintScore(Graphics graphics, int x, int y, NutriScore score) {
		// Shape the box of the score
		graphics.setColor(Color.decode(score.getColor()));
		graphics.fillRect(x, y, SCORE_BOX_WIDTH, SCORE_BOX_HEIGHT);

		// Write the letter of the score
		graphics.setFont(new Font("Arial", Font.BOLD, 30));
		int letterWidth = graphics.getFontMetrics().stringWidth(score.getLetter());
		int boxX = x + (SCORE_BOX_WIDTH - letterWidth) / 2;
		int boxY = y + SCORE_BOX_HEIGHT / 2 + graphics.getFontMetrics().getAscent() / 2;

		// Highlight the current score
		if (score.equals(currentScore)) {
			graphics.setColor(Color.RED);
		} else {
			graphics.setColor(Color.WHITE);
		}
		graphics.drawString(score.getLetter(), boxX, boxY);
	}
}