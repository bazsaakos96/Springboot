package hu.uni.miskolc.lev.java.CourseBoot.model.repo;

import hu.uni.miskolc.lev.java.CourseBoot.model.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> { }


