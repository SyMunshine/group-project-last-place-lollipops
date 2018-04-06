import java.awt.event.MouseEvent;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Supplier;
import java.util.stream.Stream;

import acm.graphics.GObject;

public class LeaderboardsPane extends GraphicsPane {
	private MainApplication program; // you will use program to get access to
										// all of the GraphicsProgram calls
	//private Score[] leaderboards;
	private GParagraph topTen;
	private GButton back;
	private FileReader in = null;
	private FileWriter out = null;
	private BufferedReader reader;
	private String line;
	private String[] splitline;
	
	public LeaderboardsPane(MainApplication app) {
		this.program = app;
		topTen = new GParagraph("", 200, 200);
		back = new GButton("Back", 200, 400, 200, 100);
		//leaderboards = new Score[11];
		Score[] leaderboards;
		leaderboards = getLeaders();
		
		//leaderboards[11] = playerScore;
		
		Arrays.sort(leaderboards, Collections.reverseOrder());
		displayLeaders(leaderboards);
		writeLeaders(leaderboards);
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
	}
	
	public void writeLeaders(Score[] l) {
		try {
			out = new FileWriter(System.getProperty("user.dir")+"/leaders.txt"); //Update relative filepath to write to same file instead of creating new one
		}catch(IOException e) {
			e.printStackTrace();
		}
		for(int i=0;i<10;i++) {
			try {
				out.write(l[i].getName() + " " + l[i].getScore() + "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			out.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		System.out.println("file written");
	}
	
	public void displayLeaders(Score[] l) {
		for(int i=0;i<10;i++) {
			topTen.addText(l[i].getName() + " " + Integer.toString(l[i].getScore()) + "\n");
		}
	}
	
	
	public Score[] getLeaders() {
		Score[] scores = new Score[11];
		scores = Stream.generate(() -> new Score()).limit(11).toArray(Score[]::new);
		try {
			in = new FileReader(System.getProperty("user.dir")+"/leaders.txt");
			reader = new BufferedReader(in);
		}catch(FileNotFoundException ex) {
			System.out.println("Could not open leaderboards.");
			ex.printStackTrace();
		}
		for(int i=0;i<10;i++) {
			try {
				line = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				splitline = line.split(" ");
				scores[i].setName(splitline[0]);
				scores[i].setScore(Integer.valueOf(splitline[1]));
			}catch(NullPointerException ex) {
				//System.out.println("less than 10 scores.");
			}
			
		}
		try {
			reader.close();
			in.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return scores;
	}
	
	@Override
	public void showContents() {
		program.add(topTen);
		program.add(back);
	}

	@Override
	public void hideContents() {
		program.remove(topTen);
		program.remove(back);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == back) {
			program.switchToMenu();
		}

	}

}