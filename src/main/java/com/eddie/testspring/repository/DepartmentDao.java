package com.eddie.testspring.repository;

import com.eddie.testspring.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/15 6:02 下午
 */

@Repository
public interface DepartmentDao extends JpaRepository<Department,Long> {

   List<Department> findDepartmentsByLevels(Integer level);
}
