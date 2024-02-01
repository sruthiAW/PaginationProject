package com.example.mypagingapp.list

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState

class SamplePagingSource : PagingSource<Int, SampleModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SampleModel> {
        try {
            val start = params.key ?: START_KEY
            Log.d("MTEST", "Key: $start")
            val limit = params.loadSize
            Log.d("MTEST", "Limit: $limit")

            val resultList = arrayListOf<SampleModel>()
            for (i in 1..limit) {
                resultList.add(
                    SampleModel(
                        id = "${start}-$i",
                        name = "Name $i"
                    )
                )
            }

            Log.d("MTEST", "ResultList: $resultList")

            return LoadResult.Page(
                data = resultList,
                prevKey = if (start == START_KEY) null else start - 1,
                nextKey = if(start == END_KEY) null else start + 1
            )
        } catch (ex: Exception) {
            Log.d("MTEST", "Error: ${ex.message}")
            return LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SampleModel>): Int? {
        Log.d("MTEST", "getRefreshKey. State ${state.anchorPosition}")
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val START_KEY = 1
        private const val END_KEY = 10
    }
}
