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

	// 더블 버퍼링위한 전체 화면에 대한 이미지를 담는 인스턴스
	private Image screenImage;
	private Graphics screenGraphic;
	private Image IntroBackGround = new ImageIcon(Main.class.getResource("../images/intro.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	private JButton exitButton = new JButton(new ImageIcon(Main.class.getResource("../images/exitButtonbasic.png")));

	private int mouseX, mouseY;
	
	public RhythmBeat() {
		setUndecorated(true);
		setTitle("Do you Know Rhythm");
		// 사이즈 조정
		setSize(Main.S_WIDTH, Main.S_HEIGHT);
		// 인위적으로 사이즈를 줄일수 없다.
		setResizable(false);
		// 실행시 중앙에 뜨도록
		setLocationRelativeTo(null);
		// 게임창을 끄면 프로그램 종료하도록 한다.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 화면 출력
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		// 위치와 크기를 정해준다.
		menuBar.setBounds(0, 0, 1200, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				
			}
		});
		// 메뉴바를 드래그 했을때 게임창의 위치를 바꿔준다.
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

	// 전체 화면 이미지를 매 순간마다 프로그램이 종료 될 때까지 반복한다.
	public void screenDraw(Graphics g) {
		g.drawImage(IntroBackGround, 0, 0, null);
		// 항상 고정 되는 이미지
		paintComponents(g);
		this.repaint();
	}
}
