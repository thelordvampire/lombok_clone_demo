/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bao.getter.processor;

import com.bao.getter.annotation.MySetter;
import com.bao.getter.context.AppContext;
import  static com.bao.getter.context.AppContext.*;
import com.bao.getter.util.MethodUtils;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Position;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

/**
 *
 * @author bao
 */
@SupportedAnnotationTypes("com.bao.getter.annotation.MySetter")
@SupportedSourceVersion(SourceVersion.RELEASE_14)
public class SetterProcessor extends AbstractProcessor {
    
    @Override
    public synchronized void init(ProcessingEnvironment env)
    {
        System.out.println("init setter");
        System.out.println(env.toString());
        AppContext.init(env);
    }
    
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) {
            return true;
        }
//        TreeMaker.at(Position.FIRSTPOS);
        System.out.println("process set");
        Set<? extends Element> mySetterEls = roundEnv.getElementsAnnotatedWith(MySetter.class);
        
        mySetterEls.forEach((el) -> {
            if(el.getKind() != ElementKind.FIELD) return;
            if(el.getEnclosingElement().getKind() != ElementKind.CLASS) return;
            JCTree.JCClassDecl claxx = (JCTree.JCClassDecl) TreeApi.getTree(el.getEnclosingElement());
            JCTree.JCVariableDecl field = (JCTree.JCVariableDecl) TreeApi.getTree(el);
            
            System.out.printf("field: %s \n", field.sym.getClass());
//            System.out.printf("my setter element: %s \n", el.toString());
//            System.out.printf("my setter element kind: %s \n", el.getKind().toString());
//            System.out.printf("my setter enclosing element: %s \n", el.getEnclosingElement().toString());
//            System.out.printf("my setter enclosing element kind: %s \n", el.getEnclosingElement().getKind().toString());
//            System.out.printf("tree node: %s , kind: %s \n", treeNode.getClass().toString(), treeNode.getKind().toString());
//            System.out.printf("vari: %s \n", vari.toString());
            
//            JCTree.JCMethodDecl setMethod =  MethodUtils.createSetMethod(field, claxx, Symtab.instance(context).methodClass);
            JCTree.JCMethodDecl setMethod =  MethodUtils.createSetMethod(field);
            claxx.defs = claxx.defs.append(setMethod);
        });
        System.out.println("end process set 1");
        return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() { 
        return SourceVersion.RELEASE_14;
    }
    
}


