public class MainApplication extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	public static final String MUSIC_FOLDER = "sounds";
	private static final String[] SOUND_FILES = { "r2d2.mp3", "somethinlikethis.mp3" };

	private SomePane somePane;
	private MenuPane menu;
	private PausePane pause;
	private SettingsPane settings;
	private InstructionsPane instructions;
	private LeaderboardsPane leaderboards;

	private int count;
	
	public boolean volume = true;

	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		System.out.println("Hello, world!");
		somePane = new SomePane(this);

		pause = new PausePane(this);
		settings = new SettingsPane(this);
		instructions = new InstructionsPane(this);
		leaderboards = new LeaderboardsPane(this);

		menu = new MenuPane(this);
		switchToMenu();
	}

	public void switchToMenu() {
		//playRandomSound();
		count++;
		switchToScreen(menu);
		playMenuMusic();
		
	}

	public void switchToSome() {
		//playRandomSound();
		switchToScreen(somePane);
		pauseMenuMusic();
		playGameMusic();
		
	}
	
	public void switchToSettings() {
		switchToScreen(settings);
	}
	
	public void switchToInstructions() {
		switchToScreen(instructions);
	}
	
	public void switchToLeaderboards() {
		switchToScreen(leaderboards);
	}

	private void playRandomSound() {
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.playSound(MUSIC_FOLDER, SOUND_FILES[count % SOUND_FILES.length]);
	}
	
	public void playMenuMusic() {
		if (!volume) return;
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.playSound("", "lobby.mp3");
	}
	public void pauseMenuMusic() {
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.pauseSound("", "lobby.mp3");
	}
	public void playGameMusic() {
		if (!volume) return;
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.playSound("", "gameMusic.mp3");
	}
	public void pauseGameMusic() {
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.pauseSound("", "gameMusic.mp3");
	}
}
