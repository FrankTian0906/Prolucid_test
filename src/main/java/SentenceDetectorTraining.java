import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.sentdetect.SentenceSampleStream;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;


/*
* Sentences training by trainingDataSentences.txt file
* */
public class SentenceDetectorTraining {
    /*
    public static void main(String[] args) {
        try {
            new SentenceDetectorTraining().trainSentDetectModel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */

    /**
     * This method generates s custom model file for sentence detection, in directory "custom_models".
     * The training data used is "trainingDataSentences.txt". Training data contains a sentence per line in the text file.
     * @throws IOException
     */
    public void trainSentDetectModel() throws IOException {
        // directory to save the model file that is to be generated, create this directory in prior
        File destDir = new File("./src/main/java/nlpbin/");

        // training data
        InputStreamFactory in = new MarkableFileInputStreamFactory(new File("./src/main/resources/trainingDataSentences.txt"));

        // parameters used by machine learning algorithm, Maxent, to train its weights
        TrainingParameters mlParams = new TrainingParameters();
        mlParams.put(TrainingParameters.ITERATIONS_PARAM, Integer.toString(15));
        mlParams.put(TrainingParameters.CUTOFF_PARAM, Integer.toString(1));

        // train the model
        SentenceModel sentdetectModel = SentenceDetectorME.train(
                "en",
                new SentenceSampleStream(new PlainTextByLineStream(in, StandardCharsets.UTF_8)),
                true,
                null,
                mlParams);

        // save the model, to a file, "en-sent-custom.bin", in the destDir : "custom_models"
        File outFile = new File(destDir,"en-sent-custom.bin");
        FileOutputStream outFileStream = new FileOutputStream(outFile);
        sentdetectModel.serialize(outFileStream);

        // loading the model
        SentenceDetectorME sentDetector = new SentenceDetectorME(sentdetectModel);

        // detecting sentences in the test string
        String testString = ("Sugar is sweet. That doesn't mean its good.");
        System.out.println("\nTest String: "+testString);
        String[] sents = sentDetector.sentDetect(testString);
        System.out.println("---------Sentences Detected by the SentenceDetector ME class using the generated model-------");
        for(int i=0;i<sents.length;i++){
            System.out.println("Sentence "+(i+1)+" : "+sents[i]);
        }
    }
}