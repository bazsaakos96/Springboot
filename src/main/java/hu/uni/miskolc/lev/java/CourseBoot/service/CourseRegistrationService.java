package hu.uni.miskolc.lev.java.CourseBoot.service;

import hu.uni.miskolc.lev.java.CourseBoot.model.entity.CourseRegistration;
import hu.uni.miskolc.lev.java.CourseBoot.model.entity.CourseRegistrationDTO;

import java.util.List;

public interface CourseRegistrationService {
    void addCourseRegistration(CourseRegistrationDTO courseregistrationDTO);
    void updateCourseRegistration(int courseregistration_id, int power);
    void deleteCourseRegistration(int coursereg_id);
    List<CourseRegistration> getAllCourseRegistration();
    String lastAddedCourseReg();
}