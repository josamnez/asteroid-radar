package com.udacity.asteroidradar.ui

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var asteroidRecyclerView: RecyclerView

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentMainBinding.inflate(inflater)

        asteroidRecyclerView = binding.asteroidRecycler
        asteroidRecyclerView.layoutManager = LinearLayoutManager(context)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        return binding.root
    }

    private inner class AsteroidViewHolder(view: RecyclerView): RecyclerView.ViewHolder(view){
        val codenameTextView: TextView = itemView.findViewById(R.id.text_asteroid_codename)
        val closeApproachTextView: TextView = itemView.findViewById(R.id.text_close_approach_date)
        val hazardImageView: ImageView = itemView.findViewById(R.id.img_potentially_hazardous)
    }

    private inner class AsteroidAdapter(var asteroids: List<Asteroid>)
        : RecyclerView.Adapter<AsteroidViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
            val view = layoutInflater.inflate(R.layout.asteroid_item, parent, false)
            return AsteroidViewHolder(view as RecyclerView)
        }

        override fun getItemCount() = asteroids.size

        override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
            val asteroid = asteroids[position]
            holder.apply {
                codenameTextView.text = asteroid.codename
                closeApproachTextView.text = asteroid.closeApproachDate
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }


}
