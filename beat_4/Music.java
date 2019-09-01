package beat_4;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import javazoom.jl.player.Player;

public class Music extends Thread {
	private Player player;
	private boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;

	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/" + name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// 음악이 어떤 위치에 실행되고 있는지 알려줌
	public int getTime() {
		if (player == null)
			return 0;
		return player.getPosition();
	}

	// 곡이 안정적으로 종료되도록
	public void close() {
		isLoop = false;
		player.close();
		this.interrupt(); // 쓰레드를 종료
	}

	@Override
	public void run() {
		try {
			do {
				player.play(); // 곡을 시작
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while (isLoop);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
