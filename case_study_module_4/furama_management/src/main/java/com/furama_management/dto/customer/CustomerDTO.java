package com.furama_management.dto.customer;

import com.furama_management.dto.facility.FacilityDTO;
import com.furama_management.model.customer.Customer;
import com.furama_management.model.customer.CustomerType;
import com.furama_management.model.facility.Facility;
import com.furama_management.service.customer.ICustomerService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class CustomerDTO implements Validator {
    private int id;
    private CustomerType customerType;
    private String name;
    private String dateOfBirth;

    private boolean gender;
    private String idCard;
    private String phoneNumber;
    private String email;
    private String address;
    private boolean flag;


    public CustomerDTO() {
    }

    public CustomerDTO(CustomerType customerType, String name, String dateOfBirth, boolean gender, String idCard, String phoneNumber, String email, String address) {
        this.customerType = customerType;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.idCard = idCard;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public CustomerDTO(int id, CustomerType customerType, String name, String dateOfBirth, boolean gender, String idCard, String phoneNumber, String email, String address, boolean flag) {
        this.id = id;
        this.customerType = customerType;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.idCard = idCard;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    public void checkPhoneNumber(List<Customer> list, CustomerDTO customerDTO, Errors errors) {
        for (Customer x : list) {
            if (x.getPhoneNumber().equals(customerDTO.phoneNumber)) {
                errors.rejectValue("phoneNumber", "phoneNumber", "S??? ??i???n tho???i " + customerDTO.getPhoneNumber() + " ???? c?? ng?????i d??ng r???i");
            }
        }
    }

    public void checkIDCard(List<Customer> list, CustomerDTO customerDTO, Errors errors) {
        for (Customer x : list) {
            if (x.getIdCard().equals(customerDTO.idCard)) {
                errors.rejectValue("idCard", "idCard", "S??? CMND " + customerDTO.getIdCard() + " ???? t???n t???i");
            }
        }
    }

    public void checkDuplicate(List<Customer> listCustomer, CustomerDTO customerDto, Errors errors) {

        if (customerDto.getName().matches("")) {
            errors.rejectValue("name", "name", "T??n kh??ch h??ng kh??ng ???????c ????? tr???ng.");
        } else if (!customerDto.getName().matches("^\\p{Lu}\\p{Ll}+(\\s\\p{Lu}\\p{Ll}+)*$")) {
            errors.rejectValue("name", "name", "T??n kh??ch h??ng kh??ng ???????c ch???a s???. V?? c??c k?? t??? ?????u ti??n c???a m???i t??? ph???i vi???t hoa.");
        }

        //Validate age of birth day
        String birthdayVal = customerDto.getDateOfBirth();
        if (birthdayVal.matches("")) {
            errors.rejectValue("dateOfBirth", "dateOfBirth", "Vui l??ng ch???n ng??y sinh");
        } else {
            LocalDate dayOfBirth = LocalDate.parse(birthdayVal);
            LocalDate now = LocalDate.now();
            Period checkAge = Period.between(dayOfBirth, now);
            if (checkAge.getYears() < 18 | checkAge.getYears() > 100) {
                errors.rejectValue("dateOfBirth", "dateOfBirth", "Tu???i ph???i l???n h??n ho???c b???ng 18 v?? nh??? h??n 100");
            }
        }

        //Validate s??? ??i???n tho???i
        if (customerDto.getPhoneNumber().matches("")) {
            errors.rejectValue("phoneNumber", "phoneNumber", "S??? ??i???n tho???i kh??ng ???????c ????? tr???ng");
        } else if (!customerDto.getPhoneNumber().matches("^0[0-9]{9}$")) {
            errors.rejectValue("phoneNumber", "phoneNumber", "S??? ??i???n tho???i ph???i b???t ?????u b???ng 0 v?? c?? 10 s???");
        } else {
            for (int i = 0; i < listCustomer.size(); i++) {
                if (customerDto.getPhoneNumber().equals(listCustomer.get(i).getPhoneNumber())) {
                    errors.rejectValue("phoneNumber", "phoneNumber", "S??? ??i???n tho???i ???? c?? ng?????i s??? d???ng");
                    break;
                }
            }
        }
//

        //Validate s??? CMND
        if (customerDto.getIdCard().matches("")) {
            errors.rejectValue("idCard", "idCard", "S??? CMND ph???i kh??ng ???????c ????? tr???ng");
        } else if (!customerDto.getIdCard().matches("[0-9]{10}")) {
            errors.rejectValue("idCard", "idCard", "S??? CMND ph???i l?? 10 s??? v?? kh??ng ???????c ch???a b???t k?? k?? t??? n??o kh??c");
        } else {
            for (int i = 0; i < listCustomer.size(); i++) {
                if (customerDto.getIdCard().equals(listCustomer.get(i).getIdCard())) {
                    errors.rejectValue("idCard", "idCard", "S??? CMND ???? c?? ng?????i x??? d???ng");
                    break;
                }
            }
        }
        //Validate email
        if (customerDto.getEmail().matches("")) {
            errors.rejectValue("email", "email", "Email kh??ng ???????c ????? tr???ng");
        } else if (!customerDto.getEmail().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            errors.rejectValue("email", "email", "Email kh??ng ????ng ?????nh d???ng");
        } else {
            for (int i = 0; i < listCustomer.size(); i++) {
                if (customerDto.getEmail().equals(listCustomer.get(i).getEmail())) {
                    errors.rejectValue("email", "email", "Email ???? c?? ng?????i x??? d???ng");
                    break;
                }
            }
        }

        // Validate ?????a ch???
        if (customerDto.getAddress().matches("")) {
            errors.rejectValue("address", "address", "B???n qu???t n??? t??i bi???t t??m ????u");
        } else if (!customerDto.getAddress().matches("^\\p{Lu}\\p{Ll}+(\\s\\p{Lu}\\p{Ll}+)*$")) {
            errors.rejectValue("address", "address", "Ghi hoa ch??? c??i ?????u");

        }
    }

    @Override
    public void validate(Object target, Errors errors) {

        CustomerDTO customerDto = (CustomerDTO) target;
        //Validate name
        if (customerDto.getName().matches("")) {
            errors.rejectValue("name", "name", "T??n kh??ch h??ng kh??ng ???????c ????? tr???ng.");
        } else if (!customerDto.getName().matches("^\\p{Lu}\\p{Ll}+(\\s\\p{Lu}\\p{Ll}+)*$")) {
            errors.rejectValue("name", "name", "T??n kh??ch h??ng kh??ng ???????c ch???a s???. V?? c??c k?? t??? ?????u ti??n c???a m???i t??? ph???i vi???t hoa.");
        }

        //Validate age of birth day
        String birthdayVal = customerDto.getDateOfBirth();
        if (birthdayVal.matches("")) {
            errors.rejectValue("dateOfBirth", "dateOfBirth", "Vui l??ng ch???n ng??y sinh");
        } else {
            LocalDate dayOfBirth = LocalDate.parse(birthdayVal);
            LocalDate now = LocalDate.now();
            Period checkAge = Period.between(dayOfBirth, now);
            if (checkAge.getYears() < 18 | checkAge.getYears() > 100) {
                errors.rejectValue("dateOfBirth", "dateOfBirth", "Tu???i ph???i l???n h??n ho???c b???ng 18 v?? nh??? h??n 100");
            }
        }

        //Validate s??? ??i???n tho???i
        if (customerDto.getPhoneNumber().matches("")) {
            errors.rejectValue("phoneNumber", "phoneNumber", "S??? ??i???n tho???i kh??ng ???????c ????? tr???ng");
        } else if (!customerDto.getPhoneNumber().matches("^0[0-9]{9}$")) {
            errors.rejectValue("phoneNumber", "phoneNumber", "S??? ??i???n tho???i ph???i b???t ?????u b???ng 0 v?? c?? 10 s???");
        }
//

        //Validate s??? CMND
        if (customerDto.getIdCard().matches("")) {
            errors.rejectValue("idCard", "idCard", "S??? CMND ph???i kh??ng ???????c ????? tr???ng");
        } else if (!customerDto.getIdCard().matches("[0-9]{10}")) {
            errors.rejectValue("idCard", "idCard", "S??? CMND ph???i l?? 10 s??? v?? kh??ng ???????c ch???a b???t k?? k?? t??? n??o kh??c");
        }
        //Validate email
        if (customerDto.getEmail().matches("")) {
            errors.rejectValue("email", "email", "Email kh??ng ???????c ????? tr???ng");
        } else if (!customerDto.getEmail().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            errors.rejectValue("email", "email", "Email kh??ng ????ng ?????nh d???ng");
        }

        // Validate ?????a ch???
        if (customerDto.getAddress().matches("")) {
            errors.rejectValue("address", "address", "B???n qu???t n??? t??i bi???t t??m ????u");
        } else if (!customerDto.getAddress().matches("^\\p{Lu}\\p{Ll}+(\\s\\p{Lu}\\p{Ll}+)*$")) {
            errors.rejectValue("address", "address", "Ghi hoa ch??? c??i ?????u");
        }
    }
}
