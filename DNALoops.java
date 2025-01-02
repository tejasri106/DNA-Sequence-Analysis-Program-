import java.util.Scanner;
/**
 * 
 *
 * @author Tejasri Addanki
 * @version 10-21-24
 */
public class DNALoops
{
    public static void main (String[] args) {
        Scanner sequences = new Scanner(System.in);
        String sequenceOne;
        String sequenceTwo;
        // Input for first sequence
        do {
            System.out.println("Enter a single DNA sequence (capitalize):");
            sequenceOne = sequences.nextLine();
            if (!validateString(sequenceOne)) {
                System.out.println("Invalid sequence! Please enter a valid DNA sequence (only A, T, C, G are allowed).");
            }
        } while (!validateString(sequenceOne));
        validateString(sequenceOne);
        cCount(sequenceOne);
        fractionOfCG(sequenceOne);
        complementary(sequenceOne);
          // Input for second sequence
        do {
            System.out.println("Enter a shorter single DNA sequence (capitalize):");
            sequenceTwo = sequences.nextLine();
            if (!validateString(sequenceOne)) {
                System.out.println("Invalid sequence! Please enter a valid DNA sequence (only A, T, C, G are allowed).");
            }
        } while (!validateString(sequenceTwo));
        validateString(sequenceTwo);
        cCount(sequenceTwo);
        fractionOfCG(sequenceTwo);
        complementary(sequenceTwo);
        alignmentScore(sequenceOne, sequenceTwo);
    } 
    
    public static boolean validateString(String dnaSequence) {
        for (int i = 0; i < dnaSequence.length(); i++) {
            char nucleotide = dnaSequence.charAt(i); 
            if (nucleotide != 'A' && nucleotide != 'G' && nucleotide != 'T' && nucleotide != 'C') {
                return false;  
            }
        }
        return true;
    }
    
    public static void cCount(String dnaSequence) {
        int counter = 0;
        for (int i = 0; i < dnaSequence.length(); i++) {
            char nucleotide = dnaSequence.charAt(i); 
            if (nucleotide == 'C') {
                counter++;
            }
        }
        System.out.println("C-counter: " + counter);
    }
    
    public static void fractionOfCG(String dnaSequence) {
        int counterCG = 0;
        for (int i = 0; i < dnaSequence.length(); i++) {
            char nucleotide = dnaSequence.charAt(i); 
            if (nucleotide == 'C'|| nucleotide == 'G') {
                counterCG++;
            }
        }
        double ratio = (double) counterCG/dnaSequence.length();
        System.out.print("CG-ratio: ");
        System.out.printf("%.3f", ratio);
        System.out.println();
    }
    
    public static void complementary(String dnaSequence) {
        String complementarySequence = "";
        for (int i = 0; i < dnaSequence.length(); i++) {
            char nucleotide = dnaSequence.charAt(i);
            if (nucleotide == 'A') {
                complementarySequence += "T";
            } else if (nucleotide == 'T') {
                complementarySequence += "A";
            } else if (nucleotide == 'C') {
                complementarySequence += "G";
            } else {
                complementarySequence += "C";
            }
        }
        System.out.println("Complement: " + complementarySequence);
    }
    
    public static void alignmentScore(String sequenceOne, String sequenceTwo) {
        String longerSequence, shorterSequence;
        int movementLimit; // limits the number of positions the shorter sequence can be shifted
        if (sequenceOne.length() > sequenceTwo.length()) {
            longerSequence = sequenceOne;
            shorterSequence = sequenceTwo;
            movementLimit = sequenceOne.length() - sequenceTwo.length();
        } else {
            longerSequence = sequenceTwo;
            shorterSequence = sequenceOne;
            movementLimit = sequenceTwo.length() - sequenceOne.length();
        } 
        int bestScore = 0;
        int bestAlignment = 0;
        // Try all possible alignments
        for (int i = 0; i <= movementLimit; i++) {
            int currentScore = 0;
            // Calculates score for this alignment

            for (int j = 0; j < shorterSequence.length(); j++) {
                if (longerSequence.charAt(j + i) == (shorterSequence.charAt(j))) {
                    currentScore++;
                }
            }
             // Checks if this alignment is better than previous best
            if (currentScore > bestScore) {
                bestScore = currentScore;
                bestAlignment = i;
            } 
        }
        System.out.println("Best alignment score: " + bestScore);
        System.out.println(longerSequence);
        
        for (int k = 0; k < bestAlignment; k++) {
            System.out.print("");
        }
        System.out.println(shorterSequence);
    }
}
