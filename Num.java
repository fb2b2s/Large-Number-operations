import java.util.NoSuchElementException;
import java.util.ArrayList;

// Starter code for SP3.
// Version 1.0 (Fri, Feb 3).

// Change following line to your NetId

public class Num  implements Comparable<Num> {

    static long defaultBase = 10;  // Change as needed
    long base = defaultBase;  // Change as needed
    long[] list;  // array to store arbitrarily large integers
    boolean isNegative;  // boolean flag to represent negative numbers
    int len;  // actual number of elements of array that are used; number is stored in list[0..len-1]

    // new variable : number of digits to be kept in one list element
    private static int listElementSize = 9; // 9 is good for multiplication

    public Num(String s) {
        isNegative = false; // assumed to be false
        int n = s.length();
        len = (n%listElementSize == 0)
             ? n/listElementSize : n/listElementSize +1;

        list = new long[len];

        int start;
        int end = n;
        for(int i = len-1; i != -1; i--) {
            start = (n - (len-i)*listElementSize > -1)
                ? n - (len-i)*listElementSize : 0;

            // System.out.println("start: " + start + "     end: " + end);
            list[i] = Long.parseLong(
                s.substring(start, end));

            end = start;
        }

        // System.out.println("first addition is: " + numDigitsFirstAddition);
    }

    private static int numDigits(long num) {
        int nd = 0;
        while(num!=0) {
            nd++;
            num/=10;
        }
        return nd;
    }


    public static Num add(Num a, Num b) {
        // exception thrown if one of the lists is empty
        if(a.list.length == 0 && b.list.length == 0) 
            throw new NoSuchElementException(); 

        // System.out.println("The non static value is: " + a.numDigitsFirstAddition);
        int aSize = a.list.length;
        int bSize = b.list.length;
        int listSize = Math.max(aSize, bSize);

        boolean moreThanMax = false;
        if(aSize == bSize) {
            if(numDigits(a.list[0] + b.list[0]) > listElementSize) {
                listSize++;
            }
        }
        
        long[] list = new long[listSize];
        if(!moreThanMax) {
            list[0] = 1;
        }
        // System.out.println("sizes are " + aSize + " and " + bSize);

        long carry = 0;
        int i = aSize - 1, j = bSize - 1;

        for (int k = listSize - 1; k > -1; k--) {
            // System.out.println("a[" + i + "]" + " = " + a.list[i]);
            // System.out.println("b[" + j + "]" + " = " + b.list[j]);
            if (i > -1) {
                if (j > -1) {
                    
                    // System.out.println(carry);

                    list[k] = (a.list[i] + b.list[j] + carry) 
                        % (long)Math.pow(10, listElementSize);
                    carry = (a.list[i] + b.list[j] + carry) 
                        / (long)Math.pow(10, listElementSize);
                    i--;
                    j--;
                }
                else{
                    list[k] = a.list[i] + carry;
                    carry = (a.list[i] + carry) 
                    / (long)Math.pow(10, listElementSize);
                    i--;
                }
            }
            else if(j > -1) {
                list[k] = b.list[j] + carry;
                carry = (b.list[j] + carry) 
                    / (long)Math.pow(10, listElementSize);
                j--;
            }
            
            
            // System.out.println("list[" + k + "]" + " = " + list[k]);
        }

        String num = Long.toString(list[0]);
        // System.out.println("toString start");
        for(int l = 1; l < list.length; l++){
            int nd = numDigits(list[l]);

            if(nd == listElementSize) { 
                // System.out.println("nd==listElementSize " + l);
                num += Long.toString(list[l]);
            }
            else if(nd > 0) {
                // System.out.println("nd>0 " + l);
                num += ("0".repeat(listElementSize-nd) 
                    + Long.toString(list[l]));
            }
            else{
                // System.out.println("else " + l);
                num += "0".repeat(listElementSize);
            }
        }

        // System.out.println("answer is: " + ans);
	    return new Num(num);
    }


    public static Num product(Num a, Num b) {


        int digs = (a.list.length - 1 + b.list.length - 1)
            * listElementSize + numDigits(a.list[0]) + numDigits(b.list[0]);
        int listSize = (digs%listElementSize == 0) 
            ? digs/listElementSize : digs/listElementSize + 1;

        if(listSize == 0) {return new Num("0");}

        long[] list = new long[listSize];

        // System.out.println("listSize = " + listSize);

        int aSize = a.list.length;
        int bSize = b.list.length;

        long carry = 0;
        long lim = (long)Math.pow(10, listElementSize);
        for(int i = bSize-1; i > -1; i--) {
            int k = listSize - (bSize-i);
            for(int j = aSize-1; j > -1; j--) {
                long mul = a.list[j] * b.list[i];
                long curVal = list[k];
                list[k] = (curVal + mul + carry)%lim;
                // carry = 0;
                carry = (curVal + mul + carry) / lim;
                k--;
                // System.out.println("carry: " + carry);
            }
            if(carry != 0) {
                long curVal = list[k];
                list[k] = (curVal + carry)%lim;
                carry = (curVal + carry)/lim;
            }
            //for(int f = 0; f < listSize; f++) {
             //   System.out.println("(" + f + "): " + list[f]);
            //}
            //System.out.println("\n");
        }
        
        

       //// for(int f = 0; f < listSize; f++) {
        //     System.out.println("(" + f + "): " + list[f]);
        // }

        String num = (list[0] == 0) ? "" : Long.toString(list[0]);
        // System.out.println("toString start");
        for(int i = 1; i < listSize; i++){
            int nd = numDigits(list[i]);

            if(nd == listElementSize) { 
                // System.out.println("nd==listElementSize " + i);
                num += Long.toString(list[i]);
            }
            else if(nd > 0) {
                // System.out.println("nd>0 " + i);
                num += ("0".repeat(listElementSize-nd) 
                    + Long.toString(list[i]));
            }
            else{
                // System.out.println("else " + i);
                num += "0".repeat(listElementSize);
            }
        }

	    return new Num(num);
    }
	
	// Return number to a string in base 10
    public String toString() {
        String num = Long.toString(list[0]);
        // System.out.println("toString start");
        for(int i = 1; i < len; i++){
            int nd = numDigits(list[i]);

            if(nd == listElementSize) { 
                // System.out.println("nd==listElementSize " + i);
                num += Long.toString(list[i]);
            }
            else if(nd > 0) {
                // System.out.println("nd>0 " + i);
                num += ("0".repeat(listElementSize-nd) 
                    + Long.toString(list[i]));
            }
            else{
                // System.out.println("else " + i);
                num += "0".repeat(listElementSize);
            }
        }
        // System.out.println("toString end");

	    return num;
    }

	//  methods below are optional
	
	public static Num subtract(Num a, Num b) {
	    return null;
    }
    // Use divide and conquer
    public static Num power(Num a, long n) {
        if(a.list.length == 0) {throw new NoSuchElementException();}
        if(a.list.length == 1 && a.list[0] == 0){ return new Num("1");}

        if(n == 1) {return a;}

        Num ans;
        if(n%2 == 0) {
            ans =  Num.product(power(a, n/2), power(a, n/2));
        }
        else {
            ans = Num.product(a, Num.product(power(a, n/2), power(a, n/2)));
        }
	    return ans;
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

    public static Num fibonacci(int n) {
        Num a = new Num("0");
        Num b = new Num("1");
        for(int i=0; i<n; i++) {
            Num c = Num.add(a,b);
            a = b;
            b = c;
        }
        return a;
    }

 


    public static void main(String[] args) {
        Num x = new Num("2");
        // // // x.printList();
        // // // System.out.println(x);
        Num y = new Num("1343");
        // // // y.printList();
        // // // System.out.println(y);
        // // // // System.out.println("digits in 100 is " + Num.numDigits(100));
        // Num z = Num.add(x, y);
        // // // System.out.println("z list: ");
        // // z.printList();
        // System.out.println(z);
        // System.out.println(Num.fibonacci(2000));
        // z.printList();
        // System.out.println(z);
        // Num a = Num.product(x, y);
        // System.out.println(a);
        Num b = Num.power(x, 5);
        System.out.println(b);
        // if(a != null) a.printList();
        
    }
}
