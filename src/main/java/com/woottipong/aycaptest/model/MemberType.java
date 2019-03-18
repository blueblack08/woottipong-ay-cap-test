package com.woottipong.aycaptest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "member_type")
public class MemberType {
    @Id
    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private MemberTypeName typeName;

	public MemberTypeName getTypeName() {
		return typeName;
	}

	public void setTypeName(MemberTypeName typeName) {
		this.typeName = typeName;
	}
}