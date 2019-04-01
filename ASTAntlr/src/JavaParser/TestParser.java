package JavaParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;


import java.io.*;

public class TestParser {
    public static void main(String[] args) throws IOException {
    	//InputStream is = new FileInputStream("D:/Program Files/antlr/file/java8/for.java"); // or System.in;
        InputStream is = new FileInputStream("for.java");
        ANTLRInputStream input = new ANTLRInputStream(is);
        JavaLexer lexer = new  JavaLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new  JavaParser(tokens);
        ParseTree tree = parser.statement(); // statement() is the starting rule

        System.out.println("LISP:");
        System.out.println(tree.toStringTree(parser));
        System.out.println();
    }
}
/*
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

import antlr.CommonAST;
import antlr.DumpASTVisitor;

public class TestParser {

    public static void main(String[] args) throws Exception {
        System.out.println("Parsing: " + args[0]);
        
        Reader reader = new BufferedReader(new FileReader(args[0]));
        ScriptLexer lexer = new ScriptLexer(reader);
        ScriptParser parser = new ScriptParser(lexer);
        parser.script();

        CommonAST ast = (CommonAST)parser.getAST();
        DumpASTVisitor visitor = new DumpASTVisitor();
        visitor.visit(ast);
    }
}*/
