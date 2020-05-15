package me.zhengjie.modules.personnel.repository;

import me.zhengjie.modules.personnel.domain.Induction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author Li GanSi
* @date 2020-04-08
*/
public interface InductionRepository extends JpaRepository<Induction, Long>, JpaSpecificationExecutor<Induction> {
}