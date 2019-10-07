import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Button {
	private static JButton startButton = new JButton("START");
	private static JButton rotateButton = new JButton("◯️");
	private static JButton downButton = new JButton("↓️");
	private static JButton leftButton = new JButton("←️");
	private static JButton rightButton = new JButton("→️");

	static JButton startButton() {
		getStartButton().setBounds(100, 5, 100, 50);
		getStartButton().addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						MovingBlock.resetSheetArray();
						DeadBlock.resetSheetArray();
						Paint.getCanvas().repaint();
						SingleThread t = new SingleThread();
						t.startRunning();
						t.start();
						getStartButton().setEnabled(false);
						Paint.getLabel().setText(String.valueOf(0));
					}
				}
				);
		return getStartButton();
	}

	static JButton rotateButton() {
		rotateButton.setBounds(280, 5, 50, 50);
		rotateButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						MovingBlock.rotate();
						Paint.getCanvas().repaint();
					}
				}
				);
		return rotateButton;
	}

	static JButton downButton() {
		downButton.setBounds(280, 55, 50, 45);
		downButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						MovingBlock.moveDown();
						Paint.getCanvas().repaint();
					}
				}
				);
		return downButton;
	}

	static JButton leftButton() {
		leftButton.setBounds(230, 30, 50, 50);
		leftButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						MovingBlock.moveLeft();
						Paint.getCanvas().repaint();
					}
				}
				);
		return leftButton;
	}

	static JButton rightButton() {
		rightButton.setBounds(330, 30, 50, 50);
		rightButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						MovingBlock.moveRight();
						Paint.getCanvas().repaint();
					}
				}
				);
		return rightButton;
	}

	public static JButton getStartButton() {
		return startButton;
	}

}
