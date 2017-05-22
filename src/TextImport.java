import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class TextImport {
	
	public char [] constructList(String text){
        int arraySize = charCount(text);
        char [] T = new char [arraySize];
            try {
                InputStream in = new FileInputStream(text);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
               
                int r;
                int i=0;
              
                while ((r = reader.read()) != -1){
                  T[i] = (char) (r);
                    if(Character.isUpperCase(T[i])){
                        T[i] = Character.toLowerCase(T[i]);
                    }
                  i++;
                }
            reader.close();
            } catch (FileNotFoundException e) {
            } catch (IOException e){
            }
         return T;
    }
	
    public int charCount(String text){
            int count = 0;
            int r = 0;
            try {
                InputStream in = new FileInputStream(text);
                Reader reader = new InputStreamReader(in);
                while ((r = reader.read()) != -1) count++;
                reader.close();
        } catch (IOException e) {
        	
        }
            
         return count;
}
    public int lineCount(String text){
        int count = 0;
        String r;
        try {
        	InputStream in = new FileInputStream(text);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while ((r = reader.readLine()) != null) count++;
            reader.close();
    } catch (IOException e) {
    }
            
     return count;
}
	
}
