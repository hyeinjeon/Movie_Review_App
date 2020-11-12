package ddwucom.mobile.finalreport;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MovieDBHelper extends SQLiteOpenHelper  {

    final static String TAG = "MovieDBHelper";

    final static String DB_NAME = "movies.db";
    public final static String TABLE_NAME = "movie_table";

    public static final String COL_ID = "_id";
    public final static String COL_TITLE = "movieTitle";
    public final static String COL_REVIEW = "movieReview";
    public final static String COL_ACTOR = "movieActor";
    public final static String COL_DIR = "movieDir";
    public final static String COL_DATE = "movieDate";

    public MovieDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " integer primary key autoincrement, "
                + COL_TITLE + " TEXT, " + COL_REVIEW + " TEXT, "
                + COL_ACTOR + " TEXT, " + COL_DIR + " TEXT, " +
                COL_DATE + " TEXT) ";
        Log.d(TAG, sql);
        db.execSQL(sql);

        db.execSQL("insert into " + TABLE_NAME + " values (1, 'Sound of music', '노래들이 모두 너무 좋다. 뮤지컬 영화 중에 최고인것 같다. 영상미도 이쁘고, 배우들의 연기력도 좋다. 죽기전에 꼭 봐야한다.', '줄리 앤드류스', '로버트 와이즈', '1969.10.29');");
        db.execSQL("insert into " + TABLE_NAME + " values (2, 'A queer girl', '전지현의 청순함과 발랄함이 두드러지는 영화이다. 첫사랑 영화 중 최고이다. 우울할때 보면 좋다. 그리고 전지현이 너무 이쁘다.', '전지현, 차태현', '조근식', '2016.05.12');");
        db.execSQL("insert into " + TABLE_NAME + " values (3, 'Eternal Sunshine', '인생 영화이다. 처음보면 뭐지?싶은 장면들이 많이 나오는데, 다시 보면 많인것들이 보이는 영화이다. 몇번이나 다시 봤다.', '짐 캐리, 케이트 윈슬렛', '미셸 공드리', '2005.11.10');");
        db.execSQL("insert into " + TABLE_NAME + " values (4, 'Notebook', '전세계적으로 유명한 첫사랑 영화.. 눈물 안흘리면서 보면 싸이코패스이다. 많은 친구들의 인생영화를 물어보면 노트북을 말한다.', '레이첼 맥아담스, 라이언 고슬링', '닉 카사베츠', '2004.11.26');");
        db.execSQL("insert into " + TABLE_NAME + " values (5, 'Phantom of the Opera', '노래가 너무 아름답다. 스토리도 잘 만들어져있지만, 귀가 힐링하는 영화.', '제라드 버틀러, 에미로섬', '조엘', '2004.12.08');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
