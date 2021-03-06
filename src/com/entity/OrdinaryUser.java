package com.entity;

import com.DAO.Impl.userDAOImpl;
import com.DAO.Impl.managerDAOImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
/*
普通用户类
功能：用户类的子类， void looku_a()查看该用户的借用信息，void operation() 通过调用函数满足交互界面的使用要求
*/
public class OrdinaryUser extends User {
    public void looku_a() throws SQLException {
        /*
       查看该用户的所有借用情况
        */
        managerDAOImpl userDAO=new managerDAOImpl();
        User user = new OrdinaryUser();
        user.setName(name);
        List<Asset> assetList = userDAO.getUsersAssets(user);
        int i = 0;
        System.out.print("No\tid\tname\ttotal\n");
        while (i<assetList.size()){
            Asset asset = assetList.get(i);
            System.out.print(i+"\t");
            System.out.print(asset.getId()+"\t");
            System.out.print(asset.getName()+"\t");
            System.out.println(asset.getTotal()+"\t");
            i++;
        }
        if(i == 0)
            System.out.println("没有信息");
    }

    @Override
    void operation() throws SQLException {
           /*
         普通user
          */
        int flag;
        Scanner scanner=new Scanner(System.in);
        boolean isexit=false;
        do{
            System.out.println("您是普通用户，可以：");
            System.out.println("1、修改密码");
            System.out.println("2、查看剩余库存");
            System.out.println("3、查看自己的借用情况");
            System.out.println("4、退出");
            flag=scanner.nextInt();
            switch (flag){
                case 1:
                    changePw();
                    break;
                case 2:
                    lookAssets();
                    break;
                case 3:
                    looku_a();
                    break;
                case 4:
                    isexit=true;
                    break;
                default:
                    System.out.println("输入错误！");
            }
        }while(!isexit);
        //scanner.close();
    }
}
