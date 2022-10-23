package com.example.cooolweather.db;
import org.litepal.crud.LitePalSupport;

public class Province extends LitePalSupport{
    private int id, provinceCode;
    private String provinceName;

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
