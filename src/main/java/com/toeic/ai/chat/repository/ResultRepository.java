
package com.toeic.ai.chat.repository;

import com.toeic.ai.chat.model.Result;
import com.toeic.ai.chat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findByUser(User user);
}
