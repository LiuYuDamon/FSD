/**
 * @ClassName SalaryInput
 * @Description TODO
 * @Author YuLiu
 * Date 2019/8/5 11:44
 * Version 1.0
 **/
public class SalaryInput {

    private int startingSalary;
    private int incrementNumber;
    private int incrementPercent;
    private int deductionsNumber;
    private int deductionPercent;
    private int predictionYear;

    public SalaryInput(int startingSalary, int incrementNumber, int incrementPercent, int deductionsNumber, int deductionPercent, int predictionYear) {
        this.startingSalary = startingSalary;
        this.incrementNumber = incrementNumber;
        this.incrementPercent = incrementPercent;
        this.deductionsNumber = deductionsNumber;
        this.deductionPercent = deductionPercent;
        this.predictionYear = predictionYear;
    }

    public int getStartingSalary() {
        return startingSalary;
    }

    public void setStartingSalary(int startingSalary) {
        this.startingSalary = startingSalary;
    }

    public int getIncrementNumber() {
        return incrementNumber;
    }

    public void setIncrementNumber(int incrementNumber) {
        this.incrementNumber = incrementNumber;
    }

    public int getIncrementPercent() {
        return incrementPercent;
    }

    public void setIncrementPercent(int incrementPercent) {
        this.incrementPercent = incrementPercent;
    }

    public int getDeductionsNumber() {
        return deductionsNumber;
    }

    public void setDeductionsNumber(int deductionsNumber) {
        this.deductionsNumber = deductionsNumber;
    }

    public int getDeductionPercent() {
        return deductionPercent;
    }

    public void setDeductionPercent(int deductionPercent) {
        this.deductionPercent = deductionPercent;
    }

    public int getPredictionYear() {
        return predictionYear;
    }

    public void setPredictionYear(int predictionYear) {
        this.predictionYear = predictionYear;
    }

    @Override
    public String toString() {
        return "SalaryInput{" +
                "startingSalary=" + startingSalary +
                ", incrementNumber=" + incrementNumber +
                ", incrementPercent=" + incrementPercent +
                ", deductionsNumber=" + deductionsNumber +
                ", deductionPercent=" + deductionPercent +
                ", predictionYear=" + predictionYear +
                '}';
    }
}
