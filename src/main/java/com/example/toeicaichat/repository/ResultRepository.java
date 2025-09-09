
package com.example.toeicaichat.repository;

import com.example.toeicaichat.model.Result;
import com.example.toeicaichat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findByUser(User user);
}
