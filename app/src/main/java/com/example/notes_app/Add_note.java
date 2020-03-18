package com.example.notes_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Add_note extends AppCompatActivity {
EditText ed,ed1;
DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        ed=findViewById(R.id.did);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Note");
    }
    public void openDialoge(View view) {

            if (!ed.getText().toString().trim().isEmpty()) {
                final Dialog Di = new Dialog(this);
                Di.setContentView(R.layout.add_note_dialoge);
                ed1= Di.findViewById(R.id.un_code);
                Button cancel = Di.findViewById(R.id.cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Di.dismiss();
                    }
                });
                Button ok = Di.findViewById(R.id.ok);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!ed1.getText().toString().trim().isEmpty()) {
                            access_firebase(ed.getText().toString().trim(),ed1.getText().toString().trim());
                            Toast.makeText(Add_note.this,"Note created",Toast.LENGTH_LONG).show();
                            finish();

                        } else {
                            Toast.makeText(Add_note.this, "fill uni_code", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                Di.show();

            } else {
                Toast.makeText(this, "area is empty", Toast.LENGTH_LONG).show();
            }
    }

    private void access_firebase(String ed,String ed1) {

        myRef.child(ed1).setValue(new note_details(ed1,ed));
    }


}
