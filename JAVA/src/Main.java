import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        //输入起薪
        System.out.println("Please input starting salary :");
        int startingSalary = scanner.nextInt();
        if (startingSalary < 1) {
            errorPrint("Do not accept a number less than 1 for the starting salary.");
        }
        //输入涨薪次数
        System.out.println("Please input number of increments :");
        int incrementNumber = scanner.nextInt();
        if (incrementNumber < 1) {
            errorPrint("Do not accept a number less than 1 for frequency of increment.");
        }
        //输入涨幅
        System.out.println("Please input increment percent :");
        int incrementPercent = scanner.nextInt();
        if (incrementPercent < 0) {
            errorPrint("Do not accept a negative number for increment.");
        }
        //输入降薪次数
        System.out.println("Please input number of deductions :");
        int deductionsNumber = scanner.nextInt();
        if (deductionsNumber < 1) {
            errorPrint("Do not accept a number less than 1 for frequency of deductions.");
        }
        //输入降幅
        System.out.println("Please input deduction percent :");
        int deductionPercent = scanner.nextInt();
        if (deductionPercent < 0) {
            errorPrint("Do not accept a negative number for deduction.");
        }
        //输入预测年
        System.out.println("Please input years of prediction :");
        int predictionYear = scanner.nextInt();
        if (predictionYear < 1) {
            errorPrint("Do not accept a number less than 1 for year.");
        }

        SalaryInput salaryInput = new SalaryInput(startingSalary, incrementNumber, incrementPercent, deductionsNumber, deductionPercent, predictionYear);

        System.out.println(salaryInput.toString());


        double salary = (double) startingSalary;
        double deductionSalary = (double) startingSalary;
        List<SalaryOutput> list = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#.00");
        for (int i = 0; i < predictionYear; i++) {
            double incrementAmount = 0;
            double deductionAmount = 0;

            SalaryOutput salaryOutput = new SalaryOutput();
            salaryOutput.setPredictionYear(i + 1);
            salaryOutput.setIncrementNumber(incrementNumber);
            salaryOutput.setIncrementPercent(incrementPercent);
            salaryOutput.setStartingSalary(salary);
            for (int j = 0; j < incrementNumber; j++) {
                incrementAmount += (double) Math.round(salary * incrementPercent) / 100;
                salary = salary + (double) Math.round(salary * incrementPercent) / 100;
            }
            salaryOutput.setIncrementAmount(incrementAmount);

            salaryOutput.setDeductionsStartingSalary(deductionSalary);
            salaryOutput.setDeductionsNumber(deductionsNumber);
            salaryOutput.setDeductionsPercent(deductionPercent);
            for (int j = 0; j < deductionsNumber; j++) {
                deductionAmount += (double) Math.round(deductionSalary * deductionPercent) / 100;
                deductionSalary = deductionSalary - (double) Math.round(deductionSalary * deductionPercent) / 100;
            }
            salaryOutput.setDeductionsAmount(deductionAmount);
            salaryOutput.setSalary(salary-deductionAmount);

            salaryOutput.setSalaryGrowth(incrementAmount - deductionAmount);
            list.add(salaryOutput);

        }
        Formatter formatter = new Formatter(System.out);
        formatter.format("|%-20s |%-20s |%-20s |%-20s |%-20s|\n", "Year", "Starting Salary", "Number of Increments", "Increment %", "Increment Amount");
        list.forEach(salaryOutput -> {
            formatter.format("|%-20s |%-20s |%-20s |%-20s |%-20s|\n", salaryOutput.getPredictionYear(), df.format(salaryOutput.getStartingSalary()), salaryOutput.getIncrementNumber(), salaryOutput.getIncrementPercent(), df.format(salaryOutput.getIncrementAmount()));
        });
        System.out.println();

        formatter.format("|%-20s |%-20s |%-20s |%-20s |%-20s|\n", "Year", "Starting Salary", "Number of Deductions", "Deduction %", "Deduction Amount");
        list.forEach(salaryOutput -> {
            formatter.format("|%-20s |%-20s |%-20s |%-20s |%-20s|\n", salaryOutput.getPredictionYear(), df.format(salaryOutput.getDeductionsStartingSalary()), salaryOutput.getDeductionsNumber(), salaryOutput.getDeductionsPercent(), df.format(salaryOutput.getDeductionsAmount()));
        });
        System.out.println();

        formatter.format("|%-20s |%-20s |%-20s |%-20s |%-20s|\n", "Year", "Starting Salary", "Increment Amount", "Deduction Amount", "Salary Growth");
        list.forEach(salaryOutput -> {
            formatter.format("|%-20s |%-20s |%-20s |%-20s |%-20s|\n", salaryOutput.getPredictionYear(), df.format(salaryOutput.getSalary()), df.format(salaryOutput.getIncrementAmount()), df.format(salaryOutput.getDeductionsAmount()), df.format(salaryOutput.getSalaryGrowth()));
        });
        System.out.println();

    }

    private static void errorPrint(String message) {
        System.out.println(message);
        System.exit(0);
    }
}
