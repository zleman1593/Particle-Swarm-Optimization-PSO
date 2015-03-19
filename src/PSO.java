/*
Zackery Leman, Min "Ivy" Xing, Alana Weinstein
 Particle Swarm Optimization 

 Adapted from Stephen Majercik
 2013 April 6

 */

import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//Notes: make sure to update global appropriately
//Remove the random while loops as they are uneeded
//Change height and width to be dynamic
//Change k to be taken as a prameter

public class PSO {

	// for random numbers
	private Random rand = new Random();

	private int width = 4;
	private int height = 3;
	private int k = 5;

	// **************** PSO ******************
	private int dimensions;
	// number of particles in the swarm
	private int numParticles = 12;

	// personal best acceleration coefficient
	private double phi1 = 2.05;
	// global best acceleration coefficient
	private double phi2 = 2.05;

	// Construction factor
	private double phi = phi1 + phi2;
	public double constrictionFactor = 2.0 / (phi - 2.0 + Math.sqrt(phi * phi - 4.0 * phi));

	// test functions
	private final int SPHERE_FUNCTION_NUM = 1;
	private final int ROSENBROCK_FUNCTION_NUM = 2;
	private final int ACKLEY_FUNCTION_NUM = 3;
	private final int RASTRIGIN_FUNCTION_NUM = 4;

	// neighborhoods
	private final int GLOBAL = 1;
	private final int VON = 2;
	private final int RING = 3;
	private final int RANDOM = 4;
	// which neighborhood to test
	public int neighborhood = 1;

	// for controlling termination
	private int iterationNum = 0;
	private int maxIterations = 50;
	// which one to test
	public int testFunction = 1;

	// **************** Particle info ******************

	// x and y, z etc. positions for each particle
	private ArrayList<double[]> position = new ArrayList<double[]>();

	// x and y velocities for each particle
	private ArrayList<double[]> velocity = new ArrayList<double[]>();

	// pBest positions and values for each particle
	private ArrayList<double[]> pBestDimensionPos = new ArrayList<double[]>();

	private double[] pBestValue;

	// **************Global N********************
	// gbest position and value
	private ArrayList<Double> gBestDimensionPos = new ArrayList<Double>();
	private double gBestValue;

	// **********************************



	/* Constructor */
	public PSO(String neighborhood, int swarmSize, int maxIterations, String function, int dimensions) {
		this.numParticles = swarmSize;
		this.maxIterations = maxIterations;
		this.dimensions = dimensions;
		if (neighborhood.equalsIgnoreCase("gl")) {
			this.neighborhood = GLOBAL;
		} else if (neighborhood.equalsIgnoreCase("ri")) {
			this.neighborhood = RING;
		} else if (neighborhood.equalsIgnoreCase("vn")) {
			this.neighborhood = VON;
		} else if (neighborhood.equalsIgnoreCase("ra")) {
			this.neighborhood = RANDOM;

		}

		if (function.equalsIgnoreCase("sp")) {
			this.testFunction = SPHERE_FUNCTION_NUM;
		} else if (function.equalsIgnoreCase("rok")) {
			this.testFunction = ROSENBROCK_FUNCTION_NUM;
		} else if (function.equalsIgnoreCase("ack")) {
			this.testFunction = ACKLEY_FUNCTION_NUM;
		} else if (function.equalsIgnoreCase("ras")) {
			this.testFunction = RASTRIGIN_FUNCTION_NUM;
		}

		for (int i = 0; i < dimensions; i++) {
			pBestDimensionPos.add(new double[numParticles]);
			gBestDimensionPos.add(0.0);
		}

		for (int i = 0; i < dimensions; i++) {
			// create arrays for particle positions
			position.add(new double[numParticles]);
			// create arrays for particle velocities
			velocity.add(new double[numParticles]);
			// create arrays for particle personal bests
			pBestDimensionPos.add(new double[numParticles]);

		}
		// create arrays for particle personal bests
		pBestValue = new double[numParticles];

		// set gbest value very high so it will be replaced in the loop
		// that creates the particles
		gBestValue = Double.MAX_VALUE;
		
		if (numParticles == 12){
			width = 3;
			height = numParticles/width;
		}else if(numParticles == 20){
			width = 4;
			height = numParticles/width;
		}
		else if(numParticles == 50){
			width = 5;
			height = numParticles/width;
		} else{
			width = 3;
			height = numParticles/width;
		}

	}

	public void start() {

		// create particles and calculate initial personal bests;
		// find the initial global best
		for (int p = 0; p < numParticles; p++) {

			// set the coordinates and get the value of the objective function
			// at that point

			for (int i = 0; i < dimensions; i++) {

				double initPosition = 0;

				// TODO: Change these so they are random doubles
				switch (testFunction) {
				case 1:
					initPosition = rand.nextInt(15) + 15;
					while (initPosition < 15 || initPosition > 30) {
						initPosition = rand.nextInt(15) + 15;
					}
					break;
				case 2:
					initPosition = rand.nextInt(15) + 15;
					while (initPosition < 15 || initPosition > 30) {
						initPosition = rand.nextInt(15) + 15;
					}
					break;
				case 3:

					initPosition = rand.nextInt(16) + 16;
					while (initPosition < 16 || initPosition > 32) {
						initPosition = rand.nextInt(16) + 16;
					}
					break;

				case 4:
					initPosition = 5.12 + (5.12 - 2.56) * rand.nextDouble();
					while (initPosition < 2.56 || initPosition > 5.12) {
						initPosition = 2.56 + (5.12 - 2.56) * rand.nextDouble();
					}
					break;
				default:
					break;
				}

				position.get(i)[p] = initPosition;
			}

			// initial value
			double currValue = eval(testFunction, position, p);

			// initialize velocities
			for (int i = 0; i < dimensions; i++) {
				double initVelocity = 0;

				switch (testFunction) {
				case 1:
					initVelocity = rand.nextInt(5) - 2;
					while (initVelocity < -2 || initVelocity > 2) {
						initVelocity = rand.nextInt(5) - 2;
					}
					break;
				case 2:
					initVelocity = rand.nextInt(5) - 2;
					while (initVelocity < -2 || initVelocity > 2) {
						initVelocity = rand.nextInt(5) - 2;
					}
					break;
				case 3:
				case 4:
					initVelocity = rand.nextInt(7) - 2;
					while (initVelocity < -2 || initVelocity > 4) {
						initVelocity = rand.nextInt(7) - 2;
					}
					break;
				default:
					break;
				}

				velocity.get(i)[p] = initVelocity;
			}

			// ****** store initial personal best in the pBest arrays provided
			pBestValue[p] = currValue;
			for (int i = 0; i < dimensions; i++) {
				pBestDimensionPos.get(i)[p] = position.get(i)[p];
			}

			// ****** check for new global best and store, if necessary, in the
			// variables provided
			if (currValue < gBestValue) {
				gBestValue = currValue;
				for (int i = 0; i < dimensions; i++) {
					gBestDimensionPos.set(i, position.get(i)[p]);
				}
			}

		}
		while (iterationNum < maxIterations) {
			System.out.println("iteration " + iterationNum + " position value: " + gBestDimensionPos.get(0) + " "
					+ gBestDimensionPos.get(1) + "  gbest value = " + gBestValue);
			update();
			iterationNum++;
		}
		System.out.println("DONE!!");
	}

	public void update() {

		// update all the particles
		for (int p = 0; p < numParticles; p++) {

			double newValue;
			double accPersDimensionPos[] = new double[dimensions];
			double accNDimensionPos[] = new double[dimensions];

			// Iterate over all the dimensions
			for (int i = 0; i < dimensions; i++) {
				// ****** compute the acceleration due to personal best
				double smallRandomNumber1 = phi1 * rand.nextDouble();
				accPersDimensionPos[i] = smallRandomNumber1 * (pBestDimensionPos.get(i)[p] - position.get(i)[p]);

				// ****** compute the acceleration due to global best
				double bestPosForNeighborhood = neighborhoodBest(p, i);
				double smallRandomNumber2 = phi2 * rand.nextDouble();
				accNDimensionPos[i] = smallRandomNumber2 * (bestPosForNeighborhood - position.get(i)[p]);

				// ****** constrict the new velocity and reset the current
				// velocity
				velocity.get(i)[p] = constrictionFactor
						* (velocity.get(i)[p] + accPersDimensionPos[i] + accNDimensionPos[i]);

				// ****** update the position
				position.get(i)[p] = position.get(i)[p] + velocity.get(i)[p];

			}

			// ****** find the value of the new position
			newValue = eval(testFunction, position, p);

			// ****** update personal best and global best, if necessary

			if (newValue < pBestValue[p]) {
				pBestValue[p] = newValue;

				for (int i = 0; i < dimensions; i++) {
					pBestDimensionPos.get(i)[p] = position.get(i)[p];
				}

			}
			if (newValue < gBestValue) {
				gBestValue = newValue;
				for (int i = 0; i < dimensions; i++) {
					gBestDimensionPos.set(i, position.get(i)[p]);
				}

			}

		}

	}

	private double neighborhoodBest(int particle, int dimension) {
		double bestValue = 0;
		switch (neighborhood) {
		case 1:
			bestValue = gBestDimensionPos.get(dimension);
			break;
		case 2:
			bestValue = VN(particle, dimension);
			break;
		case 3:
			bestValue = ring(particle, dimension);
			break;
		case 4:
			bestValue = random(particle, dimension);
			break;
		}
		return bestValue;
	}

	private double VN(int particle, int dimension) {

		double minValue = pBestValue[particle];
		double position = pBestDimensionPos.get(dimension)[particle];
		
		int tempPosition = VNLeft(particle);
		double tempValue = pBestValue[tempPosition]; 
		if (minValue > tempValue) {
			minValue = tempValue;
			position = pBestDimensionPos.get(dimension)[tempPosition];
		}
		 tempPosition = VNRight(particle);
		 tempValue = pBestValue[tempPosition];
		if (minValue > tempValue) {
			minValue = tempValue;
			position = pBestDimensionPos.get(dimension)[tempPosition];
		}
		 tempPosition = VNUp(particle);
		 tempValue = pBestValue[tempPosition];
		if (minValue > tempValue) {
			minValue = tempValue;
			position = pBestDimensionPos.get(dimension)[tempPosition];
		}

		 tempPosition = VNDown(particle);
		 tempValue = pBestValue[tempPosition];
		if (minValue > tempValue) {
			minValue = tempValue;
			position = pBestDimensionPos.get(dimension)[tempPosition];
		}

		return position;
	}

	private int VNLeft(int particle) {
		if (particle % width == 0) {
			return particle + (width - 1);
		}
		return  particle - 1;
	}

	private int VNRight(int particle) {
		if ((particle + 1) % width == 0) {
			return particle - (width - 1);
		}
		return particle + 1;
	}

	private int VNUp(int particle) {
		if (particle >= width) {
			return particle - width;
		}
		return (pBestValue.length - width) + particle;
	}

	private int VNDown(int particle) {
		if (particle >= pBestValue.length - width) {
			return particle % width;
		}
		return particle + width;
	}

	private double random(int particle, int dimension) {
		ArrayList<Integer> randomNumbers = new ArrayList<Integer>();
		double minValue = pBestValue[particle];
		double position = pBestDimensionPos.get(dimension)[particle];
		randomNumbers.add(particle);

		while (randomNumbers.size() < k) {
			int number;
			do {
				number = rand.nextInt(numParticles);
			} while (randomNumbers.contains(number));
			randomNumbers.add(number);
			if(minValue > pBestValue[number]){
				 minValue = pBestValue[number];
				 position = pBestDimensionPos.get(dimension)[number];
			}
		}

		return position;
	}

	private double ring(int particle, int dimension) {
		double minValue = pBestValue[particle];
		double position = pBestDimensionPos.get(dimension)[particle];
		if (particle != 0) {
			if (minValue > pBestValue[particle - 1]) {
				minValue = pBestValue[particle - 1];
				position = pBestDimensionPos.get(dimension)[particle - 1];
			}
		} else {
			if (minValue > pBestValue[pBestValue.length - 1]) {
				minValue = pBestValue[pBestValue.length - 1];
				position = pBestDimensionPos.get(dimension)[pBestValue.length - 1];
			}
		}
		if (particle != pBestValue.length - 1) {
			if (minValue > pBestValue[particle + 1]) {
				minValue = pBestValue[particle + 1];
				position = pBestDimensionPos.get(dimension)[particle + 1];
			}
		} else {
			if (minValue > pBestValue[0]) {
				minValue = pBestValue[0];
				position = pBestDimensionPos.get(dimension)[0];
			}
		}
		return position;
	}

	// returns the value of the specified function for point (x, y, z, etc.)
	private double eval(int functionNum, ArrayList<double[]> position, int index) {

		double retValue = 0.0;

		if (functionNum == SPHERE_FUNCTION_NUM) {
			retValue = evalSphere(position, index);
		} else if (functionNum == ROSENBROCK_FUNCTION_NUM) {
			retValue = evalRosenbrock(position, index);
		} else if (functionNum == RASTRIGIN_FUNCTION_NUM) {
			retValue = evalRastrigin(position, index);
		} else if (functionNum == ACKLEY_FUNCTION_NUM) {
			retValue = evalAckley(position, index);
		}

		return retValue;
	}

	// returns the value of the Sphere function at point (x, y, z, etc.)
	// minimum is 0.0, which occurs at (0.0,...,0.0)
	public double evalSphere(ArrayList<double[]> shiftedPosition, int index) {
		double sum = 0;
		for (int i = 0; i < dimensions; i++) {
			sum += Math.pow(shiftedPosition.get(i)[index], 2);
		}

		return sum;
	}

	// The below functions were based of the general function implementation
	// from https://code.google.com/p/evolutionary-algorithm/

	// returns the value of the Rosenbrock Function at point (x, y, z, etc.)
	// minimum is 0.0, which occurs at (1.0,...,1.0)
	public double evalRosenbrock(ArrayList<double[]> shiftedPosition, int index) {

		double sum = 0.0;
		double[] v = new double[shiftedPosition.size()];
		for (int i = 0; i < shiftedPosition.size(); i++)
			v[i] = shiftedPosition.get(i)[index] + 1;
		for (int i = 0; i < (shiftedPosition.size() - 1); i++) {
			double temp1 = (v[i] * v[i]) - v[i + 1];
			double temp2 = v[i] - 1.0;
			sum += (100.0 * temp1 * temp1) + (temp2 * temp2);
		}

		return (sum);

	}

	// returns the value of the Rastrigin Function at point (x, y, z, etc.)
	// minimum is 0.0, which occurs at (0.0,...,0.0)
	public double evalRastrigin(ArrayList<double[]> shiftedPosition, int index) {

		double res = 10 * shiftedPosition.size();
		for (int i = 0; i < shiftedPosition.size(); i++)
			res += Math.pow(shiftedPosition.get(i)[index], 2) - 10
					* Math.cos(2.0 * Math.PI * shiftedPosition.get(i)[index]);
		return res;

	}

	// returns the value of the Ackley Function at point (x, y, z, etc.)
	// minimum is 0.0, which occurs at (0.0,...,0.0)
	public double evalAckley(ArrayList<double[]> shiftedPosition, int index) {

		double sum1 = 0.0;
		double sum2 = 0.0;

		for (int i = 0; i < shiftedPosition.size(); i++) {
			sum1 += (shiftedPosition.get(i)[index] * shiftedPosition.get(i)[index]);
			sum2 += (Math.cos(2 * Math.PI * shiftedPosition.get(i)[index]));
		}

		return (-20.0 * Math.exp(-0.2 * Math.sqrt(sum1 / ((double) shiftedPosition.size())))
				- Math.exp(sum2 / ((double) shiftedPosition.size())) + 20.0 + Math.E);

	}

}
