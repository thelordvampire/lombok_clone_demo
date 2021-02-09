/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bao.getter.util;

import static com.bao.getter.context.AppContext.*;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Name;

/**
 *
 * @author bao
 */
public class MethodUtils {
    
    public static JCTree.JCMethodDecl createGetMethod(JCTree.JCVariableDecl field) {
        if(field == null) return null;
        
        String getMethodNameStr = StringUtils.generateMethodName("get", field.getName().toString());
        Name getMethodName = NameApi.fromString(getMethodNameStr);
        JCTree.JCStatement returnStatement = TreeMaker.Return(TreeMaker.Ident(field));
        JCTree.JCBlock methodBody = TreeMaker.Block(Flags.BLOCK, List.of(returnStatement));
        JCTree.JCExpression methodType = TreeMaker.Type(field.vartype.type);
        
        return TreeMaker.MethodDef(TreeMaker.Modifiers(Flags.PUBLIC), getMethodName, methodType,
            List.nil(), List.nil(), List.nil(), methodBody, null);
    }
    
    public static JCTree.JCMethodDecl createSetMethod(JCTree.JCVariableDecl field) {
        JCTree.JCFieldAccess thisX = TreeMaker.Select(TreeMaker.Ident(NameApi.fromString("this")), field.name);
        JCTree.JCAssign assign = TreeMaker.Assign(thisX, TreeMaker.Ident(field.name));
        JCTree.JCExpression methodType = TreeMaker.Type(new Type.JCVoidType());
        JCTree.JCBlock methodBody = TreeMaker.Block(0, List.of(TreeMaker.Exec(assign)));
        Name methodName = NameApi.fromString(StringUtils.generateMethodName("set", field.getName().toString()));
        JCTree.JCVariableDecl param = TreeMaker.VarDef(TreeMaker.Modifiers(Flags.PARAMETER), field.name, field.vartype, null);
        param.pos = field.pos;
//        param.sym = new VarSymbol(Flags.PARAMETER, field.name, field.vartype.type, null);
//        System.out.printf("sym: %s \n",param.sym);
        
        return TreeMaker.MethodDef(TreeMaker.Modifiers(Flags.PUBLIC), methodName, methodType,
                List.nil(), List.of(param), List.nil(), methodBody, null);
    }
    
    
//    public static JCTree.JCMethodDecl createSetMethod(JCTree.JCVariableDecl field, JCTree.JCClassDecl claxx, TypeSymbol methodClassSymbol) {
//        Symbol.MethodSymbol methodSym;
//        Type methodType;
//        JCTree.JCVariableDecl param;
//        JCTree.JCStatement assignStatement;
//        JCTree.JCBlock block  = null;
//        JCTree.JCExpression returnType = null;
//        JCTree.JCMethodDecl setMethod = null;
//        Names nameApi = AppContext.nameApi;
//        TreeMaker TreeMaker = AppContext.TreeMaker;
//        
//        String setMethodNameStr = StringUtils.generateMethodName("set", field.getName().toString());
//        Name methodName = nameApi.fromString(setMethodNameStr);
//
//        methodType = new Type.MethodType(List.of(field.vartype.type), new Type.JCVoidType(), 
//                List.<Type>nil(), methodClassSymbol);
//        methodSym = new Symbol.MethodSymbol(Flags.PUBLIC, methodName, methodType, claxx.sym);
//        
//        
//        
////        param = createParam(field.vartype.type);
//        param = createParam2(field, methodSym);
//        System.out.println("3333333333333333333: " + param);
//        
////        System.out.println("3333333333333333333: " + field);
////        System.out.println("3333333333333333333: " + field.sym);
//        System.out.println("3333333333333333333: " + TreeMaker);
////        System.out.println("3333333333333333333: " + TreeMaker.Ident(param));
// 
//        
//        assignStatement = TreeMaker.Assignment(field.sym, TreeMaker.Ident(field.name));
//        
//        System.out.println("444444444444444444444444");
//        block = TreeMaker.Block(Flags.BLOCK, List.of(assignStatement));
//        System.out.println("5555555555555555555555555");
//        returnType = TreeMaker.Type(new Type.JCVoidType());
//        System.out.println("6666666666666666666666666");
//        
//        System.out.println("class pos: " + claxx.pos);
//        
////        TreeMaker.at(claxx.pos);
//        
//        setMethod = TreeMaker.MethodDef(TreeMaker.Modifiers(Flags.PUBLIC), methodName, returnType, List.nil(), List.of(param), List.nil(), block, null);
//        System.out.println("method pos: " + setMethod.pos);
//        setMethod.getParameters().forEach(p -> {
////            System.out.println("p pos: " + p.pos);
////            System.out.println("p adr: " + p.sym.adr);
//            
//            p.sym.adr = 0;
//        });
//        return setMethod;
//    }
//    
//    private static JCTree.JCVariableDecl createParam(Type type) {
//        Names nameApi = AppContext.nameApi;
//        TreeMaker TreeMaker = AppContext.TreeMaker;
//        return TreeMaker.Param(nameApi.fromString("pa"), type, null);
//    }
//    
//    private static JCTree.JCVariableDecl createParam2(JCTree.JCVariableDecl field, Symbol.MethodSymbol methodSym) {
//        Names nameApi = AppContext.nameApi;
//        TreeMaker TreeMaker = AppContext.TreeMaker;
//        System.out.println("1111111111111111111111111111");
//        
//        JCTree.JCVariableDecl param = TreeMaker.VarDef(TreeMaker.Modifiers(Flags.PARAMETER), field.name, field.vartype, null);
//        System.out.println("22222222222222222222222222222");
////        Symbol.VarSymbol varSymbol = new Symbol.VarSymbol(Flags.PARAMETER, param.name, param.type, methodSym);
////        param.sym = varSymbol;
//        param.sym =  field.sym;
//        
////        varSymbol.adr = field.pos;
////        varSymbol.pos = field.pos;   
////        System.out.println("pos: " + varSymbol.owner.kind);
////        System.out.println("pos: " + param.sym.pos);
////        System.out.println("pos: " + field.pos().getStartPosition());
//        
//        
//        return param;
//    }
    
}

