package com.udacity.asteroidradar.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }

        ViewModelProvider(
            this,
            MainViewModel.Factory(activity.application)
        ).get(MainViewModel::class.java)
    }

    private val asteroidAdapter = AsteroidAdapter(AsteroidAdapter.AsteroidListener { asteroid ->
        viewModel.onClickedAsteroid(asteroid)
    })

    /**
     * Called when the Fragment is ready to display content to the screen.
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Get a reference to the binding object and inflate the fragment views.
        val binding = FragmentMainBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.asteroidRecycler.adapter = asteroidAdapter

        viewModel.goToAsteroidDetail.observe(viewLifecycleOwner, { asteroid ->
            if (asteroid != null) {
                this.findNavController().navigate(MainFragmentDirections.actionShowDetail(asteroid))
                viewModel.onNavigatedAsteroid()
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.asteroidListing.observe(viewLifecycleOwner, { asteroid ->
            asteroid.apply {
                asteroidAdapter.submitList(this)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.onDateChangeFilter(
            when (item.itemId) {
                R.id.show_rent_menu -> {
                    FilterAsteroidDate.TODAY
                }
                R.id.show_all_menu -> {
                    FilterAsteroidDate.WEEK
                }
                else -> {
                    FilterAsteroidDate.ALL
                }
            }
        )
        return true
    }
}
