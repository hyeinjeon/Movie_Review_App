// 과제명: 영화 정보 관리 앱
// 분반: 02 분반
// 학번: 20181009, 성명: 전혜인
// 제출일: 2020년 7월 3일
// 가산점 항목: 1. 검색 기능 - 영화 제목으로 검색 가능. 메뉴에 '영화 검색'추가.
//             2. 위젯 사용 - 날짜 입력 시 날짜 입력 위젯 사용
//             3. 이미지 사용 - 리스트 뷰 항목에 이미지 사용 (기본 기능 이외의 추가적인 기능)

package ddwucom.mobile.finalreport;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "MainActivity";
    final int REQ_CODE = 100;
    final int UPDATE_CODE = 200;

    ListView listView;
    MyAdapter myAdapter;
    ArrayList<Movie> movieList = null;
    MovieDBManager movieDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList = new ArrayList<Movie>();
        myAdapter = new MyAdapter(this, R.layout.custom_adapter_view, movieList);
        listView = findViewById(R.id.listView);
        listView.setAdapter(myAdapter);
        movieDBManager = new MovieDBManager(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = movieList.get(position);
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("movie", movie);
                startActivityForResult(intent, UPDATE_CODE);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.dialog_title)
                        .setMessage(R.string.dialog_message)
                        .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                boolean result = movieDBManager.removeMovie(movieList.get(pos).get_id());

                                if(result) {
                                    Toast.makeText(MainActivity.this, "삭제 완료", Toast.LENGTH_SHORT).show();
                                    movieList.clear();
                                    movieList.addAll(movieDBManager.getAllMovie());
                                    myAdapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(MainActivity.this, "삭제 실패", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton(R.string.dialog_cancel, null)
                        .setCancelable(false)
                        .show();

                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        movieList.clear();
        movieList.addAll(movieDBManager.getAllMovie());
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_movie:
                Intent intent = new Intent(this, AddActivity.class);
                startActivityForResult(intent, REQ_CODE);
                break;
            case R.id.search_movie:
                Intent intent3 = new Intent(this, SearchActivity.class);
                startActivityForResult(intent3, REQ_CODE);
                break;
            case R.id.introduce:
                Intent intent2 = new Intent(this, IntroActivity.class);
                startActivityForResult(intent2, REQ_CODE);
                break;
            case R.id.finish:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("앱 종료하기")
                        .setMessage("앱을 종료하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finishAffinity();
                                System.runFinalization();
                                System.exit(0);
                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();
                break;
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE) {
            switch (resultCode) {
                case RESULT_OK:
                    String movie = data.getStringExtra("movie");
                    Toast.makeText(this,  movie + " 추가 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "영화 추가 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        } else if (requestCode == UPDATE_CODE) {
            switch (resultCode) {
                case RESULT_OK:
                    Toast.makeText(this, "영화 수정 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "영화 수정 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }


}