package beat_4;

import java.awt.Color;
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
	private Image IntroBackGround = new ImageIcon(Main.class.getResource("../images/intro.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	private JButton exitButton = new JButton(new ImageIcon(Main.class.getResource("../images/exitButtonbasic.png")));

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

		
		exitButton.setBounds(50, 50, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		add(exitButton);

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
