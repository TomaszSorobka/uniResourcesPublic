import java.util.Scanner;

 class ToTheMax {
    Scanner sc = new Scanner(System.in);

    public void calcMax() {
        int a, b, c, max;
        System.out.println("Input 3 values");
        a = sc.nextInt();
        b = sc.nextInt();
        c = sc.nextInt();
        
        max = a;
        if (b>max)
            max = b;
        
        if (c>max)
            max = c;

        System.out.println("The highest number is " + max);
           
    }

    public static void main(String[] args) {
        (new ToTheMax()).calcMax();
    }
 }