package components;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import bars.Bar;

/*
 * Classe responsavel por salvar, ler e alterar o arquivo 
 * que salva as configurações e progresso
 */

public class OptionsFile extends File{


	public static OptionsFile file = new OptionsFile(System.getProperty("user.home") + "/settings.cbtn");

	public OptionsFile(String pathname) {super(pathname);}
	
	//Método responsavel por ler o arquivo
	@SuppressWarnings("resource")
	public String readFileLine(int num){

		String line = "";

		try {

			if (!this.exists()) {

				this.createNewFile();

				FileWriter fw = new FileWriter(this.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("Preto\nBranco\nFácil\nzxc\n0");
				bw.close();

			}
			
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			for(int i = 0; i < num; i++) {
				line = br.readLine();
			}

			System.out.println("Read Done!");


		} catch (IOException e) {
			e.printStackTrace();
		}

		return line;
	}
	
	//Método responsavel por sobrescrever o arquivo
	public void writeFile(String content) {
		try {

			if (!this.exists()) {

				this.createNewFile();
			}
			
			if(Bar.recordLabel != null)
				content += Bar.recordLabel.getText();
			else
				content += "0";
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
	public void writeFile(String content, String score) {
		try {

			if (!this.exists()) {

				this.createNewFile();
			}
			
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

	//Método responsavel por salvar o novo record
	public void saveScore(String score) {
		
		String newFile = "";
		
		for(int i = 1; i < 5; i++) {
			newFile += readFileLine(i);
			newFile += "\n";
		}
		
		writeFile(newFile, score);
		
	}
}
