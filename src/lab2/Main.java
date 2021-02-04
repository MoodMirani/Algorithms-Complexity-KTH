package lab2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        //Scanner scan = new Scanner(System.in);
        String directoryPath = System.getProperty("user.dir");
        Scanner scan = new Scanner(new File(directoryPath + "/testinput.txt"));

        int vertices = scan.nextInt();
        int edges = scan.nextInt();
        int m = scan.nextInt();

        // Tar bort onödiga färger
        if (vertices < m) {
            m = vertices;
        }

        // Skapa basfallet från smallest possible production
        int roles = 3;
        int scenes = 2 + edges;
        int people = 3 + m;

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1 3");
        arrayList.add("2 3");

        HashMap<Integer, Integer> remainingR = new HashMap<>();

        // Skapa grafen
        int role1;
        int role2;

        for (int i=2; i < scenes; i++) {
            role1 = scan.nextInt();
            role2 = scan.nextInt();

            // Letar efter loopar och skippar de isåfall
            if (role1 == role2) {
                continue;
            }

            // Lägger till nya roller med basfallet i åtanke
            if  (!remainingR.containsKey(role1)) {
                roles++; // ny roll
                remainingR.put(role1, roles);
            }

            if (!remainingR.containsKey(role2)) {
                roles++;
                remainingR.put(role2, roles);
            }

            // Skapar nya scener
            arrayList.add(remainingR.get(role1).toString() + " " + remainingR.get(role2).toString());
        }

        System.out.println(roles + "\n" + scenes + "\n" + people);
        // basfall
        System.out.println("1 1\n1 2\n1 3");

        for (int i = 4; i < roles +1; i++) {
            System.out.print(m + " ");
            for (int j = 4; j < people +1; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }

        for (String string: arrayList) {
            System.out.println(2 + " " + string);
        }
    }
}


// easy = can be done in polynomial time

// NP problem: ex hitta primfaktorerna p1*p2 = 458431
// - might be easy to solve?
// - its definitely easy to verify

// P problems:
// - easy to solve
// - easy to verify

// polynomial time = how complex the algorithm gets as the input gets larger

// NP hard:
// - hard to solve
// - hard to verify

// NP Complete:
// - at least as hard as the hardest problem in NP (NP hard)
// - easy to verify 

// all NP-Complete problems are fundamentally the same, as they can be used to solve eachother
