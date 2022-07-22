package hu.uni.miskolc.lev.java.CourseBoot.service;

import hu.uni.miskolc.lev.java.CourseBoot.model.repo.CourseRegistrationRepository;
import hu.uni.miskolc.lev.java.CourseBoot.model.repo.CourseRepository;
import hu.uni.miskolc.lev.java.CourseBoot.model.repo.StudentRepository;
import hu.uni.miskolc.lev.java.CourseBoot.model.entity.CourseRegistration;
import hu.uni.miskolc.lev.java.CourseBoot.model.entity.CourseRegistrationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseRegistrationServiceImpl implements CourseRegistrationService {
    private static final Logger logger = LoggerFactory.getLogger(CourseRegistrationServiceImpl.class);
    private final CourseRegistrationRepository courseRegistrationRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public CourseRegistrationServiceImpl(CourseRegistrationRepository courseRegistrationRepository,
                                         CourseRepository courseRepository,
                                         StudentRepository studentRepository) {

        this.courseRegistrationRepository = courseRegistrationRepository;
        this.courseRepository=courseRepository;
        this.studentRepository=studentRepository;
    }
    @Override
    public void addCourseRegistration(CourseRegistrationDTO courseregistrationDTO) {
        try{
        CourseRegistration courseregistration= new CourseRegistration();
        courseregistration.setPower(courseregistrationDTO.getPower());
        courseregistration.setRegisteredAt(courseregistrationDTO.getRegisteredAt());
        if(courseRepository.findById(courseregistrationDTO.getCourse_id()).isPresent()) {
            courseregistration.setCourse(courseRepository.findById(courseregistrationDTO.getCourse_id()).get());
            courseregistration.setStudent(studentRepository.findById(courseregistrationDTO.getStudent_id()).get());
        }
        courseRegistrationRepository.save(courseregistration);
        System.out.println(lastAddedCourseReg());
        } catch (Exception e) {
            logger.error("Could not add coursereg.");
        }
    }

    @Override
    public void updateCourseRegistration(int courseregistration_id, int power){
        Optional<CourseRegistration> coursereg = courseRegistrationRepository.findById(courseregistration_id);
         coursereg.ifPresent(c -> {
            c.setPower(power);
            courseRegistrationRepository.save(c);
             System.out.println("coursereg_id:"+courseregistration_id+" updated");
        });
    }

    @Override
    public void deleteCourseRegistration (int courseregistration_id) {
        try {
             courseRegistrationRepository.deleteById(courseregistration_id);
             System.out.println("coursereg_id:"+courseregistration_id+" deleted");
        } catch (DataIntegrityViolationException e) {
            logger.error("Could not delete coursereg.");
        }
    }

    @Override
    public List<CourseRegistration> getAllCourseRegistration() {
        return (List<CourseRegistration>) courseRegistrationRepository.findAll();
    }

    public String lastAddedCourseReg(){
        int last_id=getAllCourseRegistration().size()-1;
        return "\n============== Tárgyfelvétel ==============\n" +
                getAllCourseRegistration().get(last_id).getStudent().getProfile().getName()+ " felvette " +
                "a(z) "+getAllCourseRegistration().get(last_id).getCourse().getName() + "kurzust\n" +
                "és az alábbi osztályzatot kapta: "+ getAllCourseRegistration().get(last_id).getPower();
    }
}