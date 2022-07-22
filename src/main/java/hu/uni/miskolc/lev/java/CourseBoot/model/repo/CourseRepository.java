package hu.uni.miskolc.lev.java.CourseBoot.model.repo;

import hu.uni.miskolc.lev.java.CourseBoot.model.entity.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Integer> { }