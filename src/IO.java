import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.TreeMap;

public class IO {

	private static IO instance = new IO();

	public static IO getInstance() {
		// TODO Auto-generated method stub
		return instance;
	}


	public StringBuilder read(File file) {
		StringBuilder builder = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(builder.toString());
		return builder;
	}

	public void write(File file, TreeMap<String, String[]> map) {
		// TODO Auto-generated method stub
		try {
			PrintWriter writer = new PrintWriter(file);

			while (!map.isEmpty()) {
				Entry<String, String[]> entry = map.pollFirstEntry();
				writer.print(entry.getKey());

				for (String s : entry.getValue()) {
					writer.print("," + (s == null ? "" : s));
				}

				writer.println();
			}

			writer.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void write(File file, StringBuilder builder) {
		// TODO Auto-generated method stub
		try {
			PrintWriter writer = new PrintWriter(file);

			writer.println(builder);

			writer.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
