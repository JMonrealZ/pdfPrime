package com.example.pdfprime.presentation.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.pdfprime.R;

public class Dialogs {
    public static AlertDialog dialog;
    public static void createSelectNameDoc(final Fragment fragment, final Context ctx, LayoutInflater layoutInflater, final Uri uri){

        //Getting info from file(uri)
        Cursor cursor = ctx.getContentResolver().query(uri,null,null,null,null);
        int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);
        cursor.moveToFirst();
        String name = cursor.getString(nameIndex);
        final int size = (int) (cursor.getLong(sizeIndex) / 1024L);

        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        View customLayout = layoutInflater.inflate(R.layout.dialog_new_doc, null);
        builder.setView(customLayout);

        TextView tvTitleDialog = customLayout.findViewById(R.id.tvTitleDialog);
        final TextView tvFeedbackDialog = customLayout.findViewById(R.id.tvFeedbackDialog);
        final EditText etDocNameDialog = customLayout.findViewById(R.id.etDocNameDialog);
        Button btnCancelDialog = customLayout.findViewById(R.id.btnCancelDialog);
        Button btnSaveDialog = customLayout.findViewById(R.id.btnSaveDialog);
        etDocNameDialog.setText(name.substring(0,name.length()-4));

        /*final AlertDialog */dialog = builder.create();

        btnSaveDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameSelected = etDocNameDialog.getText().toString();
                if(nameSelected.length() > 0)
                    ((NameDocDialogInterface)fragment).onNameDocSelected(nameSelected + ".pdf",uri,size);
                else {
                    tvFeedbackDialog.setText(R.string.txtWriteSomething);
                }
            }
        });

        btnCancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void dissmis(){
        assert dialog != null : "Tried to dismiss dialog when is null";
        dialog.dismiss();
    }

}