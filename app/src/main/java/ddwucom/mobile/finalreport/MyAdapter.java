package ddwucom.mobile.finalreport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Movie> myMovieList;
    private LayoutInflater layoutInflater;

    public MyAdapter(Context context, int layout, ArrayList<Movie> myMovieList) {
        this.context = context;
        this.layout = layout;
        this.myMovieList = myMovieList;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myMovieList.size();
    }

    @Override
    public Object getItem(int position) {
        return myMovieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return myMovieList.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final int pos = position;

        if (convertView == null) {
            convertView = layoutInflater.inflate(layout, viewGroup, false);
        }

        ImageView movieImage1 = convertView.findViewById(R.id.movie_image);
        TextView movieTitle = convertView.findViewById(R.id.movie_title);
        TextView movieReview = convertView.findViewById(R.id.movie_review);
        TextView movieActor = convertView.findViewById(R.id.movie_actor);
        TextView movieDir = convertView.findViewById(R.id.movie_director);

        if(pos == 0) {
            movieImage1.setImageResource(R.drawable.soundofmusic);
        } else if(pos == 1) {
            movieImage1.setImageResource(R.drawable.funnyher);
        } else if(pos == 2) {
            movieImage1.setImageResource(R.drawable.eternalsunshine);
        } else if(pos == 3) {
            movieImage1.setImageResource(R.drawable.notebook);
        } else if(pos == 4) {
            movieImage1.setImageResource(R.drawable.opera);
        } else {
            movieImage1.setImageResource(R.drawable.flower);
        }

        movieTitle.setText(String.valueOf(myMovieList.get(pos).getMovieTitle()));
        movieReview.setText(String.valueOf(myMovieList.get(pos).getMovieReview()));
        movieActor.setText(String.valueOf(myMovieList.get(pos).getMovieActor()));
        movieDir.setText(String.valueOf(myMovieList.get(pos).getMovieDir()));

        return convertView;
    }

}
