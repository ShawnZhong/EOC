import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.Format;

public class LissajousCurve extends Frame {
	Format format = new DecimalFormat("0.000");
	Font font1 = new Font("Microsoft YaHei", Font.BOLD, 20);
	Font font2 = new Font("Microsoft YaHei", Font.BOLD, 12);
	Image image = null;

	double d = 1;
	double a = 1;
	double delta = 0.025;
	boolean auto;
	int cr = 0;
	int cg = 254;
	int cb = 128;
	double changer = 1;
	double changeg = 1.1;
	double changeb = 1.2;

	public static void main(String[] args) {
		new LissajousCurve().launchFrame();
	}

	public void launchFrame() {
		setSize(750, 750);
		setLocation(100, 100);
		setVisible(true);
		thread.start();
		addWindowListener(windowClosing);
		addKeyListener(keyListener);
	}

	public void paint(Graphics g) {
		g.fillRect(0, 0, 750, 750);
		cr += changer;
		cg += changeg;
		cb += changeb;
		if (cr < 128 || cr > 254) {
			changer = -changer;
		}
		if (cg < 128 || cg > 254) {
			changeg = -changeg;
		}
		if (cb < 128 || cb > 254) {
			changeb = -changeb;
		}
		Color color = new Color(cr, cg, cb);

		g.setColor(color);
		for (double i = 0; i <= 2 * Math.PI; i += 1 / 1000.0) {
			g.fillOval((int) (300 * Math.sin(a * i)) + 375,
					(int) (300 * Math.cos((a + d) * i)) + 375, 7, 7);
		}

		if (auto) {
			a += delta;
			if (a > 10 || a < -10)
				delta = -delta;
		}

		g.setColor(Color.white);
		g.setFont(font1);
		g.drawString(
				"a = " + format.format(a) + "      b = " + format.format(a + d)
						+ "      b - a = " + format.format(d), 75, 60);

		g.setFont(font2);
		g.drawString("Up :       d+=.025", 10, 590);
		g.drawString("Down :    d-=.025", 10, 605);
		g.drawString("Left :      a-=.025", 10, 620);
		g.drawString("Right :    a+=.025", 10, 635);
		g.drawString("W :         d++", 10, 665);
		g.drawString("S :          d--", 10, 680);
		g.drawString("A :          a--", 10, 695);
		g.drawString("D :          a++", 10, 710);
		g.drawString("Space :     auto run", 10, 740);
	}

	public void update(Graphics g) {
		if (image == null)
			image = this.createImage(750, 750);
		paint(image.getGraphics());
		g.drawImage(image, 0, 0, null);
	}

	Thread thread = new Thread() {
		public void run() {
			while (true) {
				repaint();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};

	WindowAdapter windowClosing = new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	};

	KeyAdapter keyListener = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if (a > -10)
					a -= delta;
				break;
			case KeyEvent.VK_UP:
				if (d < 10)
					d += delta;
				break;
			case KeyEvent.VK_RIGHT:
				if (a < 10)
					a += delta;
				break;
			case KeyEvent.VK_DOWN:
				if (d > -10)
					d -= delta;
				break;
			case KeyEvent.VK_W:
				if (d < 10)
					d = (int) (d + 1);
				break;
			case KeyEvent.VK_S:
				if (d > -10)
					d = (int) (d - 1);
				break;
			case KeyEvent.VK_A:
				if (a > -10)
					a = (int) (a - 1);
				break;
			case KeyEvent.VK_D:
				if (a < 10)
					a = (int) (a + 1);
				break;
			case KeyEvent.VK_SPACE:
				auto = !auto;
			}
		}
	};
}