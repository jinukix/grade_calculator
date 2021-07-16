package school.report;

import grade.*;
import school.*;
import utils.Define;

import java.util.ArrayList;

public class GenerateGradeReport {
    School school = School.getInstance();
    public static final String TITLE = " 수강생 학점 \t\t\n";
    public static final String HEADER = " 이름  |  학번  |중점과목| 점수   \n ";
    public static final String LINE = "-------------------------------------\n";
    private StringBuffer sb = new StringBuffer();

    public String getReport() {
        ArrayList<Subject> subjectList = school.getSubjectList();
        for (Subject subject : subjectList) {
            makeHeader(subject);
            makeBody(subject);
            makeFooter();
        }

        return sb.toString();
    }

    public void makeHeader(Subject subject){
        sb.append(GenerateGradeReport.LINE);
        sb.append("\t").append(subject.getSubjectName());
        sb.append(GenerateGradeReport.TITLE);
        sb.append(GenerateGradeReport.HEADER);
        sb.append(GenerateGradeReport.LINE);
    }

    public void makeBody(Subject subject){
        ArrayList<Student> studentList = subject.getStudentList();

        for (Student student : studentList) {
            sb.append(student.getStudentName());
            sb.append(student.getStudentId());
            sb.append(" | ");
            sb.append(student.getMajorSubject().getSubjectName()).append("\t");
            sb.append(" | ");
            getScoreGrade(student, subject);
            sb.append("\n");
            sb.append(LINE);
        }
    }

    public void makeFooter(){
        sb.append("\n");
    }

    public void getScoreGrade(Student student, Subject subject) {
        ArrayList<Score> scoreList = student.getScoreList();
        int majorId = student.getMajorSubject().getSubjectId();

        GradeEvaluation[] gradeEvaluation = {new BasicEvaluation(), new MajorEvaluation(), new PassFailEvaluation()};

        for (Score score : scoreList) {
            if (score.getSubject().getSubjectId() == subject.getSubjectId()) {
                String grade;

                if (subject.getGradeType() == Define.PF_TYPE) {
                    grade = gradeEvaluation[Define.PF_TYPE].getGrade(score.getPoint());
                } else {
                    if (score.getSubject().getSubjectId() == majorId) {
                        grade = gradeEvaluation[Define.SAB_TYPE].getGrade(score.getPoint());
                    } else {
                        grade = gradeEvaluation[Define.AB_TYPE].getGrade(score.getPoint());
                    }
                }

                sb.append(score.getPoint());
                sb.append(":");
                sb.append(grade);
                sb.append(" | ");
            }
        }
    }
}
