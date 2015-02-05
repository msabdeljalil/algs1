/*************************************************************************
*  Name:       Mohammad Abdeljalil
*  Date:       Feb 4, 2015
*  Purpose:    In completion of the requirements for programming assignment 
*              one of Algorithms, Part 1
*  Compilation:javac -classpath .:../algs4.jar:../stdlib.jar PercolationStats.java
*  Execution:  java -classpath .:../algs4.jar:../stdlib.jar PercolationStats N T
*
*************************************************************************/
/**
 *  <i>Percolation statistics</i>. This class provides statistics
 *  including as mean, sample standard deviation, and 95% confidence
 *  interval for a series of runs of the Percolation class.
 *  <p>
 *
 *  @author Mohammad Abdeljalil
 */
public class PercolationStats {
   private int N;
   private int T;
   private int openCount;
   private double[] testRuns;

   /**
   * Perform T independent experiments on an N-by-N grid
   */
   public PercolationStats(int n, int t) {
      if (n <= 0 || t <= 0) throw new IllegalArgumentException("N and T must be > 0");
      N = n;
      T = t;
      double size = N*N;
      testRuns = new double[T];

      for (int x=0; x<T; x++) {
         Percolation perc = new Percolation(N);
         int openCount = 0;
         while (perc.percolates() == false) {
            int i = StdRandom.uniform(N)+1;
            int j = StdRandom.uniform(N)+1;
            if (perc.isOpen(i,j) == false ) {
               perc.open(i,j);
               openCount++;
            }
         }
         testRuns[x] = (double)(openCount/size);
      }
   }

   /**
   * Sample mean of percolation threshold
   * @return the mean of the percolation test runs
   */
   public double mean() {
      return StdStats.mean(testRuns);
   }

   /**
   * sample standard deviation of percolation threshold
   */
   public double stddev() {
      return StdStats.stddev(testRuns);
   }

   /**
   * low  endpoint of 95% confidence interval
   */
   public double confidenceLo() {
      return mean() - ( (1.96*stddev()) / Math.sqrt(T) );
   }

   /**
   * high endpoint of 95% confidence interval
   */
   public double confidenceHi() {
      return mean() + ( (1.96*stddev()) / Math.sqrt(T) );
   }

   /**
   * test client (described below)
   */
   public static void main(String[] args) {
      PercolationStats percTest = new PercolationStats( Integer.parseInt(args[0]), Integer.parseInt(args[1]) );
      System.out.println( "Mean                    = " + percTest.mean() );
      System.out.println( "StDev                   = " + percTest.stddev() );
      System.out.println( "95% confidence interval = " + percTest.confidenceLo() + ", " + percTest.confidenceHi() );
   }
}
