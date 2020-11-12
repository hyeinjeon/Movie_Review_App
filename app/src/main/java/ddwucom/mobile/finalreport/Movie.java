package ddwucom.mobile.finalreport;

import java.io.Serializable;

public class Movie implements Serializable {
    long _id;
    String movieTitle;
    String movieReview;
    String movieActor;
    String movieDir;
    String movieDate;

    public Movie(String movieTitle, String movieReview, String movieActor, String movieDir, String movieDate) {
        this.movieTitle = movieTitle;
        this.movieReview = movieReview;
        this.movieActor = movieActor;
        this.movieDir = movieDir;
        this.movieDate = movieDate;
    }

    public Movie(long _id, String movieTitle, String movieReview, String movieActor, String movieDir, String movieDate) {
        this._id = _id;
        this.movieTitle = movieTitle;
        this.movieReview = movieReview;
        this.movieActor = movieActor;
        this.movieDir = movieDir;
        this.movieDate = movieDate;
    }

    public Movie() {

    }

    public long get_id() { return _id; }

    public void set_id(long _id) { this._id = _id;}

    public String getMovieTitle() { return movieTitle; }

    public void setMovieTitle(String movieTitle) { this.movieTitle = movieTitle; }

    public String getMovieReview() { return movieReview; }

    public void setMovieReview(String movieReview) { this.movieReview = movieReview; }

    public String getMovieActor() { return movieActor; }

    public void setMovieActor(String movieActor) { this.movieActor = movieActor; }

    public String getMovieDir() { return movieDir; }

    public void setMovieDir(String movieDir) { this.movieDir = movieDir; }

    public String getMovieDate() { return movieDate; }

    public void setMovieDate(String movieDate) { this.movieDate = movieDate; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_id);
        sb.append(".\t\t");
        sb.append(movieTitle);
        sb.append("\t\t\t(");
        sb.append(movieReview);
        sb.append(")");
        return sb.toString();
    }
}
