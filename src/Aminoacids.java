import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Aminoacids 
{

	static MultiLinkedList aminoacids = new MultiLinkedList();
	
	
	static void textToMllAminos() throws NumberFormatException, IOException
	{
		try
		{
		File file = new File("aminoacids.txt");

        @SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file));

        String splittedLine;

        String[] tempComma;
        String[] tempDash;
        while ((splittedLine = br.readLine()) != null) {

            tempComma = splittedLine.split(",");
            aminoacids.addCategory(tempComma[0]);
            for (int i = 2; i < tempComma.length; i++) 
            {
                tempDash = tempComma[i].split("-");
                aminoacids.addItem(tempComma[0], tempDash[0], Integer.parseInt(tempDash[1]));
            }

        }
        
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
}
