//codingbat exercises

// public int diff21(int n) {
//   int diff = n-21;
//   if(n>21)
//     diff+=diff;
//   if (diff < 0)
//     diff = diff * (-1);
//   return diff;
// }


// public boolean lastDigit(int a, int b, int c) {
//     boolean check = false;
//     if ((a%10 == b%10) || (a%10 == c%10) || (b%10 == c%10))
//       check = true;
      
//       return check;
//   }
  

//   public boolean hasTeen(int a, int b, int c) {
//     return (a>=13 && a<=19) ||
//            (b>=13 && b<=19) ||
//            (c>=13 && c<=19);
// }

// public int teaParty(int tea, int candy) {
  
//     if (tea < 5 || candy <5)
//       return 0;
//     else if ((2*tea) <= candy || (2*candy) <= tea)
//       return 2;
      
//     return 1;
    
//   }
  

//   public String makeAbba(String a, String b) {
//     String c = a + b + b + a;
//     return c;
//   }

//   public String middleTwo(String str) {
//     int l = str.length()/2;
//     String s = str.substring(l-1,l+1);
//     return s;
//   }
  
  