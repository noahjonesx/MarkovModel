
import java.io.File;
import java.io.IOException;

/**
 * TextGenerator.java. Creates an order K Markov model of the supplied source
 * text, and then outputs M characters generated according to the model.
 *
 * @author     Your Name (you@auburn.edu)
 * @author     Dean Hendrix (dh@auburn.edu)
 *
 */
public class TextGenerator {

   /** Drives execution. */
   public static void main(String[] args) {
   
      if (args.length < 3) {
         System.out.println("Usage: java TextGenerator k length input");
         return;
      }
   
      // No error checking! You may want some, but it's not necessary.
      int K = Integer.parseInt(args[0]);
      int M = Integer.parseInt(args[1]);
      if ((K < 0) || (M < 0)) {
         System.out.println("Error: Both K and M must be non-negative.");
         return;
      }
   
      File text;
      try {
         text = new File(args[2]);
         if (!text.canRead()) {
            throw new Exception();
         }
      }
      catch (Exception e) {
         System.out.println("Error: Could not open " + args[2] + ".");
         return;
      }
   
   
      // instantiate a MarkovModel with the supplied parameters and
      // generate sample output text ...
      //instantiate 
      MarkovModel m = new MarkovModel(K, text);
      //create string to output answer
      String ans = m.getFirstKgram();
      for (int i = 0; i < M - K; i++) {
         String gram = ans.substring(i, i + K);
         try {
            ans += m.getNextChar(gram);
         }
            //if null
         catch (NullPointerException e) {
            ans += m.getRandomKgram();
         }
         //output
         System.out.println(ans.length());
      }
      System.out.println(ans); 
   }
}
