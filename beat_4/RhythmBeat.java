package beat_4;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class RhythmBeat extends JFrame {

	// ���� ���۸����� ��ü ȭ�鿡 ���� �̹����� ��� �ν��Ͻ�
	private Image screenImage;
	private Graphics screenGraphic;
	
	
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));

	private Image IntroBackGround = new ImageIcon(Main.class.getResource("../images/intro.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	private JButton exitButton = new JButton(exitButtonBasicImage);

	private int mouseX, mouseY;
	
	public RhythmBeat() {
		setUndecorated(true);
		setTitle("Do you Know Rhythm");
		// ������ ����
		setSize(Main.S_WIDTH, Main.S_HEIGHT);
		// ���������� ����� ���ϼ� ����.
		setResizable(false);
		// ����� �߾ӿ� �ߵ���
		setLocationRelativeTo(null);
		// ����â�� ���� ���α׷� �����ϵ��� �Ѵ�.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ȭ�� ���
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		exitButton.setBounds(1170, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("click.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("click.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				} catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitButton);
		
		// ��ġ�� ũ�⸦ �����ش�.
		menuBar.setBounds(0, 0, 1200, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				
			}
		});
		// �޴��ٸ� �巡�� ������ ����â�� ��ġ�� �ٲ��ش�.
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y -mouseY );
			}
		});
		add(menuBar);


		Music introMusic = new Music("BackGroundMusic.mp3", true);
		introMusic.start();
	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.S_WIDTH, Main.S_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	// ��ü ȭ�� �̹����� �� �������� ���α׷��� ���� �� ������ �ݺ��Ѵ�.
	public void screenDraw(Graphics g) {
		g.drawImage(IntroBackGround, 0, 0, null);
		// �׻� ���� �Ǵ� �̹���
		paintComponents(g);
		this.repaint();
	}
}
