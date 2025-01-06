class Hello {
public static void main(String a[]){ 
    System.out.println("Hello World");
    System.out.print(6);

    int num1  = 10_00_00; // underscore for clarity
    // integer converted to double automatically. So, can save int in double and float

    char c = 'a';
    c++; // this is possible

    // Type conversion and type casting
    byte b = 127;
    int a = 256;
    // b = a won't work as byte is smaller than a, but a=b will work
    b = (byte)a;

   // cannot store char in bool. but can convert int, float, double
   float f = 5.6f; // without f, decimal considered as double
   ++c; c++;
   
}
}