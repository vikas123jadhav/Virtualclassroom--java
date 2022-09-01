package com.solugenix.Virtualclassroom.repo;

import com.solugenix.Virtualclassroom.entity.PptAndVideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface IPptAndVideoEntityRepo extends JpaRepository<PptAndVideoEntity, Serializable> {

    List<PptAndVideoEntity> findByTypeIs(String type);

}
