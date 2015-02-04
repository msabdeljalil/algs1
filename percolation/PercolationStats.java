import java.util.Random;

public class PercolationStats {
   private int N;
   private int T;
   private int openCount;
   public double[] testRuns;
   /**
   * perform T independent experiments on an N-by-N grid
   */
   public PercolationStats(int n, int t) {
      N = n;
      T = t;
      double size = N*N;
      testRuns = new double[T];
      for (int x=0; x<T; x++) {
         Percolation perc = new Percolation(N);
         int openCount = 0;
         while (perc.percolates() == false) {
            int i = randInt(1,N);
            int j = randInt(1,N);
            if (perc.isOpen(i,j) == false ) {
               perc.open(i,j);
               openCount++;
            }
         }
         testRuns[x] = (double)(openCount/size);
      }
   }

   /**
   * sample mean of percolation threshold
   */
   public double mean() {
      double sum = 0.0;
      for (int i=0; i<testRuns.length; i++) {
         sum += testRuns[i];
      }
      return (sum/testRuns.length);
   }

   /**
   * sample standard deviation of percolation threshold
   */
   public double stddev() {
      double avg = mean();
      double sumSquares = 0;
      for (int i=0; i<testRuns.length; i++) {
         sumSquares += Math.pow(testRuns[i]-avg, 2);
      }
      return Math.sqrt(sumSquares/(T-1));
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
      System.out.println( "Mean                    = " + percTest.mean() ); //=> ?
      System.out.println( "StDev                   = " + percTest.stddev() ); //=> ?
      System.out.println( "95% confidence interval = " + percTest.confidenceLo() + ", " + percTest.confidenceHi() );


   }

   private int randInt(int min, int max) {
      // StdRandom.uniform(1,10)
      return min + (int)(Math.random() * ((max - min) + 1));
   }
}
