package org.svja.top.practica1_sevj.util;


public class CalorieCalculator {
    private static final double CALORIES_PER_KG = 7700;

    public static double calculateCaloriesPerMinute(double weight, double metValue) {
        return (metValue * weight * 3.5) / 200;
    }
    public static double calculateCaloriesPerHour(double caloriesPerMinute) {
        return caloriesPerMinute *60;

    }
    public static double calculateTotalCalories(double kilosToLose){
        return CALORIES_PER_KG * kilosToLose;

    }
    public static double calculateTotalCaloriesPerMinute(double weight,
                                                         double metValue){
        return (metValue*weight*3.5)/200;

    }
    public static double calculateTotalCaloriesPerHour(double caloriesPerMinute){

        return caloriesPerMinute*60;
    }
    public static double calculateTotalHours(double totalCalories,
                                             double caloriesPerHour){
        if(caloriesPerHour == 0){
            return 0;
        }
        return totalCalories/caloriesPerHour;

    }
    public static double getMETBySpeed(double speed){
        if(speed <8){
            return 8.3;
        } else if (speed < 10) {
            return 9.8;
        }else if (speed < 12) {
            return 11.0;
        }else if (speed < 14) {
            return 11.8;
        }else{
            return 12.8;
        }
    }

    public static int calculateSessions(
      double totalCalories,
      double caloriesPerMinute,
      int minutesPerSession
    ){
        double caloriesPerSession = caloriesPerMinute*minutesPerSession;
        if(caloriesPerSession == 0){
            return 0;
        }
        return (int)Math.ceil(totalCalories/caloriesPerSession);
    }


}
