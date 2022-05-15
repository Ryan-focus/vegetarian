package bean;

import java.io.Serializable;

public class Goods implements Serializable{
    private int goodsId;
    private String goodsName;
    private float price;
    private int amount;
    private int leave_amount;
    private int saleNum;
    private String img;
    private GoodsClass goodsClass; 

    public Goods() {
    }
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getLeave_amount() {
        return leave_amount;
    }

    public void setLeave_amount(int leave_amount) {
        this.leave_amount = leave_amount;
    }

    public GoodsClass getGoodsClass() {
        return goodsClass;
    }

    public void setGoodsClass(GoodsClass goodsClass) {
        this.goodsClass = goodsClass;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }
	public Goods(int goodsId, String goodsName, float price, int amount, int leave_amount, int saleNum, String img,
			GoodsClass goodsClass) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.price = price;
		this.amount = amount;
		this.leave_amount = leave_amount;
		this.saleNum = saleNum;
		this.img = img;
		this.goodsClass = goodsClass;
	}
    
}