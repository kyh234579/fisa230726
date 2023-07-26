package com.fisa.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fisa.model.domain.entity.DeptCopy;

@Repository
public interface DeptCopyRepository extends CrudRepository<DeptCopy, Integer>{


}
