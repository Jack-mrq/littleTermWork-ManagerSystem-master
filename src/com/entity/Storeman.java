package com.entity;

import com.DAO.Impl.storemanDAOImpl;

import java.sql.SQLException;
import java.util.Scanner;
/*
库管
功能：用户类的子类，void addKind(int assetsid,int num)增加某个资产的数量，若没有改资产则创建；
void decreaseKind(int assestsid,int num) 减少某个资产的数量；
void addAsset()和void decreaseAssets()分别是对前两者的进一步封装调用；
void operation() 通过调用函数满足交互界面的使用要求
*/
public class Storeman extends User{
    storemanDAOImpl storemanDAO;
    Storeman(){
        storemanDAO = new storemanDAOImpl();
    }
    public void addKind(int assetsid,int num) throws SQLException {
       /*
       增加某个资产的数量，若没有改资产则创建
        */
       Asset asset = storemanDAO.getAsset(assetsid);
        Asset tmpAsset = new Asset();
        tmpAsset.setId(assetsid);
       if(asset.id>0){
           tmpAsset.setName(asset.getName());
           tmpAsset.setTotal(num+asset.getTotal());
            storemanDAO.updateAsset(tmpAsset);
       }else{
           Scanner scanner=new Scanner(System.in);
           System.out.println("该资产不存在\n默认创建\n请输入资产ID、名字和数量：");
           int id=scanner.nextInt();
           String name=scanner.next();
           tmpAsset.setId(id);
           int total = scanner.nextInt();
           tmpAsset.setTotal(total);
           tmpAsset.setName(name);
           storemanDAO.addAsset(tmpAsset);
           System.out.println("更新成功！");
           //scanner.close();
       }
    }
    public void addAssets() throws SQLException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入资产id和增加的数量：");
        int assestsid=scanner.nextInt();
        int num=scanner.nextInt();
        addKind(assestsid,num);
        //scanner.close();
    }
    public void create_asset() throws SQLException{
        Asset tmpAsset = new Asset();
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入资产ID、名字和数量：");
        int id=scanner.nextInt();
        String name=scanner.next();
        tmpAsset.setId(id);
        int total = scanner.nextInt();
        tmpAsset.setTotal(total);
        tmpAsset.setName(name);
        storemanDAO.addAsset(tmpAsset);
        System.out.println("更新成功！");
    }
    public void decreaseKind(int assestsid,int num) throws SQLException {
        /*
       删除某个资产的数量
        */
        Asset asset = storemanDAO.getAsset(assestsid);
        Asset tmpAsset = new Asset();
        tmpAsset.setId(assestsid);
        if(asset.getId()>0){
            tmpAsset.setName(asset.getName());
            if(asset.getTotal()>=num)
             tmpAsset.setTotal(asset.getTotal()-num);
            else tmpAsset.setTotal(0);
            storemanDAO.updateAsset(tmpAsset);
        }
    }
    public void decreaseAssets() throws SQLException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入资产id和减少的数量：");
        int assestsid=scanner.nextInt();
        int num=scanner.nextInt();
        decreaseKind(assestsid,num);
        //scanner.close();
    }
    @Override
    void operation() throws SQLException {
        Scanner scanner=new Scanner(System.in);
        boolean isexit=false;
        int flag;
        do{
            System.out.println("您是库管，可以：");
            System.out.println("1、修改密码");
            System.out.println("2、查看剩余库存");
            System.out.println("3、增加资产");
            System.out.println("4、减少资产数量");
            System.out.println("5、创建资产");
            System.out.println("6、退出");
            flag=scanner.nextInt();
            switch (flag){
                case 1:
                    changePw();
                    break;
                case 2:
                    lookAssets();
                    break;
                case 3:
                    addAssets();
                    break;
                case 4:
                    decreaseAssets();
                    break;
                case 5:
                    create_asset();
                    break;
                case 6:
                    isexit=true;
                    break;
                default:
                    System.out.println("输入错误！");
            }
        }while(!isexit);
        //scanner.close();
    }
}