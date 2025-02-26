import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fudu.movieapp.data.model.Movie
import com.fudu.movieapp.R

class MovieSearchAdapter(private val movieList: List<Movie>) :
    RecyclerView.Adapter<MovieSearchAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item_list, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movieList.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val posterImageView: ImageView = itemView.findViewById(R.id.imageViewPoster)
        private val movieTitleTextView: TextView = itemView.findViewById(R.id.movieTitleTextView)
        private val movieYearTextView: TextView = itemView.findViewById(R.id.movieYearTextView)

        fun bind(movie: Movie) {
            movieTitleTextView.text = movie.title
            movieYearTextView.text = movie.releaseYear
            Glide.with(itemView).load(movie.posterImgUrl).error(R.drawable.ic_image_placeholder)
                .into(posterImageView)
        }
    }
}
