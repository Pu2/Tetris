import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Paint{
	private static JPanel pane = new JPanel();
	private static PaintCanvas canvas = new PaintCanvas();
	private static JLabel label = new JLabel();
	private static int score = 0;
	private static PaintCanvasForNextBlock canvasForNextBlock = new PaintCanvasForNextBlock();

	public static void main(String[] args) {
		JFrame frame = new JFrame("テトリス");
		frame.setBounds(500,500,400,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.white);

		pane.setLayout(null);

		getCanvas().setBounds(20, 100, 400, 800);
		pane.add(getCanvas());

		getCanvasForNextBlock().setBounds(20, 5, 60, 100);
		pane.add(getCanvasForNextBlock());

		//canvas.addMouseListener(new Mouse());
		//getCanvas().addKeyListener(new Keyboard());
		DeadBlock.makeWall();
		MovingBlock.makeFirstBlock();

		setLavel(label, String.valueOf(getScore()));
		pane.add(label);
		pane.add(Button.startButton());
		pane.add(Button.rotateButton());
		pane.add(Button.downButton());
		pane.add(Button.rightButton());
		pane.add(Button.leftButton());

		frame.getContentPane().add(pane);
		frame.setVisible(true);
	}

	static JPanel getJPanel() {
		return pane;
	}

	static JLabel getLabel() {
		return label;
	}	

	static void changeScore(int number) {
		setScore(getScore() + number);
	}

	static void isGameClear() {
		if(score >= 1000) {
			SingleThread.stopRunning();
			Paint.getLabel().setText(String.valueOf("Game Clear"));
			activateStartButton();
		}else {
			label.setText(String.valueOf(Paint.getScore()));
		}
	}

	static void gameOver() {
		getLabel().setText("Game Over");
		activateStartButton();
	}

	static void setLavel(JLabel label, String message) {
		label.setText(message);
		label.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 15));
		label.setForeground(Color.red);
		label.setBounds(100, 50, 100, 50);
	}

	static PaintCanvas getCanvas() {
		return canvas;
	}

	public static void setCanvas(PaintCanvas canvas) {
		Paint.canvas = canvas;
	}

	static int getScore() {
		return score;
	}

	static void setScore(int score) {
		Paint.score = score;
	}

	static void activateStartButton() {
		setScore(0);
		Button.getStartButton().setEnabled(true);
	}

	static PaintCanvasForNextBlock getCanvasForNextBlock() {
		return canvasForNextBlock;
	}

	static class PaintCanvas extends Canvas{
		static Cell[][] deadBlockArray = DeadBlock.getSheetArray();
		static Cell[][] movingBlockArray = MovingBlock.getSheetArray();

		@Override
		public void paint(Graphics g) {
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setStroke(new BasicStroke(1.5f));
			g2d.setColor(Color.black);

			for (int i = 0; i <= 630; i = i + 30) {
				g2d.drawLine(0, 0 + i, 360, 0 + i);
			}
			for (int i = 0; i <= 360; i = i + 30) {
				g2d.drawLine(0 + i, 0, 0 + i, 630);
			}

			//セル配列から情報を読み取りセルに色付け
			for (int i = 0; i < deadBlockArray.length; i++ ) {
				for (int h = 0; h < deadBlockArray[0].length; h++ ) {
					if (deadBlockArray[i][h].getColor() == "red") {
						g2d.setColor(Color.red);
					}else if(deadBlockArray[i][h].getColor() == "green") {
						g2d.setColor(Color.green);
					}else if(deadBlockArray[i][h].getColor() == "blue") {
						g2d.setColor(Color.blue);
					}else if(deadBlockArray[i][h].getColor() == "yellow") {
						g2d.setColor(Color.yellow);
					}else if(deadBlockArray[i][h].getColor() == "black") {
						g2d.setColor(Color.black);
					}else if(deadBlockArray[i][h].getColor() == "orange") {
						g2d.setColor(Color.orange);
					}else if(deadBlockArray[i][h].getColor() == "gray") {
						g2d.setColor(Color.gray);
					}else if(deadBlockArray[i][h].getColor() == "white") {
						g2d.setColor(Color.white);
					}else if(deadBlockArray[i][h].getColor() == "pink") {
						g2d.setColor(Color.pink);
					}
					g2d.fillRect(deadBlockArray[i][h].getX(), deadBlockArray[i][h].getY(), 28, 28);
				}
			}

			for (int i = 0; i < movingBlockArray.length; i++ ) {
				for (int h = 0; h < movingBlockArray[0].length; h++ ) {
					g2d.setColor(new Color(0, 0, 0, 0));
					if (movingBlockArray[i][h].getColor() == "red") {
						g2d.setColor(Color.red);
					}else if(movingBlockArray[i][h].getColor() == "green") {
						g2d.setColor(Color.green);
					}else if(movingBlockArray[i][h].getColor() == "blue") {
						g2d.setColor(Color.blue);
					}else if(movingBlockArray[i][h].getColor() == "yellow") {
						g2d.setColor(Color.yellow);
					}else if(movingBlockArray[i][h].getColor() == "black") {
						g2d.setColor(Color.black);
					}else if(movingBlockArray[i][h].getColor() == "orange") {
						g2d.setColor(Color.orange);
					}else if(movingBlockArray[i][h].getColor() == "gray") {
						g2d.setColor(Color.gray);
					}else if(movingBlockArray[i][h].getColor() == "skelton") {
						g2d.setColor(new Color(0, 0, 0, 0));
					}
					g2d.fillRect(movingBlockArray[i][h].getX(), movingBlockArray[i][h].getY(), 28, 28);
				}
			}

		}

	}

	static class PaintCanvasForNextBlock extends Canvas{
		static Cell[][] nextBlockArray = NextBlock.getSheetArray();

		@Override
		public void paint(Graphics g) {
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setStroke(new BasicStroke(1.5f));
			g2d.setColor(Color.black);

			for (int i = 0; i <= 80; i = i + 20) {
				g2d.drawLine(0, 0 + i, 60, 0 + i);
			}
			for (int i = 0; i <= 60; i = i + 20) {
				g2d.drawLine(0 + i, 0, 0 + i, 80);
			}

			//セル配列から情報を読み取りセルに色付け
			for (int i = 0; i < nextBlockArray.length; i++ ) {
				for (int h = 0; h < nextBlockArray[0].length; h++ ) {
					if (nextBlockArray[i][h].getColor() == "red") {
						g2d.setColor(Color.red);
					}else if(nextBlockArray[i][h].getColor() == "green") {
						g2d.setColor(Color.green);
					}else if(nextBlockArray[i][h].getColor() == "blue") {
						g2d.setColor(Color.blue);
					}else if(nextBlockArray[i][h].getColor() == "yellow") {
						g2d.setColor(Color.yellow);
					}else if(nextBlockArray[i][h].getColor() == "black") {
						g2d.setColor(Color.black);
					}else if(nextBlockArray[i][h].getColor() == "orange") {
						g2d.setColor(Color.orange);
					}else if(nextBlockArray[i][h].getColor() == "gray") {
						g2d.setColor(Color.gray);
					}else if(nextBlockArray[i][h].getColor() == "white") {
						g2d.setColor(Color.white);
					}else if(nextBlockArray[i][h].getColor() == "pink") {
						g2d.setColor(Color.pink);
					}
					g2d.fillRect(nextBlockArray[i][h].getX(), nextBlockArray[i][h].getY(), 18, 18);
				}
			}


		}

	}


}
