package hu.uni.miskolc.lev.java.CourseBoot.controller;

import hu.uni.miskolc.lev.java.CourseBoot.model.entity.Course;
import hu.uni.miskolc.lev.java.CourseBoot.service.CourseService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CourseController {
    private final CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    // Kurzus felvétel
    @PostMapping("addCourse")
    @ResponseBody
    public void addCourse(@RequestBody Course course){
        courseService.addCourse(course);
        System.out.println(course.toString());
    }
    //Kurzus törlés
    @PostMapping(value = "/deleteCourse", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String deleteCourseById(@RequestParam int course_id){
        String result;
        if(courseService.deleteCourse(course_id)){
           result="Törlöve";
        }else{
            result="Törlés nem lehetséges!" +
                    "Előbb töröjön minden olyan rekordot a courseregistration táblából, ami kapcsolódik " +
                    "a "+course_id+" azonosítóval rendelkező kurzushoz!";
        }
        return result;
    }

    //Kurzus frissítés
    @PostMapping("updateCourse")
    @ResponseBody
    public void updateCourseById(@RequestParam int course_id, @RequestParam String name){
        courseService.updateCourse(course_id, name);
    }

    //Kurzus lekérdezés
    @GetMapping(value = "/getAllCourse", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String StudentAsHTML() {
        String result;
        if (courseService.getAllCourse().size() == 0) {
            result= "<html>\n" + "<header><title>getAllCourse</title></header>\n" +
                    "<body>\n" + "Course táblában nincs rekord!\n" +
                    "</body>\n" + "</html>";
        } else {
            result= "<html><header><title>getAllCourse</title></header><body>";
            result+="Course (rekordok száma: " + courseService.getAllCourse().size()+")<br><table id='studenttable' align='center'>" +
                    "<th>Kurzus id</th><th>Kurzus név</th><th colspan='3'>Művelet</th>";
            for(int i=0; i<courseService.getAllCourse().size(); i++) {
                result += "<tr id='coursetr"+courseService.getAllCourse().get(i).getCourse_id()+"'><td>"+courseService.getAllCourse().get(i).getCourse_id()+"</td>" +
                        "<td><input id='course"+courseService.getAllCourse().get(i).getCourse_id()+"' value='"+courseService.getAllCourse().get(i).getName()+"'></td>" +
                        "<td><button onclick='chooseCourse(this.id)' id='"+courseService.getAllCourse().get(i).getCourse_id()+"'>Kiválaszt</button></td>"+
                        "<td><button onclick='updateCourse(this.id)' id='"+courseService.getAllCourse().get(i).getCourse_id()+"'>Módosít</button></td>"+
                        "<td><button onclick='deleteCourse(this.id)' id='"+courseService.getAllCourse().get(i).getCourse_id()+"'>Törlés</button></td>";
            }
            result+="<tr><table></body></html>";
        }
        return result;
    }
}