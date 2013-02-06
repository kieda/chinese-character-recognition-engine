/*////////////////////////////////////////////////////////////////////////>>Main
./src/universals/ChineseMakeDictionary.java
//////////////////////////////////////////////////////////////////////////////*/
package universals;//package

// <editor-fold defaultstate="collapsed" desc="imports">
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;// </editor-fold>

/**
 * this class was used to make the database from the copied list of the most
 * frequent Chinese characters
 * @author Kieda
 * @since forever ago
 *//*MERGING TWO OR MORE DATA STRUCTURES*/
   /*PARSING TEXT*/
public class ChineseMakeDictionary {
    static int popularity = 31;//the starting point to add characters
    static int line = 0;//the line number
    public static void main(String[] args) throws IOException {
        File loadfile1 = new File("characters.txt");
            //loadfile: the file with the list of the most common chinese
            //characters
        File loadfile2 = new File("def.txt");
        File loadfile3 = new File("pinyin.txt");
        FileWriter fstream= new FileWriter("newest.txt");
            //the file being wrote to
        BufferedWriter out = new BufferedWriter(fstream);
            //the tool ues to write to the file
        Scanner in1;
        Scanner in2;
        Scanner in3;
            //the tool used to read the loadfile
        System.out.println(loadfile1.exists());
        System.out.println(loadfile2.exists());
        System.out.println(loadfile3.exists());
            //to check if the file exists
        System.out.println(new File ("newest.txt").exists());
        in1 = new Scanner(loadfile1);//makes a new scanner of the loadfile
        in2 = new Scanner(loadfile2);
        in3 = new Scanner(loadfile3);
        while(in1.hasNextLine()){
            String s1 = in1.nextLine();
            String s2 = in2.nextLine();
            String s3 = in3.nextLine();
            /*MERGING TWO OR MORE DATA STRUCTURES*/
            /*Reads from three reasources and writes to one*/
            out.write(s1+":"+s2+":"+s3);
                //writes the important information and a mreak (':')
                //which is replaces later using the Ctrl+f and replace
                //feature
            out.newLine();//makes a new line
        }
        in1.close();
        in2.close();
        in3.close();
        out.close();//closes all of the readers and writers
        //*********************************************************************
        //used to create a different database previously***********************
        //*********************************************************************
        /*
        while(popularity<=3000){//main loop to the 3000 most common characters
            in = new Scanner(loadfile);//makes a new scanner of the loadfile
            fast:while(in.hasNextLine()){//reads through the entire document
                String s = in.nextLine();
                if(popularity==858)//mistake in the html file
                        popularity++;//skips the line
                if(s.contains(popularity+"")){
                        //sees wether if the line inspected has the number in it
                        //(the documetnt had a format of
                        //popularity character [pinyin] UNNEEDED_STUFF
                    s = s.split(popularity+"")[1];
                        //splits up the line
                    s = s.split("]")[0];
                        //extracts the important information
                    out.write(s+"]");
                        //writes the important information and a mreak (']')
                        //which is replaces later using the Ctrl+f and replace
                        //feature
                    out.newLine();//makes a new line
                    break fast;
                        //breaks the search for a line with the popularity
                }
                line++;//the line number goes up one
            }
            popularity++;//go to the next highest popularity
            in.close();//close the scanner

        }*/
    }
}
