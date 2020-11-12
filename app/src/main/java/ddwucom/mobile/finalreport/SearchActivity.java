package ddwucom.mobile.finalreport;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {
    MovieDBManager movieDBManager = null;
    Cursor cursor;
    TextView mTitle, mReview, mActor, mDir, mDate;
    EditText etID;
    ImageView imageview = null;
    String[] columns = new String[] {"_id", "movieTitle", "movieReview", "movieActor", "movieDir", "movieDate"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        movieDBManager = new MovieDBManager(this);

        imageview = findViewById(R.id.image_search);
        mTitle = findViewById(R.id.title_search);
        mReview = findViewById(R.id.review_search);
        mActor = findViewById(R.id.actor_search);
        mDir = findViewById(R.id.dir_search);
        mDate = findViewById(R.id.date_search);
        etID = findViewById(R.id.et_search_movie);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_search:
                int id = -1;

                cursor = movieDBManager.query(columns, MovieDBHelper.COL_TITLE + "='"+ etID.getText().toString() +"'", null, null, null, null);

                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        id = cursor.getInt(0);
                        mTitle.setText("제목: " + cursor.getString(1));
                        mReview.setText("리뷰: "+ cursor.getString(2));
                        mActor.setText("주연: " + cursor.getString(3));
                        mDir.setText("감독: " + cursor.getString(4));
                        mDate.setText("개봉일: " + cursor.getString(5));
                    }
                }

                if(id == 1) {
                    imageview.setImageResource(R.drawable.soundofmusic);
                } else if(id == 2) {
                    imageview.setImageResource(R.drawable.funnyher);
                } else if(id == 3) {
                    imageview.setImageResource(R.drawable.eternalsunshine);
                } else if(id == 4) {
                    imageview.setImageResource(R.drawable.notebook);
                } else if(id == 5) {
                    imageview.setImageResource(R.drawable.opera);
                } else {
                    imageview.setImageResource(R.drawable.flower);
                }


                break;
            case R.id.btn_cancel:
                finish();
                break;
        }
    }
}
