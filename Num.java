import java.util.NoSuchElementException;

// Starter code for SP3.
// Version 1.0 (Fri, Feb 3).

// Change following line to your NetId

public class Num  implements Comparable<Num> {

    static long defaultBase = 10;  // Change as needed
    long base = defaultBase;  // Change as needed
    long[] list;  // array to store arbitrarily large integers
    boolean isNegative;  // boolean flag to represent negative numbers
    int len;  // actual number of elements of array that are used;  number is stored in list[0..len-1]

    public Num(String s) {
        isNegative = false; // assumed to be false
        int n = s.length();
        len = (n%18 == 0) ? n/18 : n/18 +1;

        list = new long[len];

        for(int i = 0; i < len; i++) {
            int start = i*18;

            int maxLen = s.substring(start, n).length();
            int end = (maxLen < 18) 
                ? start + maxLen : start + 18;
            list[i] = Long.parseLong(
                s.substring(start, end));
        }
    }


    public static Num add(Num a, Num b) {
        // exception thrown if one of the lists is empty
        if(a.list.length == 0 || b.list.length == 0) 
            throw new NoSuchElementException(); 

        
        int aSize = a.list.length;
        int bSize = b.list.length;
        int listSize = Math.max(aSize, bSize);
        long[] list = new long[listSize];

        int carry = 0;
        int i = aSize - 1, j = bSize - 1;
        for (int k = listSize - 1; k > -1; k--) {
            long num;
            if (i > -1) {
                if (j > -1) {
                    num = a.list[i] + b.list[j] + carry;
                    // System.out.println("j & i > -1: " + num);
                    i--;
                    j--;
                }
                else{
                    num = a.list[i] + carry;
                    // System.out.println("i > -1: " + num);
                    i--;
                }
            }
            else {
                num = b.list[j] + carry;
                j--;
                // System.out.println("j > -1: " + num);
            }
            carry = 0;

            if (num > 1000000000000000000L && k != 0) {
                carry = 1;
                list[k] = num % 1000000000000000000L;
                // System.out.println("num > maxL: " + num);
            }
            else{
                list[k] = num;
                // System.out.println("num is Ok: " + num);
            }
        }

        String ans = "";
        for(int l = 0; l < listSize; l++) {
            ans += (Long.toString(list[l]));
        }

        // System.out.println("answer is: " + ans);
	    return new Num(ans);
    }


    public static Num product(Num a, Num b) {
	    return null;
    }
	
	// Return number to a string in base 10
    public String toString() {
        String num = "";
        for(int l = 0; l < len; l++) {
            num += (Long.toString(list[l]));
        }
	    return num;
    }

	//  methods below are optional
	
	public static Num subtract(Num a, Num b) {
	    return null;
    }
    // Use divide and conquer
    public static Num power(Num a, long n) {
	    return null;
    }

    // Use divide and conquer or division algorithm to calculate a/b
    public static Num divide(Num a, Num b) {
	    return null;
    }


    // Utility functions
    // compare "this" to "other": return +1 if this is greater, 0 if equal, -1 otherwise
    public int compareTo(Num other) {
	    return 0;
    }
    
    // Output using the format "base: elements of list ..."
    public void printList() {
		System.out.print(base + " : ");
		for(int i=0; i<len; i++) {
			System.out.print(list[i] + " ");
		}
	System.out.println();
    }
    


    public long base() { return base; }

    // Return number equal to "this" number, in base=newBase
    public Num convertBase(int newBase) {
	    return null;
    }

    // Divide by 2, for using in binary search
    public Num by2() {
	    return null;
    }

 


    public static void main(String[] args) {
        Num x = new Num("8888888888888123456789123456789");
        x.printList();
        Num y = new Num("2");
        y.printList();
        Num z = Num.add(x, y);
        // z.printList();
        System.out.println(z);
        // Num a = Num.product(x, y);
        // System.out.println(a);
        // if(a != null) a.printList();
        
    }
}
