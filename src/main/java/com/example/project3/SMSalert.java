package com.example.project3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

public class SMSalert extends MainActivity{
    boolean result = false;

    public boolean SMSNotifications() {
        // Create the object of AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(SMSalert.this);

        // Set the message show for the Alert time
        builder.setMessage("Do you wish to enable SMS notifications");

        // Set Alert Title
        builder.setTitle("SMS notifications");

        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the user click yes button then app will close
            dialog.dismiss();
            result = true;
        });

        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            // If user click no then dialog box is canceled.
            dialog.cancel();
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
        return result;
    }
}


