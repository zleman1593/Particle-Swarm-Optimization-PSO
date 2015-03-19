
public class PSOCommandLineController {


	public static void main(String[] args) {
		
		
		
		PSO swarm = new PSO(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3],
				Integer.parseInt(args[4]));
		
		swarm.start();

	}


}
