public class Percolation {
    private int N;
    private int size;
    private int[] sites;
    private WeightedQuickUnionUF grid;

    /**
    * Create N-by-N grid, with all sites blocked
    */
    public Percolation(int n) {
        N = n;
        size = n*n+2;
        grid = new WeightedQuickUnionUF(size+1);
        sites = new int[size+1];
    }
    
    /**
    * Open site (row i, column j) if it is not open already
    */
    public void open(int i, int j) {
        sites[xyTo1D(i,j)] = 1;
        unionNeighbors(i,j);
    }
    
    /**
    * Is site (row i, column j) open?
    * @return if site is open
    */
    public boolean isOpen(int i, int j) {
        return ( sites[xyTo1D(i,j)] == 1);
    }

    /**
    * Is site (row i, column j) full (connected to the top)?
    * @return if site is full
    */
    public boolean isFull(int i, int j) {
        return grid.connected( xyTo1D(i,j), 1);
    }

    /**
    * Does the system percolate?
    * @return if site percolates
    */
    public boolean percolates() {
        return ( grid.find(1) == grid.find(size) );
    }

    /**
    * Test client (optional)
    */
    public static void main(String[] args) {
        Percolation perc1 = new Percolation(3);
        perc1.open(1,1);
        System.out.println( "1,1 Open? " + perc1.isOpen(1,1) ); //=> true
        System.out.println( "1,1 Full? " + perc1.isFull(1,1) ); //=> true
        perc1.open(2,1);
        System.out.println( "2,1 Open? " + perc1.isOpen(2,1) ); //=> true
        System.out.println( "2,1 Full? " + perc1.isFull(2,1) ); //=> true
        perc1.open(3,1);
        System.out.println( "3,1 Open? " + perc1.isOpen(3,1) ); //=> true
        System.out.println( "3,1 Full? " + perc1.isFull(3,1) ); //=> true

        // System.out.println( "4,1 Full? " + perc1.isFull(4,1) ); //=> true
        System.out.println( "PercCheck " + perc1.percolates() ); //=> true
    }

    private int xyTo1D(int row, int col) {
        return (N * row + col - N + 1);
    }

    private void boundsCheck(int i, int j) {
        if (i < 1 || j < 1) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    private void nCheck(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("N is out of bounds");
        }    
    }

    private void unionNeighbors(int i, int j) {
        if (i == 1) { 
            grid.union( 1, xyTo1D(i,j) );
        }
        if (i == N) { 
            grid.union( size, xyTo1D(i,j) );
        }
        if (i-1>0 && isOpen(i-1,j) == true) { 
            grid.union( xyTo1D(i,j), xyTo1D(i-1,j) );
        }
        if (i+1<=N && isOpen(i+1,j) == true ) { 
            grid.union( xyTo1D(i,j), xyTo1D(i+1,j) );
        }
        if (j+1<=N && isOpen(i,j+1) == true ) { 
            grid.union( xyTo1D(i,j), xyTo1D(i,j+1) );
        }
        if (j-1>0 && isOpen(i,j-1) == true ) { 
            grid.union( xyTo1D(i,j), xyTo1D(i,j-1) );
        }
    }
}
