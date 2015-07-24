package structure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager extends File{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static FileManager file = new FileManager(System.getProperty("user.home") + "/settings.cbtn");
	
	private Settings settingsFile = null;
	private String recordFile = "";
	
	private FileManager(String pathname) {
		super(pathname);
		
		try {

			if (!this.exists()) {

				this.createNewFile();

				FileWriter fw = new FileWriter(this.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("Fácil\nPreto\nBranco\nzxc\n0");
				bw.close();
				
				settingsFile = new Settings("Fácil", "Preto", "Branco", "zxc");
				recordFile = "0";

			} else {
				System.out.println("File Exists!");
				
				settingsFile = new Settings(readFileLine(1), readFileLine(2), readFileLine(3), readFileLine(4));
				recordFile = readFileLine(5);				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	public Settings getSettingsFile() {
		return settingsFile;
	}



	public String getRecordFile() {
		return recordFile;
	}



	//Método responsavel por ler o arquivo
	@SuppressWarnings("resource")
	public String readFileLine(int num){

		String line = "";

		try {
			FileReader fr = new FileReader(this);
			BufferedReader br = new BufferedReader(fr);

			for(int i = 0; i < num; i++) {
				line = br.readLine();
			}

//			System.out.println(line);


		} catch (IOException e) {
			e.printStackTrace();
		}

		return line;
	}

	//Método responsavel por sobrescrever o arquivo
	public void writeFile(Settings s) {
		try {

			if (!this.exists()) {

				this.createNewFile();
			
			}
			
			settingsFile = s;
			
			String content = settingsFile.exportSettings();
			content += readFileLine(5);

			FileWriter fw = new FileWriter(this.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

			System.out.println("Write Done!");


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Método responsavel por apenas alterar o record
	public void writeFile(Settings s, String score) {
		try {

			if (!this.exists()) {

				this.createNewFile();
			}

			String content = s.exportSettings();
			content += score;

			FileWriter fw = new FileWriter(this.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

			System.out.println("Write Done!");


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
