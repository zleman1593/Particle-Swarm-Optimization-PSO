/*
Zackery Leman, Min "Ivy" Xing, Alana Weinstein
 Particle Swarm Optimization 

 Adapted from Stephen Majercik
 2013 April 6

 */

import java.util.*;

public class PSO {

	private final int WINDOW_WIDTH = 1000;

	private final int WINDOW_HEIGHT = 1000;

	// for random numbers
	private Random rand = new Random();

	// **************** PSO ******************
	private int dimensions;

	// x and y ,z etc. positions for each particle
	private ArrayList<double[]> position = new ArrayList<double[]>();

	// x and y velocities for each particle
	private ArrayList<double[]> velocity = new ArrayList<double[]>();

	// pBest positions and values for each particle
	private ArrayList<double[]> pBestDimensionPos = new ArrayList<double[]>();

	private double[] pBestValue;

	// gbest position and value
	private ArrayList<double[]> gBestDimensionPos = new ArrayList<double[]>();
	private double gBestValue;

	// initial speed range
	private final double MIN_INIT_SPEED = -3.0;
	private final double MAX_INIT_SPEED = 3.0;

	// shifts the optimum so that it is in the center of the window
	private final double FUNCTION_SHIFT = 0;// WINDOW_WIDTH / 2.0;
	// establishes a zone around the optimum that is off limits for particles
	// when the
	// swarm is initialized (to make it a little harder)
	private final double NO_INIT_ZONE_SIDE = 100;
	private final double NO_INIT_ZONE_LEFT_COORD = FUNCTION_SHIFT - NO_INIT_ZONE_SIDE / 2.0;
	private final double NO_INIT_ZONE_RIGHT_COORD = FUNCTION_SHIFT + NO_INIT_ZONE_SIDE / 2.0;
	private final double NO_INIT_ZONE_TOP_COORD = FUNCTION_SHIFT - NO_INIT_ZONE_SIDE / 2.0;
	private final double NO_INIT_ZONE_BOTTOM_COORD = FUNCTION_SHIFT + NO_INIT_ZONE_SIDE / 2.0;

	// number of particles in the swarm
	private int numParticles = 10;

	// personal best acceleration coefficient
	private double phi1 = 2.05;
	// global best acceleration coefficient
	private double phi2 = 2.05;

	// constriction factor
	private double phi = phi1 + phi2;
	public double constrictionFactor = 2.0 / (phi - 2.0 + Math.sqrt(phi * phi - 4.0 * phi));

	// test functions
	private final int SPHERE_FUNCTION_NUM = 1;
	private final int ROSENBROCK_FUNCTION_NUM = 2;
	private final int ACKLEY_FUNCTION_NUM = 3;
	private final int RASTRIGIN_FUNCTION_NUM = 4;

	// which one to test
	public int testFunction = SPHERE_FUNCTION_NUM;

	// for controlling termination
	private int iterationNum = 0;
	private int maxIterations = 50;

	public static void main(String[] args) {

		PSO swarm = new PSO(2);

	}

	public PSO(int dimensions) {
		for (int i = 0; i < dimensions; i++) {
			gBestDimensionPos.add(new double[numParticles]);
			pBestDimensionPos.add(new double[numParticles]);
		}

		this.dimensions = dimensions;
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

		// create particles and calculate initial personal bests;
		// find the initial global best
		for (int p = 0; p < numParticles; p++) {

			// set the coordinates and get the value of the objective function
			// at that point

			for (int i = 0; i < dimensions; i++) {

				
				double initPosition = 0;

				//TODO: Change these so they are random doubles
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
					initPosition = 5.12 + (5.12 - 2.56)*rand.nextDouble();
					while (initPosition < 2.56 || initPosition > 5.12) {
						initPosition =  2.56 + (5.12 - 2.56)*rand.nextDouble();
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
					gBestDimensionPos.get(i)[p] = position.get(i)[p];
				}
			}

		}
		while (iterationNum < maxIterations) {
			System.out.println("iteration " + iterationNum + "  gbest value = " + gBestValue);
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
			double accGlobDimensionPos[] = new double[dimensions];

			double newDimensionVel[] = new double[dimensions];

			double newDimensionPos[] = new double[dimensions];
			;

			// ****** compute the acceleration due to personal best
			for (int i = 0; i < dimensions; i++) {
				accPersDimensionPos[i] = phi1 * (pBestDimensionPos.get(i)[p] - position.get(i)[p]);
			}

			// ****** compute the acceleration due to global best

			for (int i = 0; i < dimensions; i++) {
				accGlobDimensionPos[i] = phi1 * (gBestDimensionPos.get(i)[p] - position.get(i)[p]);
			}

			// ****** constrict the new velocity and reset the current velocity
			for (int i = 0; i < dimensions; i++) {
				newDimensionVel[i] = velocity.get(i)[p] + accPersDimensionPos[i] + accGlobDimensionPos[i];
			}

			for (int i = 0; i < dimensions; i++) {
				newDimensionVel[i] = velocity.get(i)[p] + accPersDimensionPos[i] + accGlobDimensionPos[i];
				velocity.get(i)[p] = newDimensionVel[i] * constrictionFactor;
			}

			// ****** update the position
			for (int i = 0; i < dimensions; i++) {
				accPersDimensionPos[i] = phi1 * (pBestDimensionPos.get(i)[p] - position.get(i)[p]);

				newDimensionPos[i] = position.get(i)[p] + velocity.get(i)[p];
			}

			for (int i = 0; i < dimensions; i++) {
				position.get(i)[p] = newDimensionPos[i];
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
					gBestDimensionPos.get(i)[p] = position.get(i)[p];
				}

			}

		}

	}

	// returns the maximum distance between particles in the swarm
	public double maxDistance(double[] x, double[] y) {

		double maxDist = -1.0;
		for (int p1 = 0; p1 < numParticles; p1++) {
			for (int p2 = p1 + 1; p2 < numParticles; p2++) {
				double thisDistance = distance(x[p1], y[p1], x[p2], y[p2]);
				if (thisDistance > maxDist)
					maxDist = thisDistance;
			}
		}

		return maxDist;
	}

	// returns the distance between (x1, y1) and (x2, y2)
	public double distance(double x1, double y1, double x2, double y2) {

		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

	// returns the value of the specified function for point (x, y, z, etc.)
	public double eval(int functionNum, ArrayList<double[]> position, int index) {

		double retValue = 0.0;

		if (functionNum == SPHERE_FUNCTION_NUM) {
			retValue = evalSphere(position, index);
		} /*
		 * else if (functionNum == ROSENBROCK_FUNCTION_NUM) { retValue =
		 * evalRosenbrock(positionShifted, index); } else if (functionNum ==
		 * RASTRIGIN_FUNCTION_NUM) { retValue = evalRastrigin(positionShifted,
		 * index); } else if (functionNum == ACKLEY_FUNCTION_NUM) { retValue =
		 * evalAckley(positionShifted, index); } else if (functionNum ==
		 * GRIEWANK_FUNCTION_NUM) { retValue = evalGriewank(positionShifted,
		 * index); }
		 */
		return retValue;
	}

	// returns the value of the Sphere function at point (x, y)
	// minimum is 0.0, which occurs at (0.0,...,0.0)
	public double evalSphere(ArrayList<double[]> shiftedPosition, int index) {
		double sum = 0;
		for (int i = 0; i < dimensions; i++) {
			sum += Math.pow(shiftedPosition.get(i)[index], 2);
		}

		return sum;
	}

	// returns the value of the Rosenbrock Function at point (x, y)
	// minimum is 0.0, which occurs at (1.0,...,1.0)
	public double evalRosenbrock(double x, double y) {

		return 100.0 * Math.pow(y - x * x, 2.0) + Math.pow(x - 1.0, 2.0);
	}

	// returns the value of the Rastrigin Function at point (x, y)
	// minimum is 0.0, which occurs at (0.0,...,0.0)
	public double evalRastrigin(double x, double y) {

		double retVal = 0;
		retVal += x * x - 10.0 * Math.cos(2.0 * Math.PI * x) + 10.0;
		retVal += y * y - 10.0 * Math.cos(2.0 * Math.PI * y) + 10.0;

		return retVal;
	}

	// returns the value of the Ackley Function at point (x, y)
	// minimum is 0.0, which occurs at (0.0,...,0.0)
	public double evalAckley(double x, double y) {

		double firstSum = x * x + y * y;
		double secondSum = Math.cos(2.0 * Math.PI * x) + Math.cos(2.0 * Math.PI * y);

		return -20.0 * Math.exp(-0.2 * Math.sqrt(firstSum / 2.0)) - Math.exp(secondSum / 2.0) + 20.0 + Math.E;
	}

	// returns the value of the Griewank function at point (x, y)
	// minimum is 0.0, which occurs at (0.0,...,0.0)
	public double evalGriewank(double x, double y) {

		double sumSquares = x * x + y * y;
		double productCos = Math.cos(x / Math.sqrt(1)) * Math.cos(y / Math.sqrt(2));

		return sumSquares / 4000.0 - productCos + 1.0;
	}

}
