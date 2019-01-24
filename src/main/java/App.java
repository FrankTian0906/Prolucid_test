import java.io.*;


import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class App{

    public static void main(String[] args)throws IOException {
        /*
        * get article file
        * */
        File file = new File("./src/main/resources/ShortStory.txt");
        String article = txt2String(file);
        SentenceDetect(article);

    }

    /*
    * sentence detector
    * parameter: String
    * */
    private static void SentenceDetect(String text)throws IOException {
        /*
        * upload model en-sent.bin(original)
        * upload model en-sent-custom.bin(trained)
        * */
        InputStream is =new FileInputStream("./src/main/java/nlpbin/en-sent-custom.bin");

        SentenceModel model =new SentenceModel(is);
        SentenceDetectorME detector =new SentenceDetectorME(model);
        String sentences[] =detector.sentDetect(text);
        //sort
        sortSentences(sentences);
        //output
        for(int i=0;i<sentences.length;i++)
        {
            System.out.println(sentences[i]);
        }
    }

    /*
    * get txt file to string
    *  parameter: File
    * */
    private static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s;
            while((s = br.readLine())!=null){
                //replace divided lines
                s= s.replaceAll("------------------------------------------------","");
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }

    /*
    * sort with alphabetical order
    * parameter: String[]
    * */
    private static void sortSentences(String[] sentences){
        for(int i=0; i<sentences.length; i++)
        {
            for(int j=1; j<sentences.length; j++)
            {
                if(sentences[j-1].compareTo(sentences[j])>0)
                {
                    String temp=sentences[j-1];
                    sentences[j-1]=sentences[j];
                    sentences[j]=temp;
                }
            }
        }
    }
}