import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

class Mouse extends JFrame implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getPoint().x / 30 * 30 + 1;
			int y = e.getPoint().y / 30 * 30 + 1;
			Paint.getCanvas().repaint();
		}
		@Override
		public void mousePressed(MouseEvent e) {
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
	}