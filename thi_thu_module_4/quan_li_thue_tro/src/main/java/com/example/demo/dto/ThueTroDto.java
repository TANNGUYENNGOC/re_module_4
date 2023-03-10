package com.example.demo.dto;

import com.example.demo.model.HinhThucThanhToan;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

public class ThueTroDto implements Validator {
    private int id;

    private String tenNguoiThueTro;

    private String soDienThoai;

    private String ngayBatDauThue;

    private HinhThucThanhToan hinhThucThanhToan;

    public ThueTroDto(int id, String tenNguoiThueTro, String soDienThoai, String ngayBatDauThue, HinhThucThanhToan hinhThucThanhToan) {
        this.id = id;
        this.tenNguoiThueTro = tenNguoiThueTro;
        this.soDienThoai = soDienThoai;
        this.ngayBatDauThue = ngayBatDauThue;
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public ThueTroDto(String tenNguoiThueTro, String soDienThoai, String ngayBatDauThue, HinhThucThanhToan hinhThucThanhToan) {
        this.tenNguoiThueTro = tenNguoiThueTro;
        this.soDienThoai = soDienThoai;
        this.ngayBatDauThue = ngayBatDauThue;
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public ThueTroDto() {
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

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ThueTroDto thueTroDto = (ThueTroDto) target;
        // Validate name
        if (thueTroDto.getTenNguoiThueTro().matches("")) {
            errors.rejectValue("tenNguoiThueTro", "tenNguoiThueTro", "T??n ng?????i thu?? tr??? kh??ng ???????c ????? tr???ng.");
        } else if (!thueTroDto.getTenNguoiThueTro().matches("^\\p{Lu}\\p{Ll}+(\\s\\p{Lu}\\p{Ll}+)*$")) {
            errors.rejectValue("tenNguoiThueTro", "tenNguoiThueTro", "T??n ng?????i thu?? tr??? kh??ng ???????c ch???a s???. V?? c??c k?? t??? ?????u ti??n c???a m???i t??? ph???i vi???t hoa.");
        }

        //Validate s??? ??i???n tho???i
        if (thueTroDto.getSoDienThoai().matches("")) {
            errors.rejectValue("soDienThoai", "soDienThoai", "S??? ??i???n tho???i kh??ng ???????c ????? tr???ng");
        } else if (!thueTroDto.getSoDienThoai().matches("^0[0-9]{9}$")) {
            errors.rejectValue("soDienThoai", "soDienThoai", "S??? ??i???n tho???i ph???i b???t ?????u b???ng 0 v?? c?? 10 s???");
        }

        //Validate ng??y
        String ngayBatDauThue = thueTroDto.getNgayBatDauThue();
        if (ngayBatDauThue.matches("")) {
            errors.rejectValue("ngayBatDauThue", "ngayBatDauThue", "Vui l??ng ch???n ng??y b???t ?????u thu??");
        }
    }
}
