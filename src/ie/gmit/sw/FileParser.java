package ie.gmit.sw;

import java.io.*;

public class FileParser implements Parseable {

	// declare instance variables
	private BufferedReader br = null;
	private BufferedWriter bw = null;
	private String fileRead;
	private String fileWrite;

	public FileParser() {

	}

	public FileParser(String fread, String fwrite) {

		fileRead = fread;
		fileWrite = fwrite;

	}

	@Override
	public void encrypt(String key) throws FileNotFoundException, IOException {

		try {

			// open files
			br = new BufferedReader(new InputStreamReader(new FileInputStream(fileRead), "UTF-8"));
			bw = new BufferedWriter(new FileWriter(new File(fileWrite)));

			key = key.toUpperCase();

			int index = 0;
			int j = ' ';

			long start = System.currentTimeMillis();

			// read in each character n times in terms of time complexity O(n)
			// linear
			while ((j = br.read()) != -1) {

				j = Character.toUpperCase(j);// transform keyword to upper case

				if (j < 'A' || j > 'Z') {

					continue;

				} else {
					// accessing tableau index O(1),charAt of string O(1)
					// so all methods run in constant time

					bw.write(PortaCipher.tableau[(key.charAt(index) - 65) / 2][(j % 65)]);
					index = ++index % key.length();
				}

			}

			long finish = System.currentTimeMillis() - start;

			System.out.println(finish + " milliseconds elapsed to parse a text and write into a file");

		} catch (Exception e) {

			System.err.println(e.getMessage());

		} finally {

			try {// close all streams
				br.close();
				bw.flush();
				bw.close();

			} catch (IOException e) {

				System.out.println("IOException occured can not close file");
			}

		}

	}

	// the same as a method above
	@Override
	public void decrypt(String key) throws FileNotFoundException, IOException {

		try {

			br = new BufferedReader(new InputStreamReader(new FileInputStream(fileRead), "UTF-8"));
			bw = new BufferedWriter(new FileWriter(new File(fileWrite)));

			key = key.toUpperCase();

			int index = 0;
			int j = ' ';

			long start = System.currentTimeMillis();

			while ((j = br.read()) != -1) {

				j = Character.toUpperCase(j);

				if (j < 'A' || j > 'Z') {

					continue;

				} else {

					bw.write(PortaCipher.tableau[(key.charAt(index) - 65) / 2][(j % 65)]);
					index = ++index % key.length();
				}

			}

			long finish = System.currentTimeMillis() - start;

			System.out.println(finish + " milliseconds elapsed to parse a text and write into a file");

		} catch (Exception e) {

			System.err.println(e.getMessage());

		} finally {

			try {
				br.close();
				bw.flush();
				bw.close();

			} catch (IOException e) {

				System.out.println("IOException occured can not close file");
			}

		}

	}

}
