package hu.uni.miskolc.lev.java.CourseBoot.controller;

import hu.uni.miskolc.lev.java.CourseBoot.model.entity.Student;
import hu.uni.miskolc.lev.java.CourseBoot.service.ProfileService;
import hu.uni.miskolc.lev.java.CourseBoot.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {
    private final StudentService studentService;
    private final ProfileService profileService;

    public StudentController(StudentService studentService,ProfileService profileService) {
        this.studentService = studentService;
        this.profileService = profileService;
    }

    @PostMapping("addStudent")
    @ResponseBody
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @PostMapping("updateStudent")
    @ResponseBody
    public void updateStudentById(@RequestParam int student_id, @RequestParam String email){
        studentService.updateStudent(student_id, email);
    }

    @PostMapping(value = "/deleteStudent", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String deleteStudent(@RequestParam int student_id){
        String result;
        if(studentService.deleteStudent(student_id)){
           result="Törölve";
        }else{
           result="Törlés nem lehetséges!" +
                   "Előbb töröjön minden olyan rekordot a courseregistration táblából, ami kapcsolódik" +
                   " a "+student_id+" azonosítóval rendelkező tanulóhoz!";
        }
        return result;
    }

    @GetMapping(value = "/getAllStudent", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String StudentAsHTML() {
        String result;
        if (studentService.getAllStudent().size() == 0) {
            result= "<html>\n" + "<header><title>getAllstudent</title></header>\n" +
                    "<body>\n" + "Student és a profile táblában nincs rekord!\n" +
                    "</body>\n" + "</html>";
        } else {
             result= "<html><header><title>getAllstudent</title></header><body>";
             result+="Student/Profile (rekordok száma: " + studentService.getAllStudent().size()+")<br><table id='coursetable' align='center'>" +
                     "<th>Tanuló</th><!--th>Tanuló név (id) (profile táblából)</th--><th>E-mail</th><th colspan='3'>Művelet</th>";
             for(int i=0; i<studentService.getAllStudent().size(); i++) {
                 result += "<tr id='studenttr"+studentService.getAllStudent().get(i).getStudent_id()+"'><td>"+profileService.getAllProfile().get(i).getName()+" (id:"+ studentService.getAllStudent().get(i).getStudent_id()+")</td>" +
                         "<td><input readonly id='email"+studentService.getAllStudent().get(i).getStudent_id()+"' value='"+ studentService.getAllStudent().get(i).getEmail()+"'></td>"+
                         "<td><button onclick='chooseStudent(this.id)' id='"+ studentService.getAllStudent().get(i).getStudent_id()+"'>Kiválaszt</button></td>"+
                         "<!--td><button onclick='updateStudent(this.id)' id='"+ studentService.getAllStudent().get(i).getStudent_id()+"'>Módosít</button></td-->"+
                         "<td><button onclick='getProfile(this.id)' id='"+ studentService.getAllStudent().get(i).getStudent_id()+"'>Profil</button></td>"+
                         "<td><button onclick='deleteStudent(this.id)' id='"+ studentService.getAllStudent().get(i).getStudent_id()+"'>Törlés</button></td>";
             }
             result+="<tr><table></body></html>";
        }
        return result;
    }
}