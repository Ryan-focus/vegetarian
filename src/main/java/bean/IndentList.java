package bean;

import java.io.Serializable;

public class IndentList implements Serializable{
    private int indentId;
    private int goodsId;
    private int amount;
    private Goods goods;
    private  Indent indent;

    public int getIndentId() {
        return indentId;
    }

    public void setIndentId(int indentId) {
        this.indentId = indentId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Indent getIndent() {
        return indent;
    }

    public void setIndent(Indent indent) {
        this.indent = indent;
    }

	public IndentList(int indentId, int goodsId, int amount, Goods goods, Indent indent) {
		super();
		this.indentId = indentId;
		this.goodsId = goodsId;
		this.amount = amount;
		this.goods = goods;
		this.indent = indent;
	}
    
}