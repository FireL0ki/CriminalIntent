package com.example.criminalintent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "CrimeListFragment"

    class CrimeListFragment : Fragment() {

        private lateinit var crimeRecyclerView: RecyclerView
        private var adapter: CrimeAdapter? = CrimeAdapter(emptyList())

        // set up crimeLiveViewModel with lazy initialization (to be created when needed)
        private val crimeListViewModel: CrimeListViewModel by lazy {
            ViewModelProviders.of(this).get(CrimeListViewModel::class.java)
        }

        // on create, print list of crimes
//        override fun onCreate(savedInstanceState: Bundle?) {
//            super.onCreate(savedInstanceState)
//            Log.d(TAG, "Total crimes: ${crimeListViewModel.crimes.size}")
//        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.fragment_crime_list, container, false)

            crimeRecyclerView =
                view.findViewById(R.id.crime_recycler_view) as RecyclerView
            crimeRecyclerView.layoutManager = LinearLayoutManager(context)
            crimeRecyclerView.adapter = adapter

            return view
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            crimeListViewModel.crimeListLiveData.observe(
                viewLifecycleOwner,
                Observer { crimes ->
                    crimes?.let {
                        Log.i(TAG, "Got crimes ${crimes.size}")
                        updateUI(crimes)
                    }
                })
        }


        private fun updateUI(crimes: List<Crime>) {
            adapter = CrimeAdapter(crimes)
            crimeRecyclerView.adapter = adapter
        }


        // define a view holder (add an inner class that extends from RecyclerView.ViewHolder
        private inner class CrimeHolder(view: View)
            : RecyclerView.ViewHolder(view), View.OnClickListener {

            private lateinit var crime: Crime

            private val titleTextView: TextView = itemView.findViewById(R.id.crime_title)
            private val dateTextView: TextView = itemView.findViewById(R.id.crime_date)

            private val solvedImageView: ImageView = itemView.findViewById(R.id.crime_solved)

            init {
                itemView.setOnClickListener(this)
            }

            // bind will update the title TextView and date TextView to show the state of the Crime
            fun bind(crime: Crime) {
                this.crime = crime
                titleTextView.text = this.crime.title
                dateTextView.text = this.crime.date.toString()
                solvedImageView.visibility = if (crime.isSolved) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            }

            override fun onClick(v: View) {
                Toast.makeText(context, "${crime.title} pressed!", Toast.LENGTH_SHORT)
                    .show()
            }


        }


        private inner class CrimeAdapter(var crimes: List<Crime>)
            : RecyclerView.Adapter<CrimeHolder>() {

            // creates a view to display, wrap in view holder, return result
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                    : CrimeHolder {
                val view = layoutInflater.inflate(R.layout.list_item_crime, parent, false)
                return CrimeHolder(view)
            }
            // return number of items in the list of crimes per view's request
            override fun getItemCount() = crimes.size

            // populates a given holder with the crime from given position
            override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
                val crime = crimes[position]
                holder.bind(crime)
            }
        }


        companion object {
            fun newInstance(): CrimeListFragment {
                return CrimeListFragment()
            }
        }
    }

