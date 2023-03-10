package com.example.jewel;

public class Shop {

}
class Client{
    double cashBalance,goldBalance,silverBalance;
    Client(double cashBalance, double goldBalance, double silverBalance){
        this.cashBalance = cashBalance;
        this.goldBalance = goldBalance;
        this.silverBalance = silverBalance;
    }
}
class Price{
    double goldPrice,silverPrice;
    Price(double goldPrice,double silverPrice){
        this.goldPrice = goldPrice;
        this.silverPrice = silverPrice;
    }
}
class CashPayment{
    double amount,value;
    String type;
    CashPayment(double amount , double value,String type){
        this.amount = amount;
        this.value = value;
        this.type = type;
    }

    public double getfine(){
        if(type.equals("gold")){
            return (10.00 * amount)/value;
        }
        else if(type.equals("silver")){
            return (1000.00*amount)/value;
        }
        return -1;
    }
}

class MetalPayment{
    double weight,quality;
    String type;
    MetalPayment(double weight , double quality,String type){
        this.weight = weight;
        this.quality = quality;
        this.type = type;
    }

    public double getfine(){
        return weight*quality*0.01;
    }
}

class Sold{
    double weightPoly,weight,numPiece,quality;
    String type,labour;
    Sold(String type ,double weight,double weightPoly,double numPiece,double quality,String labour){
        this.type = type;
        this.weight = weight;
        this.weightPoly = weightPoly;
        this.numPiece = numPiece;
        this.quality = quality;
        this.labour = labour;
    }
    public double getprice(){
        String[] t = labour.split("/");
        if(t[1].equals("Kg")){
            return Double.parseDouble(t[0])*(weight - numPiece*weightPoly)*0.001;
        }
        else{
            return Double.parseDouble(t[0])*numPiece;
        }
    }
    public  double getfine(){
        return (weight - weightPoly*numPiece)*quality*0.01;
    }
}


