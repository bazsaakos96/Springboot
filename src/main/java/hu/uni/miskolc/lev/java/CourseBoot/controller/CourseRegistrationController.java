package hu.uni.miskolc.lev.java.CourseBoot.controller;

import hu.uni.miskolc.lev.java.CourseBoot.model.entity.CourseRegistrationDTO;
import hu.uni.miskolc.lev.java.CourseBoot.service.CourseRegistrationService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CourseRegistrationController {
    private final CourseRegistrationService courseRegistrationService;

    public CourseRegistrationController( CourseRegistrationService courseRegistrationService) {
        this.courseRegistrationService =courseRegistrationService;
    }

    @PostMapping("addCourseRegistration")
    @ResponseBody
    public void addCourseRegistration(@RequestBody CourseRegistrationDTO courseregistrationdto){
            courseRegistrationService.addCourseRegistration(courseregistrationdto);
    }

    @PostMapping("updateCourseRegistration")
    @ResponseBody
    public void updateCourseRegistration (@RequestParam int courseregistration_id, @RequestParam int power){
            courseRegistrationService.updateCourseRegistration(courseregistration_id, power);
    }

    @PostMapping("deleteCourseRegistration")
    @ResponseBody
    public void deleteCourseRegistration(@RequestParam int courseregistration_id){
           courseRegistrationService.deleteCourseRegistration(courseregistration_id);
    }

    @GetMapping(value = "/getAllCourseRegistration", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String CourseRegAsHTML() {
        try{
        String result;
        if (courseRegistrationService.getAllCourseRegistration().size() == 0) {
            result= "<html>\n" + "<header><title>getAllCourseAndStudents</title></header>\n" +
                    "<body>\n" + "Még egyetlen tanuló sem vett fel kurzust, " +
                    "kérjük rendelje a tanulókhoz az órarend szerinti kurzus id-ket!\n" +
                    "</body>\n" + "</html>";
        } else {
            result= "<html><header><title>getAllCourseAndStudents</title></header><body>";
            result+="Kurzus fevétel, kapcsoló tábla (rekordok száma: " +courseRegistrationService.getAllCourseRegistration().size()+")<table align='center'>" +
                    "<th>Sorsz.</th><th>Tanuló név (id)</th><th>Kurzus név (id)</th><th>Jegy</th><th colspan='2'>Művelet</th>";
         for(int i=0; i<courseRegistrationService.getAllCourseRegistration().size(); i++) {
                result += "<tr><td>"+courseRegistrationService.getAllCourseRegistration().get(i).getCourseregistration_id()+"</td>" +
                        "<td>"+courseRegistrationService.getAllCourseRegistration().get(i).getStudent().getProfile().getName() +
                        "(" +courseRegistrationService.getAllCourseRegistration().get(i).getStudent().getStudent_id()+")</td>" +
                        "<td>"+courseRegistrationService.getAllCourseRegistration().get(i).getCourse().getName() +
                        "("+courseRegistrationService.getAllCourseRegistration().get(i).getCourse().getCourse_id()+")</td>" +
                        "<td><input id='coursereg"+courseRegistrationService.getAllCourseRegistration().get(i).getCourseregistration_id()+"'" +
                        "value='"+courseRegistrationService.getAllCourseRegistration().get(i).getPower()+"'/>" +
                        "<td><button onclick='updateCourseReg(this.id)' id='"+courseRegistrationService.getAllCourseRegistration().get(i).getCourseregistration_id()+"'>Módosít</button></td>"+
                        "<td><button onclick='deleteCourseReg(this.id)' id='"+courseRegistrationService.getAllCourseRegistration().get(i).getCourseregistration_id()+"'>Töröl</button></td>";
            }
            result+="<tr><table>";
        }
        return result;
    } catch (Exception e) {
            return "Runtime error.";
        }
    }
}