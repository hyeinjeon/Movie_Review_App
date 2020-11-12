package ddwucom.mobile.finalreport;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.datatype.Duration;

public class AddActivity extends AppCompatActivity {

    int mYear, mMonth, mDay, mHour, mMinute;

    TextView mTxtDate;

    EditText etTitle;
    EditText etReview;
    EditText etActor;
    EditText etDirector;
    TextView etDate;

    MovieDBManager movieDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mTxtDate = (TextView)findViewById(R.id.txtdate1);
        Calendar cal = new GregorianCalendar();

        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        UpdateNow();


        etTitle = findViewById(R.id.addTitle);
        etReview = findViewById(R.id.addReview);
        etActor = findViewById(R.id.addActor);
        etDirector = findViewById(R.id.addDir);
        etDate = findViewById(R.id.txtdate1);

        movieDBManager = new MovieDBManager(this);
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.btnchangedate2:
                new DatePickerDialog(AddActivity.this, mDateSetListener, mYear, mMonth, mDay).show();
                break;
        }
    }

    DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    UpdateNow();
                }
    };

    void UpdateNow(){
        mTxtDate.setText(String.format("%d/%d/%d", mYear, mMonth + 1, mDay));
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd3:
                boolean result = false;

                if(etTitle.getText().toString().equals("") || etReview.getText().toString().equals("") || etActor.getText().toString().equals("")||
                   etDirector.getText().toString().equals("") || etDate.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "정보를 모두 입력하세요.", Toast.LENGTH_SHORT).show();
                } else {
                    result = movieDBManager.addNewMovie(
                            new Movie(etTitle.getText().toString(), etReview.getText().toString(),
                                    etActor.getText().toString(), etDirector.getText().toString(),
                                    etDate.getText().toString()));
                }

                if (result) {    // 정상수행에 따른 처리
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("movie", etTitle.getText().toString() );
                    setResult(RESULT_OK, resultIntent);
                    finish();
                } else {        // 이상에 따른 처리
                    Toast.makeText(this, "새로운 영화 추가 실패!", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnCancel2:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }




}
