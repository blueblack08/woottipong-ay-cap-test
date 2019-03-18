package com.woottipong.aycaptest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.woottipong.aycaptest.model.MemberType;
import com.woottipong.aycaptest.model.MemberTypeName;

@Repository
public interface MemberTypeRepository extends JpaRepository<MemberType, Long> {
    Optional<MemberType> findByTypeName(MemberTypeName typeName);
}