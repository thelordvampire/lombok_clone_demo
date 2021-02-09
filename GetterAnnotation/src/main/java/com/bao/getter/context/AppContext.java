/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bao.getter.context;

import com.sun.source.util.Trees;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Names;
import javax.annotation.processing.ProcessingEnvironment;

/**
 *
 * @author bao
 */
public final class AppContext {
    public static JavacProcessingEnvironment JavacEnv;
    public static Context Context;
    public static Trees TreeApi;
    public static TreeMaker TreeMaker;
    public static Names NameApi;
    
    public static void init(ProcessingEnvironment env)
    {
        if(env instanceof JavacProcessingEnvironment) {
            JavacEnv = (JavacProcessingEnvironment) env;
            Context = JavacEnv.getContext();
            TreeMaker = TreeMaker.instance(Context);
            TreeApi = Trees.instance(env);
            NameApi = Names.instance(Context);
        }
    }
    
}
