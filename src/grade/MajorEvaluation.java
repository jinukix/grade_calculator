package grade;

public class MajorEvaluation implements GradeEvaluation{
    @Override
    public String getGrade(int point) {
        String grade;

        if (90 <= point && point <= 100) grade = "A";
        else if (80 <= point && point <= 89) grade = "B";
        else if (70 <= point && point <= 79) grade = "C";
        else if (55 <= point && point <= 69) grade = "D";
        else grade = "F";

        return grade;
    }
}
