package hu.uni.miskolc.lev.java.CourseBoot.model.repo;

import hu.uni.miskolc.lev.java.CourseBoot.model.entity.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Integer> { }
