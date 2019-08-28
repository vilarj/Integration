import java.util.Scanner;

/**
 * Program that approximates a definite integral of three methods: Midpoint, Trapezoid, and Simpson. 
 * 
 * @author vilarj
 *
 */
public class Integral {

	// Constructing a Scanner object in the scope of this class only
	public static Scanner input = new Scanner(System.in);
	
	/**
	 * 
	 * @param x
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param e
	 * @param f
	 * @return value
	 */
	
	// Integral: Function
	public static double Function(double x, double a, double b, double c, double d, double e, double f){
		double value;
		
		value = ((a * Math.pow(x, 5) - b) * (Math.pow(x, 4) + c) * (Math.pow(x, 3) + d) * (Math.pow(x, 2) + e) * (x + f));
	
		return value;
	}
	
	
	/**
	 * 
	 * @param lower limit
	 * @param upper limit
	 * @param n
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param e
	 * @param f
	 * @return sum
	 */
	
	// Integral: Midpoint
	public static double midPoint(double lower, double upper, double n, double a, double b, double c, double d, double e, double f) {
		double width, sum = 0;
		
		// Setting the width
		width = ((b - a) / n);
		
		// Doing the approximation
		for(int k = 1; k <= n; k++) {
			double function = ((a + (k - 1)) * width );
			sum += k * Function(function, a, b, c, d, e, f);
		}
		return sum;
	}
	/**
	 * 
	 * @param lower limit
	 * @param upper limit
	 * @param n
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param e
	 * @param f
	 * @return (height / 2) * sum
	 */
	
	// Integral: Trapezoid
	public static double Trapezoid(double lower, double upper, double n, double a, double b, double c, double d, double e, double f) {
		double height, sum = 0;
		
		// Setting the height
		height = ((b - a) / n);
		
		// Computing the first and last
		sum = Function(lower, a, b, c, d, e, f) + Function(upper, a, b, c, d, e, f);
		
		// Adding the middle terms
		for(int j = 1; j < n; j++) {
			sum += 2 * Function(lower, a, b, c, d, e, f + j * height);
		}
		
		return (height / 2) * sum;
	}
	
	/**
	 * 
	 * @param lower limit
	 * @param upper limit
	 * @param n
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param e
	 * @param f
	 * @return (sum * height) / 2
	 */
	
	// Integral: Simpson
	public static double Simpson(double lower, double upper, double n, double a, double b, double c, double d, double e, double f) {
		double height, sum = 0, mid;
		
		height = (b - a) / n;
		
		mid = (lower + height) / 2;
		sum = Function(lower, a, b, c, d, e, f);
		
		for(int i = 0; i < n - 1; i++) {
			lower = i * height;
			sum += 4 * Function(mid, a, b, c, d, e, f);
			mid = lower + (height / 2);
		}
		return (sum * height) / 6;
	}
	
	/**
	 * 
	 * @param approximation
	 * @param actual
	 * @return error
	 */
	
	// Integral: Error
	public static double Error(double approximation, double actual) {
		return Math.abs(approximation - actual) / actual; // The error cannot be negative
	}
	
	/**
	 * 
	 * @param x
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param e
	 * @param f
	 */
	// Integral: Integrating
	public static void toIntegrate(double a, double b, double c, double d, double e, double f) {
		int lower, upper, n;
		double midpoint, trapezoid, simpson, actual = 0;
		
		// Getting the limits of the integral from the user and the subintervals between each point
		System.out.print("Enter the lower limit(a) of the definite integral: ");
		lower = input.nextInt();
		
		System.out.print("Enter the upper limit(b) of the definite integral: ");
		upper = input.nextInt();
		
		System.out.print("Enter the subintervals(n) of the definite integral: ");
		n = input.nextInt();
		
		midpoint = midPoint(lower, upper, n, a, b, c, d, e, f);
		trapezoid = Trapezoid(lower, upper, n, a, b, c, d, e, f);
		simpson = Simpson(lower, upper, n, a, b, c, d, e, f);
		
		// Printing out to the console the results
		System.out.print("---------------------------------\n");
		System.out.println("Interval[" + upper + ", " + lower + "]");;
		System.out.println("Subdivisions: " + n);

		System.out.print("---------------------------------\n");
		System.out.println("Midpoint's Method: " + midpoint);
		System.out.println("Error: " + Error(actual, midpoint));

		System.out.print("---------------------------------\n");
		System.out.println("Trapezoid's Method: " + trapezoid);
		System.out.println("Error: " + Error(actual, trapezoid));

		System.out.print("---------------------------------\n");
		System.out.println("Simpson's Method: " + simpson);
		System.out.println("Error: " + Error(actual, simpson));
	}
	
	/**
	 * Main method that executes all the other methods
	 * @param args
	 */
	public static void main(String[] args) {
		int a, b, c, d, e, f;
		
		// Getting the coefficients from the user
		System.out.print("Enter the coefficient of the function Ax: ");
		a = input.nextInt();
		
		System.out.print("Enter the coefficient of the function Bx: ");
		b = input.nextInt();
		
		System.out.print("Enter the coefficient of the function Cx: ");
		c = input.nextInt();
		
		System.out.print("Enter the coefficient of the function Dx: ");
		d = input.nextInt();
		
		System.out.print("Enter the coefficient of the function Ex: ");
		e = input.nextInt();
		
		System.out.print("Enter the coefficient of the function Fx: ");
		f = input.nextInt();
		
		// Calling the method that does the calculations
		toIntegrate(a,b,c,d,e,f);
		
		// Closing the Scanner class
		input.close();
	}
}
