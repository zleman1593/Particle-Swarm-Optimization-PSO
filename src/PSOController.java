import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PSOController {
	public static int SizeOfArray = 100;
	public static BufferedWriter outputWriter;
	public static void main(String[] args) throws IOException {
		String root = "/Users/zackleman/Desktop";
		File file = new File(root + "/first" + ".txt");

		// If file does not exists, then create it.
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fileW = new FileWriter(file.getAbsoluteFile());
		outputWriter = new BufferedWriter(fileW);


		
		
		outputWriter.write("gl 12 50000 rok 30");
		outputWriter.newLine();
		ArrayList<ArrayList<Double>> list = get2DArrayList(SizeOfArray);
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("gl", 12, 50000, "rok", 30);
			double test = swarm.start();
			outputWriter.write("" + test);
			outputWriter.newLine();
			addToArray(list, swarm.progress );
		}
		reportProgressStats(list);
		outputWriter.flush();
		outputWriter.close();
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("ri", 12, 50000, "rok", 30);
			swarm.start();
		}
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("vn", 12, 50000, "rok", 30);
			swarm.start();
		}
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("ra", 12, 50000, "rok", 30);
			swarm.start();
		}

		// ******
		outputWriter.newLine();
		outputWriter.write("gl 12 50000 ack 30");
		outputWriter.newLine();
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("gl", 12, 50000, "ack", 30);
			swarm.start();
		}
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("ri", 12, 50000, "ack", 30);
			swarm.start();
		}
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("vn", 12, 50000, "ack", 30);
			swarm.start();
		}
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("ra", 12, 50000, "ack", 30);
			swarm.start();
		}

		// ******
		outputWriter.newLine();
		outputWriter.write("gl 12 50000 ras 30");
		outputWriter.newLine();
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("gl", 12, 50000, "ras", 30);
			swarm.start();
		}
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("ri", 12, 50000, "ras", 30);
			swarm.start();
		}
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("vn", 12, 50000, "ras", 30);
			swarm.start();
		}
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("ra", 12, 50000, "ras", 30);
			swarm.start();
		}

		// ***********************************************
		outputWriter.newLine();
		outputWriter.write("gl 20 50000 rok 30");
		outputWriter.newLine();
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("gl", 20, 50000, "rok", 30);
			swarm.start();
		}
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("ri", 20, 50000, "rok", 30);
			swarm.start();
		}
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("vn", 20, 50000, "rok", 30);
			swarm.start();
		}
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("ra", 20, 50000, "rok", 30);
			swarm.start();
		}

		// ******
		outputWriter.newLine();
		outputWriter.write("gl 20 50000 ack 30");
		outputWriter.newLine();
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("gl", 20, 50000, "ack", 30);
			swarm.start();
		}
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("ri", 20, 50000, "ack", 30);
			swarm.start();
		}
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("vn", 20, 50000, "ack", 30);
			swarm.start();
		}
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("ra", 20, 50000, "ack", 30);
			swarm.start();
		}

		// ******
		outputWriter.newLine();
		outputWriter.write("gl 20 50000 ras 30");
		outputWriter.newLine();
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("gl", 20, 50000, "ras", 30);
			swarm.start();
		}
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("ri", 20, 50000, "ras", 30);
			swarm.start();
		}
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("vn", 20, 50000, "ras", 30);
			swarm.start();
		}
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("ra", 20, 50000, "ras", 30);
			swarm.start();
		}

		// *************************
		outputWriter.newLine();
		outputWriter.write("gl 50 50000 rok 30");
		outputWriter.newLine();
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("gl", 50, 50000, "rok", 30);
			swarm.start();
		}
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("ri", 50, 50000, "rok", 30);
			swarm.start();
		}
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("vn", 50, 50000, "rok", 30);
			swarm.start();
		}
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("ra", 50, 50000, "rok", 30);
			swarm.start();
		}

		// ******
		outputWriter.newLine();
		outputWriter.write("gl 50 50000 ack 30");
		outputWriter.newLine();
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("gl", 50, 50000, "ack", 30);
			swarm.start();
		}
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("ri", 50, 50000, "ack", 30);
			swarm.start();
		}
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("vn", 50, 50000, "ack", 30);
			swarm.start();
		}
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("ra", 50, 50000, "ack", 30);
			swarm.start();
		}

		// ******

		outputWriter.newLine();
		outputWriter.write("gl 50 50000 ras 30");
		outputWriter.newLine();
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("gl", 50, 50000, "ras", 30);
			swarm.start();
		}
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("ri", 50, 50000, "ras", 30);
			swarm.start();
		}
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("vn", 50, 50000, "ras", 30);
			swarm.start();
		}
		for (int i = 0; i < 50; i++) {
			PSO swarm = new PSO("ra", 50, 50000, "ras", 30);
			swarm.start();
		}

		outputWriter.flush();
		outputWriter.close();
	}

	private static Double[] toArray(ArrayList<Double> result) {

		Double[] resultArray = new Double[result.size()];
		resultArray = result.toArray(resultArray);
		return resultArray;
	}

	private static ArrayList<ArrayList<Double>> get2DArrayList(int dimensions) {
		ArrayList<ArrayList<Double>> masterList = new ArrayList<ArrayList<Double>>();

		for (int i = 0; i < dimensions; i++) {
			ArrayList<Double> list = new ArrayList<Double>();
			masterList.add(list);
		}

		return masterList;

	}

	private static ArrayList<ArrayList<Double>> addToArray(ArrayList<ArrayList<Double>> result, ArrayList<Double> toAdd) {

		for (int i = 0; i < toAdd.size(); i++) {
			result.get(i).add(toAdd.get(i));

		}

		return result;

	}
	
	
	private static void reportProgressStats(ArrayList<ArrayList<Double>> array) throws IOException{
		outputWriter.write("Progress Median:");
		outputWriter.newLine();
		for (int i = 0; i < array.size(); i++){
			
		double median =  QuickSelect.select(toArray(array.get(i)), array.get(i).size()/2);

		outputWriter.write("" + median);
		outputWriter.newLine();
		}
	}

}
