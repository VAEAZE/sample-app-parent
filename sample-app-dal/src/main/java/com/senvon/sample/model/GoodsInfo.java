package com.senvon.sample.model;

import java.io.Serializable;

public class GoodsInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * INTEGER(10) 必填<br>
     * 主键
     */
    private Integer id;

    /**
     * VARCHAR(30)<br>
     * 商品编号
     */
    private String goodCode;

    /**
     * DECIMAL(10)<br>
     * 剩余数量
     */
    private Long amount;

    /**
     * DECIMAL(10)<br>
     * 单次限额
     */
    private Long singleAmount;

    /**
     * INTEGER(10) 必填<br>
     * 获得 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * INTEGER(10) 必填<br>
     * 设置 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * VARCHAR(30)<br>
     * 获得 商品编号
     */
    public String getGoodCode() {
        return goodCode;
    }

    /**
     * VARCHAR(30)<br>
     * 设置 商品编号
     */
    public void setGoodCode(String goodCode) {
        this.goodCode = goodCode == null ? null : goodCode.trim();
    }

    /**
     * DECIMAL(10)<br>
     * 获得 剩余数量
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * DECIMAL(10)<br>
     * 设置 剩余数量
     */
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    /**
     * DECIMAL(10)<br>
     * 获得 单次限额
     */
    public Long getSingleAmount() {
        return singleAmount;
    }

    /**
     * DECIMAL(10)<br>
     * 设置 单次限额
     */
    public void setSingleAmount(Long singleAmount) {
        this.singleAmount = singleAmount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", goodCode=").append(goodCode);
        sb.append(", amount=").append(amount);
        sb.append(", singleAmount=").append(singleAmount);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        GoodsInfo other = (GoodsInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGoodCode() == null ? other.getGoodCode() == null : this.getGoodCode().equals(other.getGoodCode()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getSingleAmount() == null ? other.getSingleAmount() == null : this.getSingleAmount().equals(other.getSingleAmount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGoodCode() == null) ? 0 : getGoodCode().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getSingleAmount() == null) ? 0 : getSingleAmount().hashCode());
        return result;
    }
}