/* Labb 2 i DD1352 Algoritmer, datastrukturer och komplexitet    */
/* Se labbanvisning under kurssidan http://www.csc.kth.se/DD1352 */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */
package lab1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;


public class Main {

  public static List<String> readWordList(BufferedReader input) throws IOException {
    LinkedList<String> list = new LinkedList<String>(); // en linkedList är snabbare än en arraylist när man inte vet hur många element som kmr in
    while (true) {                                      // när man går över den definierade storleken för en arrayList 
                                                        // måste programmet föra över allt till en ny arraylist
      String s = input.readLine();                      // nackdelen med en linkedlist är att man inte kan indexa den
      if (s.equals("#"))
        break;
      list.add(s);
    }
    return list;
  }

  public static void main(String args[]) throws IOException {
    long t1 = System.currentTimeMillis();
    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
    // Säkrast att specificera att UTF-8 ska användas, för vissa system har annan
    // standardinställning för teckenkodningen.
    List<String> wordList = readWordList(stdin);
    String word;
    while ((word = stdin.readLine()) != null) {
      ClosestWords closestWords2 = new ClosestWords(word, wordList);
      System.out.print(word + " (" + closestWords2.getMinDistance() + ")");
      for (String w : closestWords2.getClosestWords())
        System.out.print(" " + w);
      System.out.println();
    }
    long tottime = (System.currentTimeMillis() - t1);
    System.out.println("CPU time: " + tottime + " ms");

  }
}

class ClosestWords {
  LinkedList<String> closestWords = null;

  int closestDistance = -1;
  int res, addLetter, deleteLetter;
  int[][] matris = new int[40][40];

  int partDist(String w1, String w2, int w1len, int w2len) {

    for (int i = 0; i <= w1len; i++) {
      matris[0][i] = i;
    }
    for (int i = 1; i <= w2len; i++) {
      matris[i][0] = i;
      for (int j = 1; j <= w1len; j++) {
        res = matris[i-1][j-1];
        addLetter = matris[i][j-1] + 1;
        deleteLetter = matris[i-1][j] + 1;
        if (w1.charAt(j-1)!=w2.charAt(i-1)){res+=1;}
        if (res>addLetter){res=addLetter;}
        if (res>deleteLetter){res=deleteLetter;}
        matris[i][j] = res;
      }
    }
    return matris[w2len][w1len];
  }

  int Distance(String w1, String w2) {
    return partDist(w1, w2, w1.length(), w2.length());
  }

  public ClosestWords(String w, List<String> wordList) {
    //int counter = 0;
    //int counter2 = 0;
    for (String s : wordList) {
      //counter++;
      // System.out.println("d(" + w + "," + s + ")=" + dist);
      if (Math.abs(s.length() - w.length()) <= closestDistance || closestDistance == -1) {
        //counter2++;
        int dist = Distance(w, s);
        if (dist < closestDistance || closestDistance == -1) {
          closestDistance = dist;
          closestWords = new LinkedList<String>();
          closestWords.add(s);
        } else if (dist == closestDistance)
          closestWords.add(s);
      }
    }
    //System.out.println(counter + "      " + counter2);
  }

  int getMinDistance() {
    return closestDistance;
  }

  List<String> getClosestWords() {
    return closestWords;
  }
}

