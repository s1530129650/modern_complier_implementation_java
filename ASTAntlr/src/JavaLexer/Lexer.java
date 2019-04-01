package JavaLexer;

import java.io.File;
import java.io.FileInputStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Token;
//https://imjching.com/writings/2017/02/16/lexical-analysis-with-antlr-v4/

public class Lexer {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//args[0] = "for.java";
		System.out.println("Parsing: ");
		
		FileInputStream fis = new FileInputStream (new File("for.txt"));
		ANTLRInputStream input = new ANTLRInputStream(fis);
		JavaLexer lexer = new JavaLexer(input);
		Token token = lexer.nextToken();
        while (token.getType() != JavaLexer.EOF) {
            System.out.println("\t" + token.getType() + "\t\t" + token.getText());
            token = lexer.nextToken();
        }
    }
}
