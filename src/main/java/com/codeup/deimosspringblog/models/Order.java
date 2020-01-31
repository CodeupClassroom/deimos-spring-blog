package com.codeup.deimosspringblog.models;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    private String item1;
    private String item2;
    private String item3;

    public Order(){}

    public Order(String item1,String item2, String item3){
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }

    public long getId(){
        return this.id;
    }

    public String getItem1(){
        return this.item1;
    }

    public String getItem2(){
        return this.item2;
    }

    public String getItem3(){
        return this.item3;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setItem1(String item1){
        this.item1 = item1;
    }

    public void setItem2(String item2){
        this.item1 = item2;
    }

    public void setItem3(String item3){
        this.item1 = item3;
    }

}
