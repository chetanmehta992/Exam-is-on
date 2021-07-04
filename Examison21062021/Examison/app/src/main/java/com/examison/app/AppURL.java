package com.examison.app;

public class AppURL {

    private static final String appURL = "https://examison.com/";
    private static final String My_Account = appURL + "student-dashboard/home.php";
    private static final String Login = appURL + "login.php";
    private static final String Register = appURL + "register.php";
    private static final String Contact_Us = appURL + "contact-us.php";
    private static final String All_Cousres = appURL + "all-courses.php";
    private static final String Free_Pdf = appURL + "free_pdf.php";


//    methods here ----------------------------------------------------


    public static String getAppURL() {
        return appURL;
    }

    public static String getMy_Account() {
        return My_Account;
    }

    public static String getLogin() {
        return Login;
    }

    public static String getContact_Us() {
        return Contact_Us;
    }

    public static String getAll_Cousres() {
        return All_Cousres;
    }

    public static String getFree_Pdf() {
        return Free_Pdf;
    }

    public static String getRegister() {
        return Register;
    }
}