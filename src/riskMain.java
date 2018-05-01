import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class riskMain {

    public static void main(String[] args) throws IOException{
        try {
            deckCreator();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private static void deckCreator()throws IOException{
        File f = new File("res/riskCards.txt");
        BufferedReader b = new BufferedReader(new FileReader(f));
        String readline = "";
        while ((readline = b.readLine()) != null) {
            System.out.println(readline);
        }
    }
}
