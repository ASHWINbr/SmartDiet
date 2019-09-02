package controller;

import java.awt.geom.CubicCurve2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileReadByWord {

	public static String getWorkoutDetails(String path, String type)
			throws IOException {
		String sCurrentLine = "";
		String output = "";
		BufferedReader br = new BufferedReader(new FileReader(path));
		boolean a = false;

		StringBuffer buffer = new StringBuffer();

		while ((sCurrentLine = br.readLine()) != null) {
			if (sCurrentLine.contains("@")) {
				if (sCurrentLine.contains(type)) {
					a = true;
				} else {
					a = false;
				}

			}
			if (a) {
				if (!sCurrentLine.isEmpty()) {
					if (!sCurrentLine.contains("@")) {
						output += sCurrentLine + "\n\n";
					}
				}

			}

		}
		return output;
	}

	public static void main(String[] args) throws Exception {
		String path = "C:\\Users\\gts\\Desktop\\men.txt";
		String workouts = FileReadByWord.getWorkoutDetails(path, "light");
		System.out.println("============" + workouts);
	}

}
