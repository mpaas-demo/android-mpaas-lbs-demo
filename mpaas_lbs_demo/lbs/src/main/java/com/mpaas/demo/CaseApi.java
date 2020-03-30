package com.mpaas.demo;

import android.content.Context;
import android.content.Intent;


public class CaseApi {

    public static void healthCase(Context context) {
        try {
            // com.mpaas.diagnose.DiagnoseService.DiagnoseService.createInstance(context);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public static void goTest(Context baseContext) {
        try {
            Class healthActivity = Class.forName("com.mpaas.diagnose.ui.HealthBizSelectActivity");
            Intent intent = new Intent(baseContext, healthActivity);
            baseContext.startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
