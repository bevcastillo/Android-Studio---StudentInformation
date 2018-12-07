package com.example.beverly.studentinformation;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener, AdapterView.OnItemSelectedListener {
    //define the properties of the UI
    EditText txtIdno, txtName;
    Spinner cboCourse, cboLevel;
    RadioGroup grpGender;
    Button btnOk, btnCancel;

    //utility variables
    String selectedCourse, selectedLevel;
    String selectedGender;
    RadioButton btnSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //instantiate the properties
        txtIdno=(EditText)this.findViewById(R.id.idno);
        txtName=(EditText)this.findViewById(R.id.fullName);

        cboCourse=(Spinner) this.findViewById(R.id.course);
        cboLevel=(Spinner) this.findViewById(R.id.level);

        grpGender=(RadioGroup) this.findViewById(R.id.gender);

        btnOk=(Button) this.findViewById(R.id.ok);
        this.btnCancel=(Button) this.findViewById(R.id.cancel);

        //add listeners to the spinners
        cboCourse.setOnItemSelectedListener(this);
        cboLevel.setOnItemSelectedListener(this);

        //add listeners to the buttons
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //check which spinner is touched
        int sid = parent.getId();
        switch(sid){
            case R.id.course: selectedCourse=this.cboCourse.getItemAtPosition(position).toString(); break;
            case R.id.level: selectedLevel=this.cboLevel.getItemAtPosition(position).toString(); break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override

    public void onClick(View v) {
        //get the data from the EditText
        String idno = this.txtIdno.getText().toString();
        String fullName = this.txtName.getText().toString();


        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, 0);

        //get the selected radiobutton checked
        int radioid=this.grpGender.getCheckedRadioButtonId();
        btnSelected=(RadioButton)findViewById(radioid);
        selectedGender=btnSelected.getText().toString();


        //create an alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Student Information");
        builder.setNeutralButton("OK", null);
        builder.setMessage("IDNO: " +idno+ "\nNAME: " +fullName+ "\nCOURSE: "+this.selectedCourse+"\nLEVEL: "+this.selectedLevel+"\nGENDER: "+this.selectedGender);

        this.txtIdno.setText("");
        this.txtName.setText("");

        //set trappings
        if((idno.equals("")) && (fullName.equals(""))){
            Toast.makeText(MainActivity.this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
        }
        else{
            //display the alert dialog box
            AlertDialog dialog = builder.create();
            dialog.show();
        }


    }
}


