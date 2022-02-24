package com.entity;
/*
主函数类
功能：生成operator对象，调用其work函数完成系统功能
 */
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] a) throws SQLException {
        Operator operator=new Operator();
        operator.work();
    }
}

