/**
 * @ClassName SalaryOutput
 * @Description TODO
 * @Author YuLiu
 * Date 2019/8/5 14:12
 * Version 1.0
 **/
public class SalaryOutput {

    private int predictionYear;
    private double startingSalary;
    private double salary;

    private int incrementNumber;
    private int incrementPercent;
    private double incrementAmount;

    private double deductionsStartingSalary;
    private int deductionsNumber;
    private int deductionsPercent;
    private double deductionsAmount;

    private double salaryGrowth;

    public int getPredictionYear() {
        return predictionYear;
    }

    public void setPredictionYear(int predictionYear) {
        this.predictionYear = predictionYear;
    }

    public double getStartingSalary() {
        return startingSalary;
    }

    public void setStartingSalary(double startingSalary) {
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

    public double getIncrementAmount() {
        return incrementAmount;
    }

    public void setIncrementAmount(double incrementAmount) {
        this.incrementAmount = incrementAmount;
    }

    public int getDeductionsNumber() {
        return deductionsNumber;
    }

    public void setDeductionsNumber(int deductionsNumber) {
        this.deductionsNumber = deductionsNumber;
    }

    public int getDeductionsPercent() {
        return deductionsPercent;
    }

    public void setDeductionsPercent(int deductionsPercent) {
        this.deductionsPercent = deductionsPercent;
    }

    public double getDeductionsAmount() {
        return deductionsAmount;
    }

    public void setDeductionsAmount(double deductionsAmount) {
        this.deductionsAmount = deductionsAmount;
    }

    public double getSalaryGrowth() {
        return salaryGrowth;
    }

    public void setSalaryGrowth(double salaryGrowth) {
        this.salaryGrowth = salaryGrowth;
    }

    public double getDeductionsStartingSalary() {
        return deductionsStartingSalary;
    }

    public void setDeductionsStartingSalary(double deductionsStartingSalary) {
        this.deductionsStartingSalary = deductionsStartingSalary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
