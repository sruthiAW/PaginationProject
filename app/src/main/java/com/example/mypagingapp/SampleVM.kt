package com.example.mypagingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.mypagingapp.list.SamplePagingSource

class SampleVM : ViewModel() {

    val models = Pager(
        config = PagingConfig(pageSize = 10, enablePlaceholders = true, prefetchDistance = 3),
        pagingSourceFactory = { SamplePagingSource() }
    ).flow
        .cachedIn(viewModelScope)

}