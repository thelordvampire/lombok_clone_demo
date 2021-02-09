/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bao.getter.processor;

import com.bao.getter.annotation.MyGetter;
import com.bao.getter.context.AppContext;
import static com.bao.getter.context.AppContext.*;
import com.bao.getter.util.MethodUtils;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;
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
@SupportedAnnotationTypes("com.bao.getter.annotation.MyGetter")
@SupportedSourceVersion(SourceVersion.RELEASE_14)
public class GetterProcessor extends AbstractProcessor {
    
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("process get");
        Set<? extends Element> myGetterElements = roundEnv.getElementsAnnotatedWith(MyGetter.class);
        myGetterElements.forEach((el) -> {
            if(el.getKind() != ElementKind.FIELD) return;
            if(el.getEnclosingElement().getKind() != ElementKind.CLASS) return;
            JCTree.JCVariableDecl vari = (JCTree.JCVariableDecl) TreeApi.getTree(el);
            JCTree.JCClassDecl claxx = (JCClassDecl) TreeApi.getTree(el.getEnclosingElement());
            JCTree.JCMethodDecl getMethod = MethodUtils.createGetMethod(vari);
            claxx.defs = claxx.defs.append(getMethod);
        });
        System.out.println("end process");
        return true;
    }

    @Override
    public synchronized void init(ProcessingEnvironment env)
    {
        System.out.println("init getter");
        System.out.println(env.toString());
        AppContext.init(env);
    }

    @Override
    public SourceVersion getSupportedSourceVersion() { 
        return SourceVersion.RELEASE_14;
    }
    
}
