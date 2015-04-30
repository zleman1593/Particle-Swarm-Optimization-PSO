import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PSOController {
	public static int SizeOfArray = 100;
	public static String root = "/Users/zackleman/Desktop/out";


	public static void main(String[] args) throws IOException {
		//Run all the test
		test();
	}
	
	private static void test() throws IOException{
		
		Runnable r1 = new Runnable() {
			public void run() {
			
				File file = new File(root + "/first" + ".txt");
					try{
				// If file does not exists, then create it.
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fileW = new FileWriter(file.getAbsoluteFile());
				BufferedWriter outputWriter = new BufferedWriter(fileW);

				outputWriter.write("gl 12 50000 rok 30");
				outputWriter.newLine();
				outputWriter.newLine();
				ArrayList<ArrayList<Double>> list = get2DArrayList(SizeOfArray);
				
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("gl", 12, 50000, "rok", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				outputWriter.write("ri 12 50000 rok 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("ri", 12, 50000, "rok", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				outputWriter.write("vn 12 50000 rok 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("vn", 12, 50000, "rok", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				outputWriter.write("ra 12 50000 rok 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("ra", 12, 50000, "rok", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				outputWriter.flush();
				outputWriter.close();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
					
				
			};
			
		};
		
		
		Runnable r2 = new Runnable() {
			public void run() {
				try{

				File file = new File(root + "/second" + ".txt");

				// If file does not exists, then create it.
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fileW = new FileWriter(file.getAbsoluteFile());
				BufferedWriter outputWriter = new BufferedWriter(fileW);
				
				// ******

				outputWriter.write("gl 12 50000 ack 30");
				outputWriter.newLine();
				outputWriter.newLine();
				ArrayList<ArrayList<Double>> list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("gl", 12, 50000, "ack", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				outputWriter.write("ri 12 50000 ack 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("ri", 12, 50000, "ack", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				outputWriter.write("vn 12 50000 ack 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("vn", 12, 50000, "ack", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				
				outputWriter.write("ra 12 50000 ack 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("ra", 12, 50000, "ack", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				
				outputWriter.flush();
				outputWriter.close();
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
			
		};
		
		Runnable r3 = new Runnable() {
			public void run() {
				try{
				File file = new File(root + "/third" + ".txt");

				// If file does not exists, then create it.
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fileW = new FileWriter(file.getAbsoluteFile());
				BufferedWriter outputWriter = new BufferedWriter(fileW);
			
				// ******

				outputWriter.write("gl 12 50000 ras 30");
				outputWriter.newLine();
				outputWriter.newLine();
				ArrayList<ArrayList<Double>> list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("gl", 12, 50000, "ras", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				outputWriter.write("ri 12 50000 ras 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("ri", 12, 50000, "ras", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				outputWriter.write("ri 12 50000 ras 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("vn", 12, 50000, "ras", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				
				list = get2DArrayList(SizeOfArray);
				outputWriter.write("ra 12 50000 ras 30");
				outputWriter.newLine();
				outputWriter.newLine();
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("ra", 12, 50000, "ras", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				

				// ***********************************************
				
				outputWriter.write("gl 20 50000 rok 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("gl", 20, 50000, "rok", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				outputWriter.write("ri 20 50000 rok 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("ri", 20, 50000, "rok", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				outputWriter.write("vn 20 50000 rok 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("vn", 20, 50000, "rok", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				
				outputWriter.write("ra 20 50000 rok 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("ra", 20, 50000, "rok", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				

				outputWriter.flush();
				outputWriter.close();
				
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			};
			
		};
		
		Runnable r4 = new Runnable() {
			public void run() {
				try{
				File file = new File(root + "/fourth" + ".txt");

				// If file does not exists, then create it.
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fileW = new FileWriter(file.getAbsoluteFile());
				BufferedWriter outputWriter = new BufferedWriter(fileW);
				// ******

				outputWriter.write("gl 20 50000 ack 30");
				outputWriter.newLine();
				outputWriter.newLine();
				ArrayList<ArrayList<Double>> list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("gl", 20, 50000, "ack", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				outputWriter.write("ri 20 50000 ack 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("ri", 20, 50000, "ack", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				outputWriter.write("vn 20 50000 ack 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("vn", 20, 50000, "ack", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				outputWriter.write("ra 20 50000 ack 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("ra", 20, 50000, "ack", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				

				outputWriter.flush();
				outputWriter.close();
				
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			};
			
		};
		
		Runnable r5 = new Runnable() {
			public void run() {
				
				try{
				File file = new File(root + "/fifth" + ".txt");

				// If file does not exists, then create it.
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fileW = new FileWriter(file.getAbsoluteFile());
				BufferedWriter outputWriter = new BufferedWriter(fileW);

				// ******

				outputWriter.write("gl 20 50000 ras 30");
				outputWriter.newLine();
				outputWriter.newLine();
				ArrayList<ArrayList<Double>>	list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("gl", 20, 50000, "ras", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				outputWriter.write("ri 20 50000 ras 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("ri", 20, 50000, "ras", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				
				outputWriter.write("vn 20 50000 ras 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("vn", 20, 50000, "ras", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				outputWriter.write("ra 20 50000 ras 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("ra", 20, 50000, "ras", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
			

				// *************************

				outputWriter.write("gl 50 50000 rok 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("gl", 50, 50000, "rok", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				outputWriter.write("ri 50 50000 rok 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("ri", 50, 50000, "rok", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				
				outputWriter.write("vn 50 50000 rok 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("vn", 50, 50000, "rok", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				
				outputWriter.write("ra 50 50000 rok 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("ra", 50, 50000, "rok", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				outputWriter.flush();
				outputWriter.close();
				
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			};
			
		};
		
		Runnable r6 = new Runnable() {
			public void run() {
				try{
				File file = new File(root + "/sixth"
						+ "" + ".txt");

				// If file does not exists, then create it.
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fileW = new FileWriter(file.getAbsoluteFile());
				BufferedWriter outputWriter = new BufferedWriter(fileW);
			
				// ******

				outputWriter.write("gl 50 50000 ack 30");
				outputWriter.newLine();
				outputWriter.newLine();
				ArrayList<ArrayList<Double>>	list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("gl", 50, 50000, "ack", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				outputWriter.write("ri 50 50000 ack 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("ri", 50, 50000, "ack", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				outputWriter.write("vn 50 50000 ack 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("vn", 50, 50000, "ack", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				
				outputWriter.write("ra 50 50000 ack 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("ra", 50, 50000, "ack", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				outputWriter.flush();
				outputWriter.close();
				
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			};
			
		};
		
		Runnable r7 = new Runnable() {
			public void run() {
				try{
				File file = new File(root + "/seventh" + ".txt");

				// If file does not exists, then create it.
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fileW = new FileWriter(file.getAbsoluteFile());
				BufferedWriter outputWriter = new BufferedWriter(fileW);
				// ******


				outputWriter.write("gl 50 50000 ras 30");
				outputWriter.newLine();
				outputWriter.newLine();
				ArrayList<ArrayList<Double>> list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("gl", 50, 50000, "ras", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				outputWriter.write("ri 50 50000 ras 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("ri", 50, 50000, "ras", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				outputWriter.write("vn 50 50000 ras 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("vn", 50, 50000, "ras", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				
				
				outputWriter.write("ra 50 50000 ras 30");
				outputWriter.newLine();
				outputWriter.newLine();
				list = get2DArrayList(SizeOfArray);
				for (int i = 0; i < 50; i++) {
					PSO swarm = new PSO("ra", 50, 50000, "ras", 30);
					double test = swarm.run();
					outputWriter.write("" + test);
					outputWriter.newLine();
					addToArray(list, swarm.progress);
				}
				reportProgressStats(list,outputWriter);
				outputWriter.flush();
				outputWriter.close();
				
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			};
			
		};
		
	
		//Starts the 8 threads
		Thread thr1 = new Thread(r1);
		//Thread thr2 = new Thread(r2);
		Thread thr3 = new Thread(r3);
		//Thread thr4 = new Thread(r4);
		Thread thr5 = new Thread(r5);
		//	Thread thr6 = new Thread(r6);
		Thread thr7 = new Thread(r7);

		thr1.start();
//		thr2.start();
		thr3.start();
//		thr4.start();
		thr5.start();
//		thr6.start();
		thr7.start();
	
		try {
		thr1.join();
//		thr2.join();
		thr3.join();
//		thr4.join();
		thr5.join();
//		thr6.join();
		thr7.join();

	
		} catch (InterruptedException e) {
		e.printStackTrace();
		
		}
	

	

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

	private static void reportProgressStats(ArrayList<ArrayList<Double>> array, BufferedWriter outputWriter) throws IOException {
		outputWriter.newLine();
		outputWriter.write("Progress Median:");
		outputWriter.newLine();
		for (int i = 0; i < array.size(); i++) {

			double median = QuickSelect.select(toArray(array.get(i)), array.get(i).size() / 2);

			outputWriter.write("" + median);
			outputWriter.newLine();
		}
	}

}
