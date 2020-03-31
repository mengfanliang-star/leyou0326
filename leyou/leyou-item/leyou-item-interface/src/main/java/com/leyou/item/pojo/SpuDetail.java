package com.leyou.item.pojo;

import java.io.Serializable;

/**
 * tb_spu_detail
 * @author 
 */
public class SpuDetail implements Serializable {
    private Integer id;

    private Long spuId;

    /**
     * 商品描述信息
     */
    private String description;

    /**
     * 通用规格参数数据
     */
    private String genericSpec;

    /**
     * 特有规格参数及可选值信息，json格式
     */
    private String specialSpec;

    /**
     * 包装清单
     */
    private String packingList;

    /**
     * 售后服务
     */
    private String afterService;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenericSpec() {
        return genericSpec;
    }

    public void setGenericSpec(String genericSpec) {
        this.genericSpec = genericSpec;
    }

    public String getSpecialSpec() {
        return specialSpec;
    }

    public void setSpecialSpec(String specialSpec) {
        this.specialSpec = specialSpec;
    }

    public String getPackingList() {
        return packingList;
    }

    public void setPackingList(String packingList) {
        this.packingList = packingList;
    }

    public String getAfterService() {
        return afterService;
    }

    public void setAfterService(String afterService) {
        this.afterService = afterService;
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
        SpuDetail other = (SpuDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSpuId() == null ? other.getSpuId() == null : this.getSpuId().equals(other.getSpuId()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getGenericSpec() == null ? other.getGenericSpec() == null : this.getGenericSpec().equals(other.getGenericSpec()))
            && (this.getSpecialSpec() == null ? other.getSpecialSpec() == null : this.getSpecialSpec().equals(other.getSpecialSpec()))
            && (this.getPackingList() == null ? other.getPackingList() == null : this.getPackingList().equals(other.getPackingList()))
            && (this.getAfterService() == null ? other.getAfterService() == null : this.getAfterService().equals(other.getAfterService()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSpuId() == null) ? 0 : getSpuId().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getGenericSpec() == null) ? 0 : getGenericSpec().hashCode());
        result = prime * result + ((getSpecialSpec() == null) ? 0 : getSpecialSpec().hashCode());
        result = prime * result + ((getPackingList() == null) ? 0 : getPackingList().hashCode());
        result = prime * result + ((getAfterService() == null) ? 0 : getAfterService().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", spuId=").append(spuId);
        sb.append(", description=").append(description);
        sb.append(", genericSpec=").append(genericSpec);
        sb.append(", specialSpec=").append(specialSpec);
        sb.append(", packingList=").append(packingList);
        sb.append(", afterService=").append(afterService);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}