package Day5Example;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame extends Frame {
	public void launchFrame() {
		setSize(750, 750);
		setLocation(100, 100);
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public void paint(Graphics g) {
		for (double t = -30; t < 30; t += .01) {
			double x = t;
			double y = t * t;
			g.fillOval((int) (x * 20) + 375, (int) (-y * 20) + 375, 5, 5);
		}
	}
}
