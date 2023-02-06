# Large-Number-operations
Addition, multiplication, division and subtraction on very large integers


AUTHORS : The boiler code is provided by Prof. Sridhar Alagar (UTD), rest of the method is filled by me (Anany Dwivedi)

Structure of the code:

-> "Num" class: Contains all the code and the "main" method at the moment (might change in future). 

Methods:

-> Constructor: "Num(String s)" takes in a number in string form and converts it into a "list" (private long[] list) of long type. 

  "8888888888888123456789123456789" -> {888888888888812345, 6789123456789}
  
-> printList(): prints the long list creatd by the constructor.

-> add(Num a, Num b): add large numbers a and b and returns Num object. 'a' and 'b' are also objects of type "Num". The functions extracts the lists of both the objects and add them and stores them in a new list. The new list is converted to String and a new Num object is initialized which is returned.
