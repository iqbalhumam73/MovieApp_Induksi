package com.mandiriinduksi.swiftmovie.module

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.mandiriinduksi.swiftmovie.data.network.ApiService
import com.mandiriinduksi.swiftmovie.data.network.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(FragmentComponent::class)
object AppModule {

    @FragmentScoped
    @Provides
    fun provideLayoutManager(
        @ApplicationContext
        context: Context
    ): LayoutManager{
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        return layoutManager
    }

    @FragmentScoped
    @Provides
    fun provideApiService(): ApiService {
        val apiService = RetrofitInstance.apiService
        return apiService
    }

}