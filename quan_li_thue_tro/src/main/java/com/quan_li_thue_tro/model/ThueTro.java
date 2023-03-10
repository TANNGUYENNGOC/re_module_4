package com.quan_li_thue_tro.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;

@Entity
public class ThueTro implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String tenNguoiThueTro;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String soDienThoai;
    @Column(columnDefinition = "DATE")
    private String ngayBatDauThue;
    @ManyToOne
    private HinhThucThanhToan hinhThucThanhToan;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String ghiChu;


    public ThueTro(int id, String tenNguoiThueTro, String soDienThoai, String ngayBatDauThue, HinhThucThanhToan hinhThucThanhToan, String ghiChu) {
        this.id = id;
        this.tenNguoiThueTro = tenNguoiThueTro;
        this.soDienThoai = soDienThoai;
        this.ngayBatDauThue = ngayBatDauThue;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.ghiChu = ghiChu;
    }

    public ThueTro(String tenNguoiThueTro, String soDienThoai, String ngayBatDauThue, HinhThucThanhToan hinhThucThanhToan, String ghiChu) {
        this.tenNguoiThueTro = tenNguoiThueTro;
        this.soDienThoai = soDienThoai;
        this.ngayBatDauThue = ngayBatDauThue;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.ghiChu = ghiChu;
    }

    public ThueTro() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenNguoiThueTro() {
        return tenNguoiThueTro;
    }

    public void setTenNguoiThueTro(String tenNguoiThueTro) {
        this.tenNguoiThueTro = tenNguoiThueTro;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getNgayBatDauThue() {
        return ngayBatDauThue;
    }

    public void setNgayBatDauThue(String ngayBatDauThue) {
        this.ngayBatDauThue = ngayBatDauThue;
    }

    public HinhThucThanhToan getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setHinhThucThanhToan(HinhThucThanhToan hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ThueTro thueTro =(ThueTro) target;
        if(thueTro.getTenNguoiThueTro().equals("")){
            errors.rejectValue("tenNguoiThueTro","tenNguoiThueTro","T??n ng?????i thu?? tr??? kh??ng ???????c ????? tr???ng");
        }
        if (thueTro.getSoDienThoai().equals("")){
            errors.rejectValue("soDienThoai","soDienThoai","S??? ??i???n tho???i kh??ng ???????c ????? tr???ng");
        }
        if(thueTro.getNgayBatDauThue().equals("")){
            errors.rejectValue("ngayBatDauThue","ngayBatDauThue","Ng??y b???t ????u thu?? kh??ng ???????c ????? tr???ng");
        }
    }
}
