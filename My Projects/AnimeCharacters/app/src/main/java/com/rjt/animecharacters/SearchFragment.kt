package com.rjt.animecharacters

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {

    private val disposable = CompositeDisposable()
    private lateinit var viewModel : ResultsViewModel
    lateinit var searchInput: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        searchInput = view.findViewById(R.id.searchInput)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        disposable.add(Observable.create(ObservableOnSubscribe<String> {
            searchInput.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText.isNullOrBlank()){
                        Toast.makeText(activity, "Please enter a search term", Toast.LENGTH_SHORT).show()
                    }
                    return false
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    viewModel.fetchResults(query!!)
                    activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragmentContainer, ResultsFragment())?.commit()
                    return false
                }
            })
        })
            .map { text -> text.toLowerCase() }
            .debounce(500, TimeUnit.MILLISECONDS)
            .filter{text -> text.isNotBlank()}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("Nia", "$it")
            }
        )
    }
}