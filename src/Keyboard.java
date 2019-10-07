import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

class Keyboard extends JFrame implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch ( e.getKeyCode() ) {
			case KeyEvent.VK_UP:
				break;

			case KeyEvent.VK_DOWN:
				MovingBlock.moveDown();
				Paint.getCanvas().repaint();
				break;

			case KeyEvent.VK_RIGHT:
			    MovingBlock.moveRight();
				Paint.getCanvas().repaint();
				break;

			case KeyEvent.VK_LEFT:
			    MovingBlock.moveLeft();
				Paint.getCanvas().repaint();
				break;

			case KeyEvent.VK_ENTER:
				break;

			case KeyEvent.VK_SPACE:
			    MovingBlock.rotate();
				Paint.getCanvas().repaint();
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

	}