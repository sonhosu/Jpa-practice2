package com.jpa.crud.repository;

import com.jpa.crud.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member ,Long> {

}
