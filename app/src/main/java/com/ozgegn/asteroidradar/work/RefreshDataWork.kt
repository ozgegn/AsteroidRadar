package com.ozgegn.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ozgegn.asteroidradar.data.local.getDatabase
import com.ozgegn.asteroidradar.repository.MainRepository

class RefreshDataWork(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = MainRepository(database)

        return try {

            val apiKey = inputData.getString("api_key") ?: ""
            val startDate = inputData.getString("start_date") ?: ""
            val endDate = inputData.getString("end_date") ?: ""

            repository.clearData()
            repository.getFeed(apiKey, startDate, endDate)
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }

    }

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }
}