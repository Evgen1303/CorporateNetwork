package co.norse.hr.mainservice.repositories;

import co.norse.hr.mainservice.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    List<Skill> findAllById(Long id);

    void deleteById(Long id);

}
