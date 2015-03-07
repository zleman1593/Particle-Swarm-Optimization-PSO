/*
Zackery Leman, Ivy Xing, Alana Weinstein
 Particle Swarm Optimization 
 
 Adapted from Stephen Majercik
 2013 April 6

*/


import java.util.*;


public class PSO  {


  private final int WINDOW_WIDTH = 1000;

  private final int WINDOW_HEIGHT = 1000;




  // ****************  MISCELLANEOUS    ******************

  // for random numbers
  private Random rand = new Random();


  // ****************  PSO  ******************

  // x and y positions for each particle
  private double[] xPos;  
  private double[] yPos;  

  // x and y velocities for each particle
  private double[] xVel;  
  private double[] yVel;  

  // pBest positions and values for each particle
  private double[] pBestXPos;  
  private double[] pBestYPos;  
  private double[] pBestValue;  

  // gbest position and value
  private double gBestXPos;  
  private double gBestYPos;  
  private double gBestValue;  

  // initial speed range
  private final double MIN_INIT_SPEED = -3.0;
  private final double MAX_INIT_SPEED = 3.0;

  // shifts the optimum so that it is in the center of the window
  private final double FUNCTION_SHIFT = WINDOW_WIDTH/2.0;
  // establishes a zone around the optimum that is off limits for particles when the 
  // swarm is initialized (to make it a little harder)
  private final double NO_INIT_ZONE_SIDE = 100;
  private final double NO_INIT_ZONE_LEFT_COORD = FUNCTION_SHIFT - NO_INIT_ZONE_SIDE/2.0;
  private final double NO_INIT_ZONE_RIGHT_COORD = FUNCTION_SHIFT + NO_INIT_ZONE_SIDE/2.0;
  private final double NO_INIT_ZONE_TOP_COORD = FUNCTION_SHIFT - NO_INIT_ZONE_SIDE/2.0;
  private final double NO_INIT_ZONE_BOTTOM_COORD = FUNCTION_SHIFT + NO_INIT_ZONE_SIDE/2.0;

  // ******************************************************************************************
  // ****** You will need to play with the values of some of the variables below 
  // ******************************************************************************************

  // number of particles in the swarm
  private int numParticles = 10; 

  // personal best acceleration coefficient
  private double phi1 = 2.05;
  // global best acceleration coefficient
  private double phi2 = 2.05;  
  
  // constriction factor
  private double phi = phi1 + phi2;
  public double constrictionFactor = 2.0 / (phi - 2.0 + Math.sqrt(phi*phi - 4.0*phi));

  // test functions
  private final int SPHERE_FUNCTION_NUM = 1;
  private final int ROSENBROCK_FUNCTION_NUM = 2;
  private final int ACKLEY_FUNCTION_NUM = 3;
  private final int RASTRIGIN_FUNCTION_NUM = 4;
  private final int GRIEWANK_FUNCTION_NUM = 5;

  // which one to test
  public int testFunction = SPHERE_FUNCTION_NUM;

  // for controlling termination 
  private int iterationNum = 0;
  private int maxIterations = 50;
  private final int MILLISECS_DELAY_BETWEEN_ITERS = 100;
  private final int LONG_DELAY = 600000;


  public void main() {

 
    // create arrays for particle positions
    xPos = new double[numParticles];  
    yPos = new double[numParticles];  

    // create arrays for particle velocities
    xVel = new double[numParticles];  
    yVel = new double[numParticles];  

    // create arrays for particle personal bests
    pBestValue = new double[numParticles];  
    pBestXPos = new double[numParticles];  
    pBestYPos = new double[numParticles];  

    // set gbest value very high so it will be replaced in the loop
    // that creates the particles
    gBestValue = Double.MAX_VALUE;

    // create particles and calculate initial personal bests; 
    // find the initial global best
    for (int p = 0 ; p < numParticles; p++) {

      // get coordimates outside of the zone around the global optimum
      double x = WINDOW_WIDTH * rand.nextDouble();
      double y = WINDOW_HEIGHT * rand.nextDouble();
      while (x > NO_INIT_ZONE_LEFT_COORD && 
        x < NO_INIT_ZONE_RIGHT_COORD && 
        y > NO_INIT_ZONE_TOP_COORD && 
        y < NO_INIT_ZONE_BOTTOM_COORD) {
        x = WINDOW_WIDTH * rand.nextDouble();
        y = WINDOW_HEIGHT * rand.nextDouble();
      }

      // set the coordinates and get the value of the objective function 
      // at that point
      xPos[p] = x;//1000.0 - rand.nextDouble() *100.0;
      yPos[p] = y;//1000.0 - rand.nextDouble() *100.0;

      // initial value
      double currValue = eval(testFunction, xPos[p], yPos[p]);

      // initialize velocities
      xVel[p] = MIN_INIT_SPEED + rand.nextDouble() * (MAX_INIT_SPEED - MIN_INIT_SPEED);
      yVel[p] = MIN_INIT_SPEED + rand.nextDouble() * (MAX_INIT_SPEED - MIN_INIT_SPEED);

      // ****** store initial personal best in the pBest arrays provided
      pBestValue[p] = currValue;
      pBestXPos[p] = xPos[p];
      pBestYPos[p] = yPos[p];

      // ****** check for new global best and store, if necessary, in the variables provided
      if (currValue < gBestValue) {
        gBestValue = currValue;
        gBestXPos = xPos[p];  
        gBestYPos = yPos[p];    
      }
      
      // draw the particle
      render((float) xPos[p], (float) yPos[p], p);
    }
  }


  public void update() {

    ++iterationNum;
    System.out.println("iteration " + iterationNum + "  gbest value = " + gBestValue);


    if (iterationNum > maxIterations) {
      System.out.println("DONE!!");
    }

 

  
    // update all the particles
    for (int p = 0 ; p < numParticles ; p++) {
      
      double accPersXPos;
      double accPersYPos;
      
      double accGlobXPos;
      double accGlobYPos;
      
      double newXVel;
      double newYVel;
      double newXPos;
      double newYPos;
      double newValue;
  
    
      // ****** compute the acceleration due to personal best
       accPersXPos =  phi1*(pBestXPos[p] - xPos[p]);
       accPersYPos =  phi1*(pBestYPos[p] - yPos[p]);
       
       
      // ****** compute the acceleration due to global best
        accGlobXPos = phi2*(gBestXPos - xPos[p]);
        accGlobYPos = phi2*(gBestYPos - yPos[p]);

      // ****** constrict the new velocity and reset the current velocity
      
      newXVel = xVel[p] + accPersXPos + accGlobXPos;
      newYVel = yVel[p] + accPersYPos + accGlobYPos;
      
      xVel[p] = newXVel * constrictionFactor;
      yVel[p] = newYVel * constrictionFactor;
      

      // ****** update the position
      newXPos = xPos[p] + xVel[p];
      newYPos = yPos[p] + yVel[p];
      
      xPos[p] = newXPos;
      yPos[p] = newYPos;
      

      // ****** find the value of the new position
      newValue = eval(testFunction, xPos[p], yPos[p]);
     

      // ****** update personal best and global best, if necessary
      
      if(newValue < pBestValue[p]) {
        pBestValue[p] = newValue;
        pBestXPos[p] = xPos[p];
        pBestYPos[p] = yPos[p];
      }
        
      if(newValue < gBestValue) {
        gBestValue = newValue; 
        gBestXPos = xPos[p];
        gBestYPos = yPos[p];
      }

      // draw the particle
      render((float) xPos[p], (float) yPos[p], p);
    }
  }


  // returns the maximum distance between particles in the swarm
  public double maxDistance (double[] x, double[] y) {

    double maxDist = -1.0;
    for (int p1 = 0 ; p1 < numParticles ; p1++) {
      for (int p2 = p1+1 ; p2 < numParticles ; p2++) {
        double thisDistance = distance(x[p1], y[p1], x[p2], y[p2]);
        if (thisDistance > maxDist)
          maxDist = thisDistance;
      }
    }

    return maxDist;
  }


  // returns the distance between (x1, y1) and (x2, y2)
  public double distance (double x1, double y1, double x2, double y2) {

    return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
  }



  // returns the value of the specified function for point (x, y)
  public double eval(int functionNum, double x, double y) {

    x -= FUNCTION_SHIFT;
    y -= FUNCTION_SHIFT;

    double retValue = 0.0;

    if (functionNum == SPHERE_FUNCTION_NUM) {
      retValue = evalSphere(x, y);
    }  
    else if (functionNum == ROSENBROCK_FUNCTION_NUM) {
      retValue = evalRosenbrock(x, y);
    }  
    else if (functionNum == RASTRIGIN_FUNCTION_NUM) {
      retValue = evalRastrigin(x, y);
    }  
    else if (functionNum == ACKLEY_FUNCTION_NUM) {
      retValue = evalAckley(x, y);
    }  
    else if (functionNum == GRIEWANK_FUNCTION_NUM) {
      retValue = evalGriewank(x, y);
    }  

    return retValue;
  }



  // returns the value of the Sphere function at point (x, y)
  //   minimum is 0.0, which occurs at (0.0,...,0.0)
  public double evalSphere (double x, double y) {

    return (x*x + y*y);
  }  




  // returns the value of the Rosenbrock Function at point (x, y)
  //   minimum is 0.0, which occurs at (1.0,...,1.0)
  public double evalRosenbrock (double x, double y) {

    return 100.0 * Math.pow(y - x*x, 2.0) + Math.pow(x-1.0, 2.0);
  }




  // returns the value of the Rastrigin Function at point (x, y)
  //   minimum is 0.0, which occurs at (0.0,...,0.0)
  public double evalRastrigin (double x, double y) {

    double retVal = 0;
    retVal += x*x - 10.0*Math.cos(2.0*Math.PI*x) + 10.0;
    retVal += y*y - 10.0*Math.cos(2.0*Math.PI*y) + 10.0;

    return retVal;
  }




  // returns the value of the Ackley Function at point (x, y)
  //   minimum is 0.0, which occurs at (0.0,...,0.0)
  public double evalAckley (double x, double y) {

    double firstSum = x*x + y*y;
    double secondSum = Math.cos(2.0*Math.PI*x) + Math.cos(2.0*Math.PI*y);

    return -20.0 * Math.exp(-0.2 * Math.sqrt(firstSum/2.0)) - 
      Math.exp(secondSum/2.0) + 20.0 + Math.E;
  }  




  // returns the value of the Griewank function at point (x, y)
  //   minimum is 0.0, which occurs at (0.0,...,0.0)
  public double evalGriewank (double x, double y) {

    double sumSquares = x*x + y*y;
    double productCos = Math.cos(x/Math.sqrt(1)) * Math.cos(y/Math.sqrt(2));

    return sumSquares/4000.0 - productCos + 1.0;
  }  

}
