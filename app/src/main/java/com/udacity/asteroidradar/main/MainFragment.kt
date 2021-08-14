package com.udacity.asteroidradar.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.AsteroidItemBinding
import com.udacity.asteroidradar.databinding.FragmentMainBinding

/**
 * Show a list of Asteroids on screen.
 */
class MainFragment : Fragment() {


    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }

        ViewModelProvider(
            this,
            MainViewModel.MainViewModelFactory(activity.application)
        ).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentMainBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

//        viewModel.pictureOfDay.observe(viewLifecycleOwner, Observer { pictureOfDay ->
//
//        })
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }

//
//    /**
//     * One way to delay creation of the viewModel until an appropriate lifecycle method is to use
//     * lazy. This requires that viewModel not be referenced before onViewCreated(), which we
//     * do in this Fragment.
//     */
//    private val viewModel: MainViewModel by lazy {
//        val activity = requireNotNull(this.activity) {
//            "You can only access the viewModel after onViewCreated()"
//        }
//        ViewModelProvider(this, MainViewModel.MainViewModelFactory(activity.application))
//            .get(MainViewModel::class.java)
//    }
//
//    /**
//     * RecyclerView Adapter
//     */
////    private var asteroidAdapter: AsteroidAdapter? = null
//
//    /**
//     * Called immediately after onCreateView() has returned, and fragment's
//     * view hierarchy has been created.
//     */
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//    }
//
//    /**
//     * Called to have the fragment instantiate its user interface view.
//     */
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//                              savedInstanceState: Bundle?):
//            View { val binding: FragmentMainBinding = DataBindingUtil.inflate(
//            inflater,
//            R.layout.fragment_main,
//            container,
//            false)
//
//        // val binding = FragmentMainBinding.inflate(inflater)
//        binding.lifecycleOwner = this
//
//        binding.viewModel = viewModel
//
//
//
//        binding.root.findViewById<RecyclerView>(R.id.asteroid_recycler).apply {
//            layoutManager = LinearLayoutManager(context)
////            adapter = asteroidAdapter
//        }
//        setHasOptionsMenu(true)
//
//        return binding.root
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.main_overflow_menu, menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return true
//    }
//
}
//
//
//
//
///**
// * RecyclerView Adapter for setting up data binding on the items in the list.
// */
//class AsteroidAdapter(): RecyclerView.Adapter<AsteroidViewHolder>() {
//    /**
//     * The asteroids that our Adapter will show
//     */
//    var asteroids: List<Asteroid> = emptyList()
//        @SuppressLint("NotifyDataSetChanged")
//        set(value) {
//            field = value
//            // For an extra challenge, update this to use the paging library.
//
//            // Notify any registered observers that the data set has changed. This will cause every
//            // element in our RecyclerView to be invalidated.
//            notifyDataSetChanged()
//        }
//
//
//    /**
//     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
//     * an item.
//     */
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
//        val withDataBinding: AsteroidItemBinding = DataBindingUtil.inflate(
//            LayoutInflater.from(parent.context),
//            AsteroidViewHolder.LAYOUT,
//            parent,
//            false)
//        return AsteroidViewHolder(withDataBinding)
//    }
//
//    override fun getItemCount() = asteroids.size
//
//    /**
//     * Called by RecyclerView to display the data at the specified position. This method should
//     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
//     * position.
//     */
//    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
//        holder.viewDataBinding.also {
//                it.asteroid = asteroids[position]
//        }
//    }
//}
//
///**
// * ViewHolder for DevByte items. All work is done by data binding.
// */
//class AsteroidViewHolder(val viewDataBinding: AsteroidItemBinding) :
//    RecyclerView.ViewHolder(viewDataBinding.root) {
//    companion object {
//        @LayoutRes
//        val LAYOUT = R.layout.asteroid_item
//    }
//}
//



