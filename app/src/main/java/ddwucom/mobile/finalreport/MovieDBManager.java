package ddwucom.mobile.finalreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MovieDBManager {

    MovieDBHelper movieDBHelper = null;
    Cursor cursor = null;

    public MovieDBManager(Context context) { movieDBHelper = new MovieDBHelper(context); }

    public ArrayList<Movie> getAllMovie() {
        ArrayList movieList = new ArrayList();
        SQLiteDatabase db = movieDBHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + MovieDBHelper.TABLE_NAME, null);

        while(cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndex(MovieDBHelper.COL_ID));
            String title = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_TITLE));
            String review = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_REVIEW));
            String actor = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_ACTOR));
            String director = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_DIR));
            String date = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_DATE));
            movieList.add ( new Movie (id, title, review, actor, director, date) );
        }

        cursor.close();
        movieDBHelper.close();
        return movieList;
    }

    public boolean addNewMovie(Movie newMovie) {
        SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(MovieDBHelper.COL_TITLE, newMovie.getMovieTitle());
        value.put(MovieDBHelper.COL_REVIEW, newMovie.getMovieReview());
        value.put(MovieDBHelper.COL_ACTOR, newMovie.getMovieActor());
        value.put(MovieDBHelper.COL_DIR, newMovie.getMovieDir());
        value.put(MovieDBHelper.COL_DATE, newMovie.getMovieDate());

        long count = db.insert(MovieDBHelper.TABLE_NAME, null, value);

        if(count > 0) return true;
        return false;
    }

    public boolean modifyMovie(Movie movie) {
        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put(MovieDBHelper.COL_TITLE, movie.getMovieTitle());
        row.put(MovieDBHelper.COL_REVIEW, movie.getMovieReview());
        row.put(MovieDBHelper.COL_ACTOR, movie.getMovieActor());
        row.put(MovieDBHelper.COL_DIR, movie.getMovieDir());
        row.put(MovieDBHelper.COL_DATE, movie.getMovieDate());

        String whereClause = MovieDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(movie.get_id()) };

        int result = sqLiteDatabase.update(MovieDBHelper.TABLE_NAME, row, whereClause, whereArgs);
        movieDBHelper.close();
        if(result > 0) return true;
        return false;
    }

    public boolean removeMovie(long id) {
        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();
        String whereClause = MovieDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(id) };
        int result = sqLiteDatabase.delete(MovieDBHelper.TABLE_NAME, whereClause, whereArgs);
        movieDBHelper.close();
        if (result >0) return true;
        return false;
    }

    public Cursor query(String[] colums,
                        String selection,
                        String[] selectionArgs,
                        String groupBy,
                        String having,
                        String orderby)
    {
        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();
        return sqLiteDatabase.query(MovieDBHelper.TABLE_NAME,
                colums,
                selection,
                selectionArgs,
                groupBy,
                having,
                orderby);
    }


//    public String  getMoviesById(long id) {
//        SQLiteDatabase sqLiteDatabase = movieDBHelper.getReadableDatabase();
//        String whereClause = MovieDBHelper.COL_ID + "=?";
//        String[] whereArgs = new String[] { String.valueOf(id) };
//        String title1 = "";
//        Cursor cursor = sqLiteDatabase.query( MovieDBHelper.TABLE_NAME, null, whereClause, whereArgs, null, null, null, null);
//        if(cursor != null) {
//            while(cursor.moveToNext()) {
//                title1 =  cursor.getString(cursor.getColumnIndex("movieTitle"));
//            }
//        }
//        return title1;
//    }

    public void close() {
        if (movieDBHelper != null) movieDBHelper.close();
        if (cursor != null) cursor.close();
    };

}
