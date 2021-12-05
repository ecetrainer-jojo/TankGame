package JavaIOLearn.BufferedReader;

import java.io.*;

public class BufferedReaderWriter {
    public static void main(String[] args) throws IOException {

        String srcPath = "src/JavaIOLearn/test.txt";
        String destPath = "src/JavaIOLearn/testDest.txt";
        writeFile(srcPath);
        fileCopy(srcPath,destPath);
    }

    public static void writeFile(String path) throws IOException{
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, false));
        String lyrics = "だからキライだよ こんな日に出かけるの\n" +
                "人がやたら歩いてて 用もないのに\n" +
                "今年は久しぶり 田舎に帰るから\n" +
                "彼女になんか土産でも どんなもんかな\n" +
                "人もけしきも 忙しそうに\n" +
                "年末だから ああ\n" +
                "僕らの町に 今年も雪が降る\n" +
                "見慣れた町に 白い雪が つもるつもる\n" +
                "あと何日かで今年も終わるから\n" +
                "たまには二人で じゃま者なしで\n" +
                "少し話して のんびりして\n" +
                "人も車も へり始めてる\n" +
                "年末だから ああ\n" +
                "僕らの町に 今年も雪が降る\n" +
                "いつもと同じ 白い雪さ つもるつもる\n" +
                "あと何日かで 今年も終わるけど\n" +
                "世の中は 色々あるから\n" +
                "どうか元気で お気をつけて";
        bufferedWriter.write(lyrics);
        bufferedWriter.close();


    }

    public static void readFile(String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String content;
        while((content = bufferedReader.readLine())!=null){
            System.out.println(content);
        }
        bufferedReader.close();

    }

    public static void fileCopy(String srcPath, String destPath) throws IOException{
        File destFile = new File(destPath);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(destFile, false));
        BufferedReader bufferedReader = new BufferedReader(new FileReader(srcPath));
        String content;
        while((content = bufferedReader.readLine())!=null){
            bufferedWriter.write(content);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
