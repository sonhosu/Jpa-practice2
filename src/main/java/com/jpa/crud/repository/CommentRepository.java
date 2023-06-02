package com.jpa.crud.repository;

import com.jpa.crud.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository  extends JpaRepository<Comment ,Long> {


}
