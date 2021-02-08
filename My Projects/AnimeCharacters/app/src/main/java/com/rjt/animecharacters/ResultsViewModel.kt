package com.rjt.animecharacters

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ResultsViewModel : ViewModel() {

    val results = MutableLiveData<ResultList>()
    val disposable = CompositeDisposable()
    val observable = APIService.getRepo().create(GetAnimeCharacters::class.java)

    fun fetchResults(searchTerm: String) {
        disposable.add(observable.getAnime(searchTerm)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({results.value = it},
                { Log.e("Error", "${it.message}")})
        )
    }
}