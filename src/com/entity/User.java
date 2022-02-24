package com.entity;
import java.sql.SQLException;
import java.util.*;
import com.DAO.Impl.*;
/*
用户类
功能：抽象类，
 */
public abstract class User {
    String name;
    String password;
    int id;
    int type;
    userDAOImpl userDAO;

    User(){
        userDAO=new userDAOImpl();
        //storeman=new storemanDAOImpl();
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setType(int type){
        this.type=type;
    }
    public int getType(){
        return type;
    }


    public void lookAssets() throws SQLException {
       /*
       查看剩余资产数量
        */
        storemanDAOImpl storeman=new storemanDAOImpl();
        List<Asset> assetslist=storeman.getAllAssets();
        
        System.out.print("No\tid\tname\ttotal\n");
        if(assetslist==null){
            System.out.println("no information");
        } else {
            for(int i = 0; i < assetslist.size(); i++) {
                Asset asset = assetslist.get(i);
                System.out.print(i + "\t");
                System.out.print(asset.getId() + "\t");
                System.out.print(asset.getName() + "\t");
                System.out.print(asset.getTotal() + "\n");
            }
        }
    }
    public void changePw() throws SQLException
    {
        //修改密码
        System.out.println("请输入新的密码：");
        Scanner scan=new Scanner(System.in);
        String s=scan.next();
        this.setPassword(s);
       /*
       在数据库中更新密码
        */
        User user=new OrdinaryUser();
        user.setId(id);
        user.setName(name);
        user.setPassword(s);
        user.setType(type);
        if(userDAO.updateUser(user)){
            System.out.println("修改成功，请妥善保管新密码。");
        }
        //scan.close();
    }

    abstract void operation() throws SQLException;
}