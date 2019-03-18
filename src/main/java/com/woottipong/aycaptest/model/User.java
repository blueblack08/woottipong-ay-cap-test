package com.woottipong.aycaptest.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "username"
        }),
        @UniqueConstraint(columnNames = {
			"phone"
        })
})
public class User{
    @Id
    @NotBlank
    @Size(min=3, max = 50)
    private String username;

    @JsonIgnore
    @NotBlank
    @Size(min=6, max = 100)
    private String password;
    
    @NotBlank
    private String name;
    
    private String address;
    
    @NotBlank
    private String phone;
    
    @NotBlank
    private String referenceCode;
    
    @NotNull
    private BigDecimal salary;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_type", 
    	joinColumns = @JoinColumn(name = "username"), 
    	inverseJoinColumns = @JoinColumn(name = "type_name"))
    private Set<MemberType> memberType = new HashSet<>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<MemberType> getMemberType() {
		return memberType;
	}

	public void setMemberType(Set<MemberType> memberType) {
		this.memberType = memberType;
	}

	public String getReferenceCode() {
		return referenceCode;
	}

	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
}