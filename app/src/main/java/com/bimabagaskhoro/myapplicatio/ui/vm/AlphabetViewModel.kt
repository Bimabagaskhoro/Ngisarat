package com.bimabagaskhoro.myapplicatio.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bimabagaskhoro.myapplicatio.domain.usecase.ItemAlphabetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlphabetViewModel @Inject constructor(val useCases: ItemAlphabetUseCase) : ViewModel() {
    fun getAlphabet() = useCases.getAllAlphabet().asLiveData()

}