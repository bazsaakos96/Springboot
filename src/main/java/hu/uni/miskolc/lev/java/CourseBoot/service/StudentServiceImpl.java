package hu.uni.miskolc.lev.java.CourseBoot.service;

import hu.uni.miskolc.lev.java.CourseBoot.model.repo.StudentRepository;
import hu.uni.miskolc.lev.java.CourseBoot.model.repo.ProfileRepository;
import hu.uni.miskolc.lev.java.CourseBoot.model.entity.Profile;
import hu.uni.miskolc.lev.java.CourseBoot.model.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    private final ProfileRepository profileRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, ProfileRepository profileRepository) {
        this.studentRepository = studentRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public void addStudent(Student student) {
        // studentRepository.save(student);
        student = studentRepository.save(student);
        Profile profile = new Profile();
        profile.setStudent(student);
        profileRepository.save(profile);
        System.out.println(lastAddedStudent(student));
    }

    @Override
    public void updateStudent(int student_id, String email) {
        Optional<Student> student = studentRepository.findById(student_id);
        if (student.isPresent()) {
            Student s =student.get();
            s.setEmail(email);
            studentRepository.save(s);
            System.out.println("\nStudentServiceImp update student: "+student_id+" "+email);
        }
    }

    @Override
    public Boolean deleteStudent(int student_id) {
        try {
            studentRepository.deleteById(student_id);
            System.out.println("student_id:"+student_id+ " deleted");
            return true;
        } catch (DataIntegrityViolationException e) {
            logger.error("Could not remove student.");
            return false;
            }
    }

    public List<Student> getAllStudent() {
        return (List<Student>) studentRepository.findAll();
    }

    public String lastAddedStudent(Student student){
        int last_id=getAllStudent().size()-1;
        return "============== Felvett Tanuló ==============\n" +
                "Tanuló id:"+
                getAllStudent().get(last_id).getStudent_id()+
                "\ne-mail:"+
                getAllStudent().get(last_id).getEmail()+ "\n";
    }
}