package com.assist.control.assistcontrolbackend.repository;

import com.assist.control.assistcontrolbackend.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long>  {
}
