package ddwucom.mobile.finalreport;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.style.UpdateLayout;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class UpdateActivity extends AppCompatActivity {

    int mYear, mMonth, mDay;
    TextView mTxtDate;

    Movie movie;

    EditText etTitle;
    EditText etReview;
    EditText etActor;
    EditText etDirector;
    TextView etDate;

    MovieDBManager movieDBManager;

    ImageView imageview = null;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        movie = (Movie) getIntent().getSerializableExtra("movie");

        mTxtDate = (TextView)findViewById(R.id.txtdate);

        Calendar cal = new GregorianCalendar();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        UpdateNow();

        long pos = movie.get_id();
        imageview = findViewById(R.id.update_image);

        if(pos == 1) {
            imageview.setImageResource(R.drawable.soundofmusic);
        } else if(pos == 2) {
            imageview.setImageResource(R.drawable.funnyher);
        } else if(pos == 3) {
            imageview.setImageResource(R.drawable.eternalsunshine);
        } else if(pos == 4) {
            imageview.setImageResource(R.drawable.notebook);
        } else if(pos == 5) {
            imageview.setImageResource(R.drawable.opera);
        }


        etTitle = findViewById(R.id.updateTitle);
        etReview = findViewById(R.id.updateReview);
        etActor = findViewById(R.id.updateActor);
        etDirector = findViewById(R.id.updateDir);
        etDate = findViewById(R.id.txtdate);

        etTitle.setText(movie.getMovieTitle());
        etReview.setText(movie.getMovieReview());
        etActor.setText(movie.getMovieActor());
        etDirector.setText(movie.getMovieDir());
        etDate.setText(movie.getMovieDate());

        movieDBManager = new MovieDBManager(this);
    }


    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.btnchangedate:
                new DatePickerDialog(UpdateActivity.this, mDateSetListener, mYear,
                        mMonth, mDay).show();
        }
    }

    DatePickerDialog.OnDateSetListener mDateSetListener  = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            UpdateNow();
        }
    };

    void UpdateNow(){
        mTxtDate.setText(String.format("%d/%d/%d", mYear, mMonth + 1, mDay));
    }


    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnUpdate1:
                boolean result = false;
                if(etTitle.getText().toString().equals("") || etReview.getText().toString().equals("") || etActor.getText().toString().equals("")||
                        etDirector.getText().toString().equals("") || etDate.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "정보를 모두 입력하세요.", Toast.LENGTH_SHORT).show();
                } else {
                    movie.setMovieTitle(etTitle.getText().toString());
                    movie.setMovieReview(etReview.getText().toString());
                    movie.setMovieActor(etActor.getText().toString());
                    movie.setMovieDir(etDirector.getText().toString());
                    movie.setMovieDate(etDate.getText().toString());
                    result = true;
                    movieDBManager.modifyMovie(movie);
                }

                if(result) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("movie", (Serializable) movie);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                } else {
                    setResult(RESULT_CANCELED);
                }
                break;
            case R.id.btnCancel3:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}
